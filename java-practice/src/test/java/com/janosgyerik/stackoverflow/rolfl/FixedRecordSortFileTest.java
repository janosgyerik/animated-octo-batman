package com.janosgyerik.stackoverflow.rolfl;

import org.junit.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class FixedRecordSortFileTest {

	private static final byte[][] DATA = buildStrings();

	private static final class IntParser implements FixedRecordSortFile.RecordParser {

		@Override
		public int recordLength() {
			return 4;
		}

		@Override
		public int compare(ByteBuffer bufferOne, ByteBuffer bufferTwo) {
			return Integer.compare(bufferOne.getInt(), bufferTwo.getInt());
		}

	}

	private static final class StringParser implements FixedRecordSortFile.RecordParser {

		@Override
		public int recordLength() {
			return 4;
		}

		private String getString(ByteBuffer buff) {
			byte[] bytes = new byte[4];
			for (int i = 0; i < bytes.length; i++) {
				bytes[i] = buff.get();
			}
			return new String(bytes, StandardCharsets.US_ASCII);
		}

		@Override
		public int compare(ByteBuffer bufferOne, ByteBuffer bufferTwo) {
			return getString(bufferOne).compareTo(getString(bufferTwo));
		}

	}

	private static final FixedRecordSortFile.RecordParser STRINGPARSER = new StringParser();
	private static final FixedRecordSortFile.RecordParser INTPARSER = new IntParser();

	private static void buildIntFile(final Path path, final long count) {
		Random r = new Random();
		try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(path)))) {
			for (long i = 0; i < count; i++) {
				dos.writeInt(r.nextInt());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static byte[][] buildStrings() {
		byte[][] data = new byte[1000][];
		for (int i = 0; i < data.length; i++) {
			data[i] = String.format("%03d%n", i).getBytes(StandardCharsets.US_ASCII);
		}
		return data;
	}

	private static void buildFileReg(final Path path, final int reps, final int range) {
		try (DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(Files.newOutputStream(path)))) {
			for (int i = 0; i < reps; i++) {
				for (int j = 0; j < range; j++) {
					dos.write(DATA[j]); // some positive integer
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static void checkStringFileSorted(final Path path, final int reps, final int range) {
		try (InputStream is = Files.newInputStream(path);
			 BufferedInputStream bis = new BufferedInputStream(is);
			 DataInputStream dis = new DataInputStream(bis)) {
			byte[] b = new byte[4];
			for (int j = 0; j < range; j++) {
				for (int i = 0; i < reps; i++) {
					dis.readFully(b);
					if (!Arrays.equals(b, DATA[j])) {
						throw new IllegalStateException(String.format("Expected value %s at position %d but got %s", Arrays.toString(DATA[j]), range * j + i, Arrays.toString(b)));
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	private static double toMS(long nanostart) {
		return (System.nanoTime() - nanostart) / 1000000.0;
	}

	private static void testInt(long count) throws IOException {
		Path data = Paths.get("testSort" + count);
		long start = System.nanoTime();
		buildIntFile(data, count);

		double buildTime = toMS(start);

		start = System.nanoTime();
		FixedRecordSortFile.sort(data, INTPARSER);
		double sortTime = toMS(start);

		start = System.nanoTime();
		FixedRecordSortFile.checkSorted(data, INTPARSER);

		double checkTime = toMS(start);

		System.out.printf("Built %10d in %8.3fms, Sorted in %8.3fms, checked in %8.3fms%n", count, buildTime, sortTime, checkTime);
	}

	private static void validate(int reps, int range) throws IOException {
		Path data = Paths.get("testSort" + reps + "by" + range);
		long start = System.nanoTime();
		buildFileReg(data, reps, range);

		double buildTime = toMS(start);

		start = System.nanoTime();
		FixedRecordSortFile.sort(data, STRINGPARSER);
		double sortTime = toMS(start);

		start = System.nanoTime();
		FixedRecordSortFile.checkSorted(data, STRINGPARSER);
		double checkTime = toMS(start);

		checkStringFileSorted(data, reps, range);

		System.out.printf("Built %10d in %8.3fms, Sorted in %8.3fms, checked in %8.3fms%n", reps * range, buildTime, sortTime, checkTime);
	}

	//@Test
	public void test() throws Exception {
		Thread.sleep(2000);
		testInt(101);
		long[] sizes = {100, 101, 10001,};// 10000001, 100000001};
		for (int i = 10; i < 500; i += 13) {
			validate(i, i);
		}
		for (int i = 1; i < 1000; i++) {
			testInt(100);
		}
		for (long sz : sizes) {
			testInt(sz);
		}

	}
}

/**
 * Sort a file of records / fixed-length data using a temporary file.
 */
class FixedRecordSortFile implements AutoCloseable {

	/**
	 * This class is required to construct a FixedRecordSortFile, and it is used to optimize IO, and process and parse the records.
	 */
	public interface RecordParser extends Comparator<ByteBuffer> {
		/**
		 * Identify the record length of each record. The IO and buffer positioning operations are affected by this.
		 *
		 * @return the size of each record
		 */
		public int recordLength();

		/**
		 * Compare the <strong>contents</strong> at the current position of each buffer.
		 * <p>
		 * The contract used by this class is that each buffer will be positioned at the start
		 * of a record. This record will need to be parsed in a way that allows both records
		 * to be compared.
		 * <p>
		 * It is guaranteed that each buffer will be able to read a full single record from the
		 * current buffer position ( buffer.position() + recordLength() <= buffer.limit() )
		 */
		@Override
		public int compare(ByteBuffer bufferOne, ByteBuffer bufferTwo);
	}

	/**
	 * An approximate size of the memory mapped window we want to use for efficiency.
	 * we will ensure that it is about this size, but also a multiple of:
	 * 4K (some filesystem block sizes) - 512 is old-style sector size, 4K is newer.
	 * the record size for the data.
	 * some operating systems require memory mapped IO to align with IO boundaries.
	 */
	public static final int DEFAULT_MAPPED_WINDOW = 1024 * 1024;


	/**
	 * Sort (in place) a file of fixed-length records defined by the parser.
	 * <p>
	 * A temp file will also be created in the system/user temp directory which will be the same size as the input file.
	 *
	 * @param source The file to sort
	 * @param parser The parser that understands the file.
	 * @throws IOException if there is an IO problem accessing the file.
	 * @see #sort(Path, RecordParser, Path, int)
	 */
	public static void sort(Path source, RecordParser parser) throws IOException {
		final Path tmp = Files.createTempFile("sort", ".tmp");
		sort(source, parser, tmp, DEFAULT_MAPPED_WINDOW);
	}

	/**
	 * Core method for sorting the data in the source path, using the specified temp file.
	 * <p>
	 * The algorithm used by this sort will create multiple (as many as 3) mappings of the file to memory,
	 * and will sort the data in the source file, using space in the temp file as well.
	 * <p>
	 * Note that the method will scan the file first to ensure it is not already sorted. There is no need
	 * to call checkSorted before sort, because sort already calls checkSorted first.
	 *
	 * @param source       The file to sort
	 * @param parser       The class that parses, and controls the process
	 * @param temp         The temp file for sorting in (will grow to the same size as the source, will be deleted at the end).
	 * @param memoryWindow How much of the file (this is a hint, not an absolute) will be mapped to memory at any one time
	 */
	public static void sort(final Path source, final RecordParser parser, final Path temp, final int memoryWindow) throws IOException {
		if (checkSorted(source, parser, memoryWindow)) {
			return;
		}
		final long size = Files.size(source);
		final int recordLength = parser.recordLength();
		final long recordCount = size / recordLength;
		final int windowSize = calculateWindow(memoryWindow, recordLength);

		if (recordCount * recordLength != size) {
			throw new IllegalArgumentException(String.format(
					"The input file size (%d) is not an exact multiple of the record length (%d), and has %d trailing bytes.",
					size, recordLength, size % recordLength));
		}
		if (recordCount <= 1) {
			return;
		}
		try (FixedRecordSortFile sorter = new FixedRecordSortFile(parser, size, recordCount, windowSize, source, temp)) {

			// read chunks of SMALLSORT records, and sort them in memory, blocking them in to the temp file.
			sorter.fastSort();

		}
	}

	/**
	 * Scan a file, parsing each record, and return true if the records are all in ascending order.
	 * <p>
	 * This method will use the default Memory window size to memory-map the file
	 *
	 * @param data   The path to scan
	 * @param parser The Record parser that decodes and compares records.
	 * @return true if the file is already sorted.
	 * @throws IOException if there is a problem accessing the file.
	 */
	public static boolean checkSorted(final Path data, final RecordParser parser) throws IOException {
		return checkSorted(data, parser, DEFAULT_MAPPED_WINDOW);
	}

	/**
	 * Scan a file, parsing each record, and return true if the records are all in ascending order.
	 *
	 * @param data         The path to scan
	 * @param parser       The Record parser that decodes and compares records.
	 * @param memoryWindow The suggested size of memory to use for memory-mapped IO.
	 * @return true if the file is already sorted.
	 * @throws IOException if there is a problem accessing the file.
	 */
	public static boolean checkSorted(final Path data, final RecordParser parser, final int memoryWindow) throws IOException {
		try (FileChannel channel = (FileChannel) Files.newByteChannel(data, StandardOpenOption.READ)) {
			final int recsize = parser.recordLength();
			final long sz = channel.size();
			if (sz % recsize != 0) {
				return false;
			}
			long reccnt = sz / recsize;

			final int winsize = calculateWindow(memoryWindow, recsize);

			final FileBuffer buffa = new FileBuffer(channel, MapMode.READ_ONLY, winsize, sz, recsize, winsize / recsize);
			final FileBuffer buffb = new FileBuffer(channel, MapMode.READ_ONLY, winsize, sz, recsize, winsize / recsize);

			for (long record = 1; record < reccnt; record++) {
				buffa.positionAtRecord(record - 1);
				buffb.positionAtRecord(record);
				int ck = parser.compare(buffa.buffer, buffb.buffer);
				if (ck < 0) {
					return false;
				}
			}
			return true;
		}

	}


	// 4K memory blocks are good for IO.
	private static final int IO_WINDOW = 4 * 1024;

	// Find an appropriate multiple of the IO and record size that is about the size of the requested mapping size.
	private static int calculateWindow(final int approxSize, final int recordlength) {
		int baseio = IO_WINDOW * recordlength;
		final int twoiomask = (IO_WINDOW << 1) - 1;
		while (baseio > approxSize && (baseio & twoiomask) == 0) {
			// the window is larger than wanted, and can be halved and still keep a full multiple of IO_WIN
			baseio >>>= 1;
		}
		while (baseio < approxSize) {
			baseio <<= 1;
		}
		return baseio;
	}

	// FileBuffer class manages/positions a single MappedByteBuffer within a file.
	private static final class FileBuffer {
		private final MapMode mode;
		private final FileChannel channel;
		private final long windowSize;
		private final long size;
		private final int recordLength;
		private final int recordsPerWindow;

		private long mapPosition = -1;
		private MappedByteBuffer buffer;


		public FileBuffer(FileChannel channel, MapMode mode, long windowSize, long size,
						  int recordLength, int recordsPerWindow) {
			this.mode = mode;
			this.channel = channel;
			this.windowSize = windowSize;
			this.size = size;
			this.recordLength = recordLength;
			this.recordsPerWindow = recordsPerWindow;
		}

		private void locateWindow(final long filepos) throws IOException {

			if (filepos >= size) {
				throw new IllegalArgumentException("Illegal file position " + filepos + " in file of size " + size);
			}

			final long mappos = filepos / windowSize;
			if (mappos != mapPosition) {
				final long pos = mappos * windowSize;
				final long len = Math.min(size - pos, windowSize);
				buffer = channel.map(mode, pos, len);
				mapPosition = mappos;
			}
			buffer.position((int) (filepos % windowSize));
		}

		private void positionAtRecord(final long record) throws IOException {
			final long filepos = record * recordLength;
			locateWindow(filepos);
			buffer.position((int) (record % recordsPerWindow) * recordLength);
		}

		private void copyRecord(final ByteBuffer source) {
//			buffer.put(source.array(), source.arrayOffset() + source.position(), recordLength);
//			source.position(source.position() + recordLength);
			for (int i = 0; i < recordLength; i++) {
				buffer.put(source.get());
			}
		}

		private void copyAll(FileBuffer source) throws IOException {
			for (long pos = 0; pos < size; pos += windowSize) {
				locateWindow(pos);
				source.locateWindow(pos);
				buffer.put(source.buffer);
				pos += windowSize;
			}
//			long pos = 0;
//			while (pos < size) {
//				locateWindow(pos);
//				source.locateWindow(pos);
//				buffer.put(source.buffer);
//				pos += windowSize;
//			}
		}

		@Override
		public String toString() {
			int recs = buffer.limit() / recordLength;
			StringBuilder sb = new StringBuilder(recs * (recordLength * 2 + 1));
			int pos = 0;
			for (int i = 0; i < recs; i++) {
				for (int j = 0; j < recordLength; j++) {
					sb.append(String.format("%02x", buffer.get(pos++)));
				}
				sb.append(" ");
			}
			return sb.toString();
		}
	}

	// FastFile manages two FileBuffers for each file/channel.
	private final class FastFile {
		private final Path path;
		private final FileChannel channel;

		private final FileBuffer bufferPrimary;
		private final FileBuffer bufferSecondary;

		FastFile(final Path path) throws IOException {
			this.path = path;
			this.channel = FileChannel.open(path, StandardOpenOption.WRITE, StandardOpenOption.READ);
			if (channel.size() != size) {
				// force the file to the specified size if it was not that size already
				channel.write(ByteBuffer.allocate(1), size - 1);
				channel.truncate(size);
			}
			bufferPrimary = new FileBuffer(channel, MapMode.READ_WRITE, windowSize, size, recordLength, recordsPerWindow);
			bufferSecondary = new FileBuffer(channel, MapMode.READ_WRITE, windowSize, size, recordLength, recordsPerWindow);
		}

	}

	private final long size;
	private final long recordCount;
	private final int recordLength;
	private final int windowSize;
	private final int recordsPerWindow;
	private final RecordParser parser;
	private final FastFile source, workspace;

	// Constructor is private. Instances are not made available outside this class.
	private FixedRecordSortFile(final RecordParser parser, long size, long recordCount, int windowSize, Path source, Path temp) throws IOException {
		this.parser = parser;
		this.size = size;
		this.recordLength = parser.recordLength();
		this.recordCount = recordCount;
		this.windowSize = windowSize;
		this.recordsPerWindow = windowSize / recordLength;
		this.source = new FastFile(source);
		this.workspace = new FastFile(temp);
	}

	// public close() to conform with AutoClose interface.
	@Override
	public void close() throws IOException {
		source.channel.force(true);
		source.channel.close();
		source.bufferPrimary.buffer = null;
		source.bufferSecondary.buffer = null;
		workspace.channel.close();
		Files.delete(workspace.path);
	}

	private void fastSort() throws IOException {
		// perform two-element sort in to temp file.
		smallSort();

		// repeatedly merge the blocks from the temp file, to the
		// countWords file, then swap them, and repeat until all sorted.
		FastFile from = workspace;
		FastFile to = source;
		FastFile tmp;
		for (int bs = 2; bs < recordCount; bs <<= 1) {
			mergeSort(from, to, bs);
			tmp = from;
			from = to;
			to = tmp;
		}

		if (to == source) {
			// copy the sorted data back to the source.
			// but only if the last operation was to the temp file.
			source.bufferPrimary.copyAll(workspace.bufferPrimary);
		}
	}

	// Cycle through the source file, and created sorted blocks of 2 members
	// in the temp file.
	private void smallSort() throws IOException {

		final FileBuffer abuf = source.bufferPrimary;
		final FileBuffer bbuf = source.bufferSecondary;
		final FileBuffer outbuf = workspace.bufferPrimary;

		long outpos = 0;
		final long max = (recordCount >>> 1) << 1;
		for (long rec = 0; rec < max; rec += 2) {
			abuf.positionAtRecord(rec);
			bbuf.positionAtRecord(rec + 1);

			int ck = parser.compare(abuf.buffer, bbuf.buffer);
			abuf.positionAtRecord(rec);
			bbuf.positionAtRecord(rec + 1);
			outbuf.positionAtRecord(outpos++);
			if (ck <= 0) {
				outbuf.copyRecord(abuf.buffer);
				outbuf.positionAtRecord(outpos++);
				outbuf.copyRecord(bbuf.buffer);
			} else {
				outbuf.copyRecord(bbuf.buffer);
				outbuf.positionAtRecord(outpos++);
				outbuf.copyRecord(abuf.buffer);
			}
		}

		// for an odd number of source records, migrate the last one.
		if (max < recordCount) {
			abuf.positionAtRecord(max);
			outbuf.positionAtRecord(outpos);
			outbuf.copyRecord(abuf.buffer);
		}

	}

	// Called repeatedly with doubling batchsize and swapped from/to...
	private void mergeSort(final FastFile from, final FastFile to, final int batchSize) throws IOException {

		final FileBuffer buffAlpha = from.bufferPrimary;
		final FileBuffer buffBeta = from.bufferSecondary;
		final FileBuffer buffWriter = to.bufferPrimary;

		long posAlpha = 0;
		long posBeta = batchSize;
		long posWrite = 0;

		while (posWrite < recordCount) {
			final long limitAlpha = Math.min(posBeta, recordCount);
			final long limitBeta = Math.min(limitAlpha + batchSize, recordCount);
			while (posAlpha < limitAlpha && posBeta < limitBeta) {
				buffWriter.positionAtRecord(posWrite++);
				buffAlpha.positionAtRecord(posAlpha);
				buffBeta.positionAtRecord(posBeta);
				if (parser.compare(buffAlpha.buffer, buffBeta.buffer) <= 0) {
					buffAlpha.positionAtRecord(posAlpha++);
					buffWriter.copyRecord(buffAlpha.buffer);
				} else {
					buffBeta.positionAtRecord(posBeta++);
					buffWriter.copyRecord(buffBeta.buffer);
				}
			}
			while (posAlpha < limitAlpha) {
				buffAlpha.positionAtRecord(posAlpha++);
				buffWriter.positionAtRecord(posWrite++);
				buffWriter.copyRecord(buffAlpha.buffer);
			}
			while (posBeta < limitBeta) {
				buffBeta.positionAtRecord(posBeta++);
				buffWriter.positionAtRecord(posWrite++);
				buffWriter.copyRecord(buffBeta.buffer);
			}
			posAlpha = limitBeta;
			posBeta = posAlpha + batchSize;
		}
	}

}
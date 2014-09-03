//package com.janosgyerik.stackoverflow.cc_niazi.downloader;
//
//// Constructor for new download.
//
//import javax.swing.text.Utilities;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.net.URI;
//import java.nio.channels.Channels;
//import java.nio.channels.FileChannel;
//import java.nio.channels.ReadableByteChannel;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.concurrent.atomic.AtomicLongArray;
//
////import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.RandomAccessFile;
//import java.net.URI;
//import java.nio.channels.Channels;
//import java.nio.channels.FileChannel;
//import java.nio.channels.ReadableByteChannel;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.concurrent.atomic.AtomicLong;
//import java.util.concurrent.atomic.AtomicLongArray;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//
//public class Downloader {
//
//	// States:
//// 1 = downloading
//// 2 = completed
//// 0 = paused
//// -1 = failed
//	AtomicInteger currentState = new AtomicInteger(1);
//	String downloadDirectory;
//	URI uri;
//	int segments;
//	String fileName;
//	File fileFile;
//	RandomAccessFile file;
//	CloseableHttpClient client;
//	FileChannel fileChannel;
//	AtomicLong bytesDone;
//	int speed;
//	int avgSpeed[] = {0, 0};
//	AtomicLongArray stateArray;
//	States states;
//	long sizeOfEachSegment;
//	long sizeofFile;
//	ExecutorService threadService;
//	long[] intialState;
//
//	// Constructor
//	public Downloader(String downloadDirectory, URI uri, CloseableHttpClient client,
//					  long[] initialState, long byteDone, int segments, long sizeOfEachSegment) {
//		this.speed = 0;
//
//		this.downloadDirectory = downloadDirectory;
//		this.uri = uri;
//		this.client = client;
//		this.intialState = initialState;
//		this.bytesDone = new AtomicLong(byteDone);
//		this.segments = segments;
//		this.sizeOfEachSegment = sizeOfEachSegment;
//
//		states = States.getInstance();
//		fileName = Utilities.getFileName(uri);
//		fileFile = new File(downloadDirectory + fileName);
//		try {
//			file = new RandomAccessFile(fileFile, "rw");
//		} catch (FileNotFoundException ex) {
//			System.out.println("failed to create file");
//		}
//
//		fileChannel = file.getChannel();
//		stateArray = new AtomicLongArray(intialState);
//		threadService = Executors.newFixedThreadPool(10 + 2);
//	}
//
//	public void startDownload() {
//		threadService.execute(() -> {
//			double currentBytes = bytesDone.doubleValue();
//			//Download each segment independently.
//			for (int i = 0; i < segments; i++) {
//				if (intialState[i] != -1) {
//					threadService.execute(new Segment((i * sizeOfEachSegment)
//							+ intialState[i], (i + 1) * sizeOfEachSegment, i));
//				}
//			}
//
//			if (intialState[segments] != -1) {
//				threadService.execute(new Segment((segments * sizeOfEachSegment)
//						+ intialState[segments], sizeofFile, segments));
//			}
//
//			// Keep saving states of threads. And updating speed.
//			while (bytesDone.get() < sizeofFile) {
//				for (int i = 0; i < 1; i++) {
//					try {
//						Thread.sleep(5000);
//					} catch (InterruptedException ex) {
//						System.out.println("thread interupted while sleeping");
//					}
//
//					System.out.println(speed
//							= (int) ((bytesDone.doubleValue() - currentBytes) / 5120));
//					currentBytes = bytesDone.doubleValue();
//					avgSpeed[0] += speed;
//					avgSpeed[1]++;
//
//				}
//				states.saveState(stateArray, currentState);
//			}
//			// Download Complete.
//			try {
//				fileChannel.close();
//				file.close();
//			} catch (IOException ex) {
//				System.out.println("failed to close file");
//			}
//			currentState.set(2);
//			states.saveState(stateArray, currentState);
//			System.out.println("Alhamdullilah Done :)");
//			System.out.println("Average Speed : " + avgSpeed[0] / avgSpeed[1]);
//		});
//	}
//
//	public class Segment implements Runnable {
//
//		long start;
//		long end;
//		long delta;
//		int name;
//
//		public Segment(long start, long end, int name) {
//			this.start = start;
//			this.end = end;
//			this.name = name;
//		}
//
//		@Override
//		public void run() {
//			try {
//				HttpGet get = new HttpGet(uri);
//				// Range header for defining which segment of file we want to receive.
//				String byteRange = start + "-" + end;
//				get.setHeader("Range", "bytes=" + byteRange);
//				try (CloseableHttpResponse response = client.execute(get)) {
//					ReadableByteChannel inputChannel = Channels.newChannel(
//							response.getEntity().getContent());
//
//					while (start < end && currentState.get() == 1) {
//						delta = fileChannel.transferFrom(inputChannel, start, 81920);
//						start += delta;
//						bytesDone.addAndGet(delta);
//						stateArray.set(name, start);
//					}
//
//					stateArray.set(name, -1);
//				}
//				System.out.println("Thread done: " + name);
//
//			} catch (IOException ex) {
//				System.out.println("thread " + name + " failed to download");
//
//			}
//		}
//	}
//}
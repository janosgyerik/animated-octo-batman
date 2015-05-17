package com.janosgyerik.codereview.ambigram_maker;


import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This is a utility class that provides methods for extraction of data from
 * ZIP (and GZIP) files whilst making use of the {@linkplain java.util.zip}
 * package. All the public methods in this class are static and have the same
 * name: {@code unzip()}. Sample usage is as below:
 * <pre>
 * ...
 * String src = "(FULL path of ZIP file)";
 * String dst = "(FULL path of destination folder)";
 * try {
 *     UnZipper.unzip(src, dst);
 * } catch(IOException e) {
 *     e.printStackTrace();
 * }
 * ...
 * </pre>
 * <p>
 * <b>NOTE:</b>
 * <i>If the specified output directory is present, it will replace the
 * existing one and create a new one.</i>
 * This class can also be used to extract JAR and EPUB files.
 * </p>
 *
 * @author Subhomoy Haldar
 * @version 1.0
 */
public final class UnZipper {

    /**
     * <i>Demonstration.</i>
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Sample usage:\n" +
                    "java UnZipper (full source file path) " +
                    "(full destination folder path");
            return;
        }
        String src = args[0];
        String dst = args[1];
        try {
            UnZipper.unzip(src, dst, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The maximum number of bytes read per iteration from the ZipInputStream.
     */
    private static final int BUFFER_SIZE = 4096; // in bytes

    // Prevent instantiation
    private UnZipper() {
    }

    /**
     * This method is used to unzip a ZIP file specified by the <i>full</i>
     * path given in the first {@code String} to a directory, whose
     * <i>full</i> path is given in the second {@code String}.
     * <p>
     * The <b>default charset</b> of the system is utilized.
     * </p>
     *
     * @param source      The <i>full</i> path of the ZIP (or JAR or EPUB) file.
     * @param destination The <i>full</i> path of the destination folder.
     * @throws IOException If a read/write error occurs.
     * @see Charset#defaultCharset()
     */
    public static void unzip(String source, String destination) throws IOException {
        unzip(new File(source), new File(destination));
    }

    /**
     * This method is used to unzip a ZIP file specified by the <i>full</i>
     * path given in the first {@code String} to a directory, whose
     * <i>full</i> path is given in the second {@code String}, whilst making
     * use of the {@code Charset} specified.
     *
     * @param source      The <i>full</i> path of the ZIP (or JAR or EPUB) file.
     * @param destination The <i>full</i> path of the destination folder.
     * @param charset     The {@code Charset} to be used during extraction.
     * @throws IOException IOException If a read/write error occurs.
     * @see Charset
     * @see Charset#forName(String)
     */
    public static void unzip(String source, String destination, Charset charset) throws IOException {
        unzip(new File(source), new File(destination), charset);
    }

    /**
     * This method is used to extract the ZIP file represented by the first
     * argument. The second parameter is the File that represents the
     * destination directory.
     * <p>
     * The <b>default charset</b> of the system is utilized.
     * </p>
     *
     * @param source      The {@code File} representing the ZIP file.
     * @param destination The {@code File} representing the destination folder.
     * @throws IOException If a read/write error occurs.
     */
    public static void unzip(File source, File destination) throws IOException {
        unzip(source, destination, Charset.defaultCharset());
    }

    /**
     * This method is used to extract the ZIP file represented by the first
     * argument. The second parameter is the File that represents the
     * destination directory. The given charset is used during extraction.
     *
     * @param source      The {@code File} representing the ZIP file.
     * @param destination The {@code File} representing the destination folder.
     * @param charset     The {@code Charset} to be used during extraction.
     * @throws IOException If a read/write error occurs.
     */
    public static void unzip(File source, File destination, Charset charset) throws IOException {
        try (ZipInputStream zipIn = new ZipInputStream(new FileInputStream(source), charset)) {
            ZipEntry entry = zipIn.getNextEntry();
            while (entry != null) {
                File file = new File(destination, entry.getName());
                if (entry.isDirectory()) {
                    file.mkdirs();
                } else {
                    File parent = file.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdirs();
                    }
                    extractFile(zipIn, file);
                }
                zipIn.closeEntry();
                entry = zipIn.getNextEntry();
            }
        }
    }

    /**
     * This method extracts a file in a ZIP archive to the given destination
     * file.
     *
     * @param zipIn The ZipInputStream (source).
     * @param file  The File (destination).
     * @throws IOException If read/write error occurs.
     */
    private static void extractFile(ZipInputStream zipIn, File file) throws IOException {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file))) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int location;
            while ((location = zipIn.read(buffer)) != -1) {
                outputStream.write(buffer, 0, location);
            }
        }
    }
}
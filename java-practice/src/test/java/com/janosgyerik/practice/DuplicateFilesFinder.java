package com.janosgyerik.practice;

import com.janosgyerik.examples.util.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class DuplicateFilesFinder {

    private DuplicateFilesFinder() {
        // utility class, forbidden constructor
    }

    public static void main(String[] args) throws IOException {
        for (String arg : args) {
            findDuplicateFiles(new File(arg)).forEach(set -> {
                set.forEach(System.out::println);
                System.out.println();
            });
        }
    }

    private static class Finder extends SimpleFileVisitor<Path> {
        private final List<File> files = new ArrayList<>();

        @Override
        public FileVisitResult visitFile(Path path, BasicFileAttributes attrs) throws IOException {
            files.add(path.toFile());
            return FileVisitResult.CONTINUE;
        }
    }

    public static Collection<Set<File>> findDuplicateFiles(File basedir) throws IOException {
        List<File> filesList = findFiles(basedir);
        File[] files = filesList.toArray(new File[filesList.size()]);

        DuplicateTracker<File> tracker = new DuplicateTracker<>();
        mergeSort(files, 0, files.length, tracker);

        return tracker.getDuplicates();
    }

    private static List<File> findFiles(File basedir) throws IOException {
        Finder finder = new Finder();
        Files.walkFileTree(Paths.get(basedir.getAbsolutePath()), finder);
        return finder.files;
    }

    private static void mergeSort(File[] files, int start, int end, DuplicateTracker<File> tracker) {
        int diff = end - start;
        if (diff < 2) {
            return;
        }

        int mid = start + diff / 2;
        mergeSort(files, start, mid, tracker);
        mergeSort(files, mid, end, tracker);
        merge(files, start, mid, end, tracker);
    }

    private static void merge(File[] files, int start, int mid, int end, DuplicateTracker<File> tracker) {
        int i = start;
        int j = mid;
        int k = 0;
        File[] merged = new File[end - start];

        while (i < mid && j < end) {
            int cmp = compare(files[i], files[j]);
            if (cmp <= 0) {
                if (cmp == 0) {
                    tracker.add(files[i], files[j]);
                }
                merged[k++] = files[i++];
            } else {
                merged[k++] = files[j++];
            }
        }
        while (i < mid) {
            merged[k++] = files[i++];
        }
        while (j < end) {
            merged[k++] = files[j++];
        }

        System.arraycopy(merged, 0, files, start, merged.length);
    }

    private static int compare(File file1, File file2) {
        int cmp = compareSizes(file1, file2);
        if (cmp != 0) {
            return cmp;
        }

        return compareContent(file1, file2);
    }

    private static int compareSizes(File file1, File file2) {
        return Long.compare(file1.length(), file2.length());
    }

    /**
     * Return 0 if the content of the two files match.
     * Otherwise return non-zero. Doesn't matter if positive or negative,
     * as long as it is a consistent ordering of the files,
     * not necessarily by content.
     *
     * @param file1 first file
     * @param file2 second file
     * @return 0 if the files have identical content, otherwise non-zero
     */
    private static int compareContent(File file1, File file2) {
        try {
            if (IOUtils.contentEquals(file1, file2)) {
                return 0;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file1.compareTo(file2);
    }
}

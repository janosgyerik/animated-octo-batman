package com.janosgyerik.practice;

import com.janosgyerik.examples.util.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class FindDuplicateFiles {

    private FindDuplicateFiles() {
        // utility class, forbidden constructor
    }

    private static class Finder extends SimpleFileVisitor<Path> {
        private final List<Path> files = new ArrayList<>();

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            files.add(file);
            return FileVisitResult.CONTINUE;
        }
    }

    public static List<Path> findFiles(File basedir) throws IOException {
        Finder finder = new Finder();
        Files.walkFileTree(Paths.get(basedir.getAbsolutePath()), finder);
        return finder.files;
    }

    public static Set<Set<Path>> findDuplicates(File basedir) throws IOException {
        List<Path> filesList = findFiles(basedir);
        Path[] files = filesList.toArray(new Path[filesList.size()]);

        DupTracker<Path> tracker = new DupTracker<>();
        mergeSort(files, 0, files.length, tracker);

        return tracker.getDups();
    }

    private static void mergeSort(Path[] files, int start, int end, DupTracker<Path> tracker) {
        int diff = end - start;
        if (diff < 2) {
            return;
        }

        int mid = start + diff / 2;
        mergeSort(files, start, mid, tracker);
        mergeSort(files, mid, end, tracker);
        merge(files, start, mid, end, tracker);
    }

    private static void merge(Path[] files, int start, int mid, int end, DupTracker<Path> tracker) {
        int i = start;
        int j = mid;
        int k = 0;
        Path[] merged = new Path[end - start];

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

    private static int compare(Path file1, Path file2) {
        int cmp = file1.getFileName().compareTo(file2.getFileName());
        if (cmp != 0) {
            return cmp;
        }
//        System.out.println(file1.s);
        return 0;
    }

    private static class DupTracker<T> {
        private Map<T, Set<T>> dups = new HashMap<>();

        private void add(T item1, T item2) {
            Set<T> dups1 = dups.get(item1);
            Set<T> dups2 = dups.get(item2);

            if (dups1 != null && dups2 != null) {
                dups1.addAll(dups2);
                dups.put(item2, dups1);
            } else if (dups1 != null) {
                dups1.add(item2);
                dups.put(item2, dups1);
            } else if (dups2 != null) {
                dups2.add(item1);
                dups.put(item1, dups2);
            } else {
                dups1 = new HashSet<>();
                dups1.add(item1);
                dups1.add(item2);
                dups.put(item1, dups1);
                dups.put(item2, dups1);
            }
        }

        private Set<Set<T>> getDups() {
            return new HashSet<>(dups.values());
        }
    }
}

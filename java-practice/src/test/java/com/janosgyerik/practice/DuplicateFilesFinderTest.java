package com.janosgyerik.practice;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;

import static com.janosgyerik.practice.DuplicateFilesFinder.findDuplicateFiles;

public class DuplicateFilesFinderTest {

    @Test
    public void test() throws IOException {
        Collection<Set<File>> dups = findDuplicateFiles(new File("/Users/janos/dev/git/github/java-examples/tmp"));
        dups.forEach(System.out::println);
    }

}

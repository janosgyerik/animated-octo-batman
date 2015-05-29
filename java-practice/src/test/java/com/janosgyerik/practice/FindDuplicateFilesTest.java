package com.janosgyerik.practice;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Set;

import static com.janosgyerik.practice.FindDuplicateFiles.findDuplicates;

public class FindDuplicateFilesTest {

    @Test
    public void test() throws IOException {
        Set<Set<File>> dups = findDuplicates(new File("/Users/janos/dev/git/github/java-examples/tmp"));
        for (Set<File> dup : dups) {
            System.out.println(dup);
        }
    }

}

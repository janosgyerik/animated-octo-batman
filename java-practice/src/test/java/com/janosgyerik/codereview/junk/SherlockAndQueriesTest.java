package com.janosgyerik.codereview.junk;

import org.junit.Test;

import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

public class SherlockAndQueriesTest {
    @Test
    public void testBasicExample() {
        Scanner scanner = new Scanner("4 3\n" +
                "1 2 3 4\n" +
                "1 2 3\n" +
                "13 29 71");
        assertEquals("13 754 2769 1508 ", Solution.solve(scanner));
    }

    @Test
    public void testBlah() {
        for (int width = 3; width < 11; ++width) {
            for (int coordinate = -10; coordinate < 21; ++coordinate) {
                int x1 = (coordinate % width + width) % width;
                int x2 = ((coordinate % width) + width) % width;
                assertEquals(x1, x2);
            }
        }
        SortedSet<String> sortedSet = new TreeSet<String>();
    }
}

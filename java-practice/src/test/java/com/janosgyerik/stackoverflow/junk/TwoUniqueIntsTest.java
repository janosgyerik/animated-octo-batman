package com.janosgyerik.stackoverflow.junk;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TwoUniqueIntsTest {
    public static List<Integer> findUniqueInts(int[] ints) {
        int[] res = new int[2];
        int resIdx = 0;

        for (int i = 0; i < ints.length && resIdx < 2; ) {
            int current = ints[i];
            int next = i + 1 < ints.length ? ints[i+1] : current - 1;
            if (current != next) {
                res[resIdx++] = current;
                ++i;
            } else {
                i += 2;
            }
        }

        List<Integer> solution = new ArrayList<Integer>();
        for (int i : res) {
            solution.add(i);
        }
        return solution;
    }

    public static List<Integer> findUniqueInts2(int[] src) {
        int[] res = new int[2];
        int resIdx = 0;

        for (int i = 0; i < src.length; ) {
            if (i == src.length - 1 || src[i] != src[i + 1]) {
                res[resIdx++] = src[i++];
                if (resIdx == 2) break;
            } else {
                i += 2;
            }
        }
        List<Integer> solution = new ArrayList<Integer>();
        for (int i : res) {
            solution.add(i);
        }
        return solution;
    }

    @Test
    public void testExample() {
        assertEquals(Arrays.asList(2, 5), findUniqueInts(new int[]{1, 1, 2, 3, 3, 4, 4, 5}));
        assertEquals(Arrays.asList(2, 5), findUniqueInts(new int[]{1, 1, 2, 3, 3, 4, 4, 5, 6, 6}));
        assertEquals(Arrays.asList(2, 5), findUniqueInts(new int[]{2, 3, 3, 4, 4, 5, 6, 6}));
        assertEquals(Arrays.asList(2, 5), findUniqueInts(new int[]{2, 3, 3, 4, 4, 5}));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testBadExample() {
        findUniqueInts(new int[]{1, 1, 2, 3, 4});
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void testBadExampleWithManyUnique() {
        findUniqueInts(new int[]{1, 1, 2, 3, 4, 5, 6, 7});
    }
}


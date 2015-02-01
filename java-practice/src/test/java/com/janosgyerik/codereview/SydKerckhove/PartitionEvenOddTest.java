package com.janosgyerik.codereview.SydKerckhove;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class PartitionEvenOddTest {
    // linear time, stable, not in-place
    private static void partition2(int[] arr) {
        int front = 0;

        // to make it stable, figure out where to start placing the last ones
        int back = 0;
        for (int item : arr) {
            if (item % 2 == 0)
                back++;
        }

        int[] tmp = new int[arr.length];

        for (int item : arr) {
            if (item % 2 == 0) {
                tmp[front++] = item;
            } else { // not even -> must be uneven.
                tmp[back++] = item;
            }
        }

        System.arraycopy(tmp, 0, arr, 0, arr.length);
    }

    private static void partition2alt(int[] arr) {
        int[] tmp = new int[arr.length];

        int front = 0;
        int back = 0;

        for (int item : arr) {
            if (item % 2 == 0) {
                arr[front++] = item;
            } else {
                tmp[back++] = item;
            }
        }

        System.arraycopy(tmp, 0, arr, front, back);
    }

    int[] partition(int[] orig) {
        int[] arr = orig.clone();
        partition2alt(arr);
        return arr;
    }

    @Test
    public void testStable() {
        int[] orig = {1, 8, 3, 2, 9, 6, 7, 5, 4};
        int[] partitioned = {8, 2, 6, 4, 1, 3, 9, 7, 5};
        assertArrayEquals(partitioned, partition(orig));
    }

    @Test
    public void testStable2() {
        int[] orig = {1, 8, 3, 2, 9, 6, 7, 5, 4};
        int[] partitioned = {8, 2, 6, 4, 1, 3, 9, 7, 5};

        int[] arr = orig.clone();
        partition2alt(arr);
        assertArrayEquals(partitioned, arr);
    }
}

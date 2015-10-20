package com.janosgyerik.codereview.randomuser.tictac2;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class RemoveDupsTest {
    @Test
    public void test_1_2_3() {
        int[] orig = {1, 2, 3};
        assertArrayEquals(orig, removeDuplicates(orig.clone()));
    }

    @Test
    public void test_empty() {
        int[] orig = {};
        assertArrayEquals(orig, removeDuplicates(orig.clone()));
    }

    @Test
    public void test_single() {
        int[] orig = {3};
        assertArrayEquals(orig, removeDuplicates(orig.clone()));
    }

    @Test
    public void test_1_1_1() {
        int[] orig = {1, 1, 1};
        assertArrayEquals(new int[]{1}, removeDuplicates(orig.clone()));
    }

    @Test
    public void test_1_1_1_2_2_3_3_3() {
        int[] orig = {1, 1, 1, 2, 2, 3, 3, 3};
        assertArrayEquals(new int[]{1, 2, 3}, removeDuplicates(orig.clone()));
    }

    @Test
    public void test_1_2_3_3_3() {
        int[] orig = {1, 2, 3, 3, 3};
        assertArrayEquals(new int[]{1, 2, 3}, removeDuplicates(orig.clone()));
    }

    @Test
    public void test_1_2_2_2_3() {
        int[] orig = {1, 2, 2, 2, 3};
        assertArrayEquals(new int[]{1, 2, 3}, removeDuplicates(orig.clone()));
    }

    private static int[] removeDuplicates(int[] arr) {
        if (arr.length <= 1) {
            return arr;
        }

        int lastFound = arr[0];

        int currPos = 1;
        for (int i = 1; i < arr.length; ++i) {
            int num = arr[i];
            if (lastFound != num) {
                lastFound = num;
                arr[currPos++] = num;
            }
        }

        return Arrays.copyOf(arr, currPos);
    }
}

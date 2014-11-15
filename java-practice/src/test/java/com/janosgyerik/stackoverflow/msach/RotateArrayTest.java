package com.janosgyerik.stackoverflow.msach;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class RotateArrayTest {
    private static void rotate2(int[] arr, int order) {
        for (int i = 0; i < order; i++) {
            for (int j = arr.length - 1; j > 0; j--) {
                int temp = arr[j];
                arr[j] = arr[j - 1];
                arr[j - 1] = temp;
            }
        }
    }

    private static void rotate(int[] arr, int order) {
        if (arr == null || order < 0) {
            throw new IllegalArgumentException("The array must be non-null and the order must be non-negative");
        }
        int offset = arr.length - order % arr.length;
        if (offset > 0) {
            int[] copy = arr.clone();
            for (int i = 0; i < arr.length; ++i) {
                int j = (i + offset) % arr.length;
                arr[i] = copy[j];
            }
        }
    }

    @Test
    public void testRotateBy2() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] expected = {7, 8, 1, 2, 3, 4, 5, 6};
        rotate(arr, 2);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testRotateBy3() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] expected = {6, 7, 8, 1, 2, 3, 4, 5};
        rotate(arr, 3);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testRotateByLength() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] expected = arr.clone();
        rotate(arr, arr.length);
        assertArrayEquals(expected, arr);
        rotate(arr, arr.length * 3);
        assertArrayEquals(expected, arr);
    }

    @Test
    public void testRotateByZero() {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8};
        int[] expected = arr.clone();
        rotate(arr, 0);
        assertArrayEquals(expected, arr);
    }
}

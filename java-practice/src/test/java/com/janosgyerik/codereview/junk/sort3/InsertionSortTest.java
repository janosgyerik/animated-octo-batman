package com.janosgyerik.codereview.junk.sort3;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;

class InsertionSort {
    public static int[] sort(int[] arr) {
        int value, hole;

        for (int i = 1; i < arr.length; i++) {
            value = arr[i];
            hole = i;

            while (hole > 0 && arr[hole - 1] > value) {
                arr[hole] = arr[hole - 1];
                hole--;
            }

            arr[hole] = value;
        }
        return arr;
    }
}

public class InsertionSortTest {
    private void sort(int[] arr) {
        InsertionSort.sort(arr);
    }

    @Test
    public void testMixedValues() {
        int[] arr = {2, 5, 1, 8, 12, 3, 7};
        sort(arr);
        assertEquals("[1, 2, 3, 5, 7, 8, 12]", Arrays.toString(arr));
    }

    @Test
    public void testDecreasingValues() {
        int[] arr = {4, 3, 2, 1};
        sort(arr);
        assertEquals("[1, 2, 3, 4]", Arrays.toString(arr));
    }

    @Test
    public void testDecreasingWithDups() {
        int[] arr = {4, 3, 2, 1, 2, 3, 2};
        sort(arr);
        assertEquals("[1, 2, 2, 2, 3, 3, 4]", Arrays.toString(arr));
    }

    @Test
    public void testMutation() {
        int[] arr1 = { 9, 4, 6, 2, 1, 7 };
        int[] arr2 = InsertionSort.sort(arr1);
        arr2[3] = -1;
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        Collections.sort(Arrays.asList(1, 2, 3));
    }
}

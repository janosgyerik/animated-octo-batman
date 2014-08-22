package com.janosgyerik.stackoverflow.junk.sort1;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

class BubbleSort {
    static void sort(int[] arr) {
        int length = arr.length;

        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }
}

public class BubbleSortTest {
    @Test
    public void testMixedValues() {
        int[] arr = {2, 5, 1, 8, 12, 3, 7};
        BubbleSort.sort(arr);
        assertEquals("[1, 2, 3, 5, 7, 8, 12]", Arrays.toString(arr));
    }

    @Test
    public void testDecreasingValues() {
        int[] arr = {4, 3, 2, 1};
        BubbleSort.sort(arr);
        assertEquals("[1, 2, 3, 4]", Arrays.toString(arr));
    }

    @Test
    public void testDecreasingWithDups() {
        int[] arr = {4, 3, 2, 1, 2, 3, 2};
        BubbleSort.sort(arr);
        assertEquals("[1, 2, 2, 2, 3, 3, 4]", Arrays.toString(arr));
    }
}

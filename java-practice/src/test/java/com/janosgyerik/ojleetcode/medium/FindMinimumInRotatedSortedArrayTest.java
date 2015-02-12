package com.janosgyerik.ojleetcode.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindMinimumInRotatedSortedArrayTest {
    public int findMin(int[] num) {
        for (int i = 1; i < num.length; ++i) {
            if (num[i] < num[i - 1]) {
                return num[i];
            }
        }
        return num[0];
    }

    @Test
    public void test_4() {
        assertEquals(4, findMin(new int[]{4}));
    }

    @Test
    public void test_1_2_3() {
        assertEquals(1, findMin(new int[]{1, 2, 3}));
    }

    @Test
    public void test_4_5_6_7_0_1_2() {
        assertEquals(0, findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
    }
}

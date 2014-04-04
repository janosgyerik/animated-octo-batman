package com.janosgyerik.practice.medium;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxDoubleSliceSumTest {

    private int solution(int[] ints) {
        int maxSum = 0;
        for (int x = 0; x < ints.length; ++x) {
            for (int y = x + 1; y < ints.length; ++y) {
                for (int z = y + 1; z < ints.length; ++z) {
                    int tmp = sumIntervals(ints, x, y, z);
                    maxSum = Math.max(maxSum, tmp);
                }
            }
        }
        return maxSum;
    }

    private int sumIntervals(int[] ints, int x, int y, int z) {
        return sumIntervals(ints, x, y) + sumIntervals(ints, y, z);
    }

    private int sumIntervals(int[] ints, int x, int y) {
        int sum = 0;
        for (int i = x + 1; i < y; ++i) {
            sum += ints[i];
        }
        return sum;
    }

    @Test
    public void testZeroSum() {
        assertEquals(0, solution(new int[] { 1, 2, 3 }));
    }

    @Test
    public void testSolution() {
        assertEquals(17, solution(new int[] { 3, 2, 6, -1, 4, 5, -1, 2 }));
    }
}

package com.janosgyerik.stackoverflow.CristianGreco;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class LinearCode {
    public static int getMinimumDistance(int[][] generator) {
        int n = generator.length;
        int k = generator[0].length;
        int minDistance = n;
        int maxKString = 1 << k;

        // generate all k-strings
        for (int i = 1; i < maxKString; i++) {
            int distance = 0;

            // matrix multiplication
            for (int[] row : generator) {
                int p = 0;
                for (int z = 0; z < k; z++) {
                    int a = row[k - z - 1];
                    int b = (i >>> z) & 1;
                    p = (p + a * b) & 1;
                }
                // distance as number if 1 bits
                distance += p;
            }

            minDistance = Math.min(minDistance, distance);
        }

        return minDistance;
    }
}

public class LinearCodeTest {
    @Test
    public void testSimple() {
        assertEquals(0, LinearCode.getMinimumDistance(new int[][]{{1, 2}, {3, 4}}));
    }

    @Test
    public void testSampleExercise1() {
        assertEquals(3, LinearCode.getMinimumDistance(new int[][]{
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 0, 1, 0},
                {0, 0, 0, 1},
                {0, 1, 1, 1},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
        }));
    }

    @Test
    public void testSampleExercise2() {
        assertEquals(1, LinearCode.getMinimumDistance(new int[][]{
                {3, 2},
                {1, 1},
                {0, 0},
                {1, 1},
        }));
    }
}

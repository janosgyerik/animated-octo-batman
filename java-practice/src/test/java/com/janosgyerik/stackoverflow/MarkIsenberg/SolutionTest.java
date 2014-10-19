package com.janosgyerik.stackoverflow.MarkIsenberg;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
    public static int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null) {
            return -1;
        }
        int[][] dp = new int[triangle.size()][triangle.size()];
        getMin(triangle, 0, 0, dp);
        return dp[0][0];
    }

    public static int getMin(List<List<Integer>> triangle, int level, int index, int[][] dp) {
        if (level == triangle.size() - 1) {
            return triangle.get(level).get(index);
        }

        int left = dp[level + 1][index];
        if (left == 0) {
            left = getMin(triangle, level + 1, index, dp);
        }

        int right = dp[level + 1][index + 1];
        if (right == 0) {
            right = getMin(triangle, level + 1, index + 1, dp);
        }

        dp[level][index] = triangle.get(level).get(index) + Math.min(left, right);
        return dp[level][index];
    }

    public static int solution(List<List<Integer>> triangle) {
//        return minimumTotal(triangle);
        assert !triangle.isEmpty();
        assert !triangle.get(0).isEmpty();
//        return getMin(triangle, 0, 0, 0);
        return getMin(triangle, 0, 0);
    }

    public static int getMin(List<List<Integer>> triangle, int level, int index) {
        if (level == triangle.size()) {
            return 0;
        }
        int current = triangle.get(level).get(index);
        return Math.min(
                current + getMin(triangle, level + 1, index),
                current + getMin(triangle, level + 1, index + 1)
        );
    }

    public static int getMin(List<List<Integer>> triangle, int level, int index, int accum) {
        if (level == triangle.size()) {
            return accum;
        }
        int newAccum = accum + triangle.get(level).get(index);
        return Math.min(
                getMin(triangle, level + 1, index, newAccum),
                getMin(triangle, level + 1, index + 1, newAccum)
                );
    }

        /*
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
         */
    @Test
    public void test1Level() {
        assertEquals(5, solution(Arrays.asList(Arrays.asList(5))));
    }

    @Test
    public void test2Levels() {
        assertEquals(5, solution(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4))));
    }

    @Test
    public void test3Levels() {
        assertEquals(10, solution(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7))));
    }

    @Test
    public void test4Levels() {
        assertEquals(11, solution(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7), Arrays.asList(4, 1, 8, 3))));
    }

    @Test
    public void test4Levels_2() {
        assertEquals(15, solution(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7), Arrays.asList(4, 11, 8, 3))));
    }

    @Test
    public void test4Levels_3() {
        assertEquals(16, solution(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7), Arrays.asList(5, 11, 8, 13))));
    }

    @Test
    public void test4Levels_4() {
        assertEquals(18, solution(Arrays.asList(Arrays.asList(2), Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7), Arrays.asList(15, 11, 8, 13))));
    }
}

package com.janosgyerik.ojleetcode.medium;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TriangleTest {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        for (int rowIndex = triangle.size() - 2; rowIndex >= 0; --rowIndex) {
            List<Integer> currentRow = triangle.get(rowIndex);
            List<Integer> nextRow = triangle.get(rowIndex + 1);
            for (int colIndex = 0; colIndex <= rowIndex; ++colIndex) {
                currentRow.set(colIndex, currentRow.get(colIndex) + Math.min(nextRow.get(colIndex), nextRow.get(colIndex + 1)));
            }
        }
        return triangle.get(0).get(0);
    }

    public int minimumTotal_Greedy(List<List<Integer>> triangle) {
        if (triangle.isEmpty()) {
            return 0;
        }
        int col = 0;
        int sum = triangle.get(0).get(0);
        for (int i = 1; i < triangle.size(); ++i) {
            List<Integer> row = triangle.get(i);
            int col1 = row.get(col);
            int col2 = row.get(col + 1);
            if (col1 < col2) {
                sum += col1;
            } else {
                sum += col2;
                ++col;
            }
        }
        return sum;
    }

    @Test
    public void testEmpty() {
        assertEquals(0, minimumTotal(Arrays.asList()));
    }

    @Test
    public void testSingleton() {
        assertEquals(2, minimumTotal(Arrays.asList(Arrays.asList(2))));
    }

    @Test
    public void testExample1() {
        assertEquals(11, minimumTotal(Arrays.asList(
                Arrays.asList(2),
                Arrays.asList(3, 4),
                Arrays.asList(6, 5, 7),
                Arrays.asList(4, 1, 8, 3)
        )));
    }

    @Test
    public void testExample2() {
        assertEquals(16, minimumTotal(Arrays.asList(
                Arrays.asList(     2),
                Arrays.asList(    3, 4),
                Arrays.asList(  1, 6, 7),
                Arrays.asList(14, 11, 8, 3)
        )));
    }
}

package com.janosgyerik.stackoverflow.malachi;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ReverseMatrixTest {
    @Test
    public void test3by3() {
        assertArrayEquals(new int[][]{
                {9, 8, 7},
                {6, 5, 4},
                {3, 2, 1},},
                reverse(new int[][]{
                        {1, 2, 3},
                        {4, 5, 6},
                        {7, 8, 9}
                }));
    }

    private int[][] reverse(int[][] orig) {
        int[][] result = new int[orig.length][orig[0].length];
        for (int x = 0, i = orig.length - 1; i >= 0; i--, x++) {
            for (int y = 0, j = orig[0].length - 1; j >= 0; j--, y++) {
                result[x][y] = orig[i][j];
            }
        }
        return result;
    }

    private int[][] reverse0(int[][] inputMatrix) {
        int[][] outputMatrix = new int[inputMatrix.length][inputMatrix[0].length];
        int x = 0;
        for (int i = inputMatrix.length - 1; i >= 0; i--) {
            int y = 0;
            for (int j = inputMatrix[0].length - 1; j >= 0; j--) {
                outputMatrix[x][y] = inputMatrix[i][j];
                y++;
            } x++;
        }
        return outputMatrix;
    }
}

package com.janosgyerik.stackoverflow.qed;

import org.junit.Test;

import java.util.*;

class PrintMatrix {
    public static <T> void printma(List<List<T>> matrix) {
        int nCol = matrix.get(0).size();
        for (int i = 1; i < matrix.size(); ++i) {
            if (nCol != matrix.get(i).size()) {
                throw new IllegalArgumentException("This is not a matrix.");
            }
        }

        for (List<T> row : matrix) {
            for (T value : row) {
                System.out.printf("%s\t", value);
            }
            System.out.println();
        }
    }
}

public class PrintMatrixTest {
    @Test(expected = IllegalArgumentException.class)
    public void test() {
        PrintMatrix.printma(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3, 4)));
    }

    @Test
    public void testOk() {
        PrintMatrix.printma(Arrays.asList(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3)));
    }
}

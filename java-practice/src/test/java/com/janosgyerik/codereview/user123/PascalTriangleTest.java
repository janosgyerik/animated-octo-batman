package com.janosgyerik.codereview.user123;

import org.junit.Test;

import java.util.Scanner;

class PascalTriangleYar {
    public void printArray(int[][] xArray) {
        for (int[] row : xArray) {
            System.out.println();
            for (int item : row) {
                if (item != 0) {
                    System.out.print(item);
                }
                System.out.print("\t");
            }
        }
    }

    public void pTriangle(int r) {
        int[][] xArr = new int[r][2 * r - 1];
        xArr[0][r - 1] = 1;
        p2T(xArr, r, 1);
        printArray(xArr);
    }

    private void p2T(int[][] xArr, int r, int n) {
        int p = r - n - 1;

        xArr[n][p] = 1;

        for (int fd = 1; fd < xArr[0].length - 1; fd++) {
            xArr[n][fd] = xArr[n - 1][fd - 1] + xArr[n - 1][fd + 1];
            xArr[r - 1][2 * r - 2] = 1;
        }

        if (n < r - 1) {
            p2T(xArr, r, n + 1);
        }
    }

    public static void main(String[] args) {
        PascalTriangleYar tri = new PascalTriangleYar();
        Scanner inputReader = new Scanner(System.in);
        System.out.println("Enter a number (x). A pascal's triangle of x rows will be printed.");
        int x = inputReader.nextInt();
        tri.pTriangle(x);
    }
}

public class PascalTriangleTest {
    @Test
    public void test() {
        new PascalTriangleYar().pTriangle(3);
        new PascalTriangleYar().pTriangle(4);
        //        new PascalTriangleYar().pTriangle(5);
    }
}

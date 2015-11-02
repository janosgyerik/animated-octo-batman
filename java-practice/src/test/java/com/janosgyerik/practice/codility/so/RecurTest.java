package com.janosgyerik.practice.codility.so;

public class RecurTest {
    public static void main(String[] args) {

        printit(3);

    }

    private static int printit(int n) {

        if (n > 0) {
            printit(--n);
        }

        System.out.print(n + ",");

        return n;
    }
}

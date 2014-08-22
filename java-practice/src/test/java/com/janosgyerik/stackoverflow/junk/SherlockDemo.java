package com.janosgyerik.stackoverflow.junk;

import java.io.IOException;
import java.util.Scanner;

// 9 10 12 13 -> 14 points
// with scanner: 6 9 10 11 12 13 14
class Solution {
    private static final int MODULO = (int) 1e9 + 7;

    public static void main(String[] args) throws IOException {
        System.out.print(solve(new Scanner(System.in)));
    }

    public static String solve(Scanner scanner) {
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        long[] A = new long[N];
        int[] B = new int[M];
        int[] C = new int[M];
        for (int i = 0; i < N; i++) A[i] = scanner.nextInt();
        for (int i = 0; i < M; i++) B[i] = scanner.nextInt();
        for (int i = 0; i < M; i++) C[i] = scanner.nextInt();

        for (int i = 0; i < M; i++) {
            for (int j = B[i] - 1; j < N; j += B[i]) {
                System.out.println(String.format("A[%d] *= C[%d]", j, i));
                A[j] = (A[j] * C[i]) % MODULO;
            }
        }
        StringBuilder sb = new StringBuilder(N * 11);
        for (long num : A) {
            sb.append(num).append(" ");
        }
        return sb.toString();
    }
}
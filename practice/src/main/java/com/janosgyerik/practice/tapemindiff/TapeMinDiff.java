package com.janosgyerik.practice.tapemindiff;

public class TapeMinDiff {
    public int minDiff(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int diff = sum;
        for (int i = 0; i < arr.length - 1; ++i) {
            int newdiff = Math.abs(diff - 2 * arr[i]);
            if (newdiff < diff) {
                diff = newdiff;
            } else {
                break;
            }
        }
        return diff;
    }

    public int submit(int[] arr) {
        return minDiff(arr);
    }
}

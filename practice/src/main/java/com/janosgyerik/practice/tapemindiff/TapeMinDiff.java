package com.janosgyerik.practice.tapemindiff;

public class TapeMinDiff {
    public int minDiff(int[] arr) {
        int sum = 0;
        for (int i : arr) {
            sum += i;
        }
        int mindiff = Math.abs(sum);
        int acc = 0;
        for (int i = 0; i < arr.length - 1; ++i) {
            acc += arr[i];
            sum -= arr[i];
            int diff = Math.abs(acc - sum);
            if (diff < mindiff) {
                mindiff = diff;
            }
        }
        return mindiff;
    }

    public int submit(int[] arr) {
        return minDiff(arr);
    }
}

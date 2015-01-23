package com.janosgyerik.codereview.mannymeng;

import org.junit.Test;

public class Euler7Test {
    @Test
    public void testOrig() {
        long time = System.nanoTime();
        int result = getNthPrime(10001);
        time = System.nanoTime() - time;
        System.out.println("Result: " + result + "\nTime used to calculate in nanoseconds: " + time);
    }

    @Test
    public void testNew() {
        long time = System.nanoTime();
        int result = getNthPrime2(10001);
        time = System.nanoTime() - time;
        System.out.println("Result: " + result + "\nTime used to calculate in nanoseconds: " + time);
    }

    private int getNthPrime(int n) {
        int max = (int) (1.4 * n * Math.log(n));
        boolean[] isPrimeArray = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            isPrimeArray[i] = true;
        }
        for (int i = 2; i * i <= max; i++) {
            if (isPrimeArray[i]) {
                for (int j = i; i * j <= max; j++) {
                    isPrimeArray[i * j] = false;
                }
            }
        }
        // Find the nth prime
        int nthPrime = 0;
        int index = 0;
        for (boolean isPrime : isPrimeArray) {
            if (isPrime) {
                nthPrime++;
            }
            if (nthPrime == n) {
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("n is not valid");
    }

    private int getNthPrime2(int n) {
        int max = (int) (1.4 * n * Math.log(n));
        boolean[] isPrimeArray = new boolean[max + 1];
        for (int i = 2; i <= max; i++) {
            isPrimeArray[i] = true;
        }
        for (int i = 2; i * i <= max; i++) {
            if (isPrimeArray[i]) {
                for (int j = i; i * j <= max; j++) {
                    isPrimeArray[i * j] = false;
                }
            }
        }
        // Find the nth prime
        int nthPrime = 0;
        int index = 0;
        for (boolean isPrime : isPrimeArray) {
            if (isPrime) {
                nthPrime++;
            }
            if (nthPrime == n) {
                return index;
            }
            index++;
        }
        throw new IllegalArgumentException("n is not valid");
    }
}

package com.janosgyerik.codereview.mannymeng;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.LongStream;

import static org.junit.Assert.assertEquals;

public class Euler5Test {
    Set<Integer> getUniqueFactors(int max) {
        Set<Integer> factors = new HashSet<>();
        for (int i = 2; i <= max; ++i) {
            for (int factor : factors) {
                if (i % factor == 0) {
                    factors.remove(factor);
                    break;
                }
            }
            factors.add(i);
        }
        return factors;
    }

    @Test
    public void testGetUniqueFactors() {
        assertEquals("[6, 7, 8, 9, 10]", getUniqueFactors(10).toString());
//        assertEquals("[6, 7, 8, 9, 10]", getUniqueFactors(20).toString());
        long product = 1;
        for (int factor : getUniqueFactors(10)) {
            product *= factor;
        }
        System.out.println(product);

//        long result = LongStream.rangeClosed(1, 20)
//                .reduce(Euler5Test::lcm).getAsLong();
//        System.out.println(result);

        int MAX = 20;
        long time = System.nanoTime();
        long result = 11;
        for(long i = result + 1; i <= MAX; i++) {
            result = lcm(result, i);
        }
        time = System.nanoTime() - time;
        System.out.println("Result: " + result + "\nTime used to calculate in nanoseconds: " + time);
    }

    private static long smallestMultiple(long i, long j) {
        for(int index = 2; index <= i && index <= j; index++) {
            if(i % index == 0 && j % index == 0) {
                i /= index;
            }
        }
        return i * j;
    }

    /*Greatest Common Divisor
Euclidean algorithm: http://en.wikipedia.org/wiki/Euclidean_algorithm */
    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
    // Least Common Multiple
    public static long lcm(long a, long b) {
        return a * b / gcd(a, b);
    }
}

package com.janosgyerik.codereview.hosch250;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class NthPrimeTest {
    static int NPrimeGenerator(int n) {
        List<Integer> primes = new ArrayList<>();

        primes.add(2);
        int num = 3;

        TOP: while (true) {
            int sqrtNum = (int) Math.sqrt(num);

            for (int i : primes) {
                if (num % i == 0) {
                    break;
                }

                if (i > sqrtNum) {
                    primes.add(num);
                    if (primes.size() == n) {
                        break TOP;
                    }
                    break;
                }
            }

            num += 2;
        }
        return primes.get(primes.size() - 1);
    }

    @Test
    public void testExample() {
        assertEquals(104743, NPrimeGenerator(10001));
    }
}

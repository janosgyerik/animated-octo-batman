package com.janosgyerik.codereview.kleinfreund;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests a positive integer for primality.
 * Prints out whether a number is prime, composite or neither.
 */
public class PrimalityTest {
    private static void fatal(String message) {
        System.out.println(message);
        System.exit(1);
    }

    private static int getValidPrimeCandidate(String[] args) {
        if (args.length < 1) {
            fatal("Not enough arguments. Please enter a positive integer prime canidate.");
        }

        if (args.length > 1) {
            fatal("Too many arguments. Please enter a positive integer prime canidate.");
        }

        int primeCandidate = 0;
        try {
            primeCandidate = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            fatal("The entered value was not an integer.");
        }

        if (primeCandidate < 0) {
            fatal("Negative numbers are never prime.");
        }
        return primeCandidate;
    }

    public static void main(String[] args) {
        int primeCandidate = getValidPrimeCandidate(args);

        if (primeCandidate < 2) {
            System.out.println(primeCandidate + " is neither prime nor composite.");
            return;
        }
        int result = smallestDivisor(primeCandidate);
        if (result == primeCandidate) {
            System.out.println(primeCandidate + " is prime.");
        } else {
            System.out.println(primeCandidate + " is composite and has the smallest divisor " + result + ".");
        }
    }

    /**
     * Returns the smallest divisor of composite numbers or
     * 1 if the number is 1 (not composite, not prime) or
     * -1 if the number is prime (not composite).
     */
    public static int smallestDivisor(int primeCandidate) {
        if (primeCandidate <= 3) {
            // 2 and 3 are prime.
            return primeCandidate;
        } else if (primeCandidate % 2 == 0) {
            // All numbers greater 2 and divisable by 2 are not prime and the smallest divisor is 2
            return 2;
        } else if (primeCandidate % 3 == 0) {
            // All numbers greater 3 and divisable by 3 are not prime and the smallest divisor is 3
            return 3;
        }
        // Checks divisability of numbers in the form of 6k+1 and 6k-1.
        // It starts with: 5 and 7, 11 and 13, 17 and 19, 23 and 25, ...
        for (int i = 5; i * i <= primeCandidate; i += 6) {
            if (primeCandidate % i == 0) {
                return i;
            } else if (primeCandidate % (i + 2) == 0) {
                return i + 2;
            }
        }
        // No divisor found, the number is prime.
        return primeCandidate;
    }

    @Test
    public void test_0() {
        assertEquals(0, smallestDivisor(0));
    }

    @Test
    public void test_1() {
        assertEquals(1, smallestDivisor(1));
    }

    @Test
    public void test_2() {
        assertEquals(2, smallestDivisor(2));
    }

    @Test
    public void test_3() {
        assertEquals(3, smallestDivisor(3));
    }

    @Test
    public void test_4() {
        assertEquals(2, smallestDivisor(4));
    }

    @Test
    public void test_5() {
        assertEquals(5, smallestDivisor(5));
    }

    @Test
    public void test_6() {
        assertEquals(2, smallestDivisor(6));
    }

    @Test
    public void test_15() {
        assertEquals(3, smallestDivisor(15));
    }

    @Test
    public void test_17() {
        assertEquals(17, smallestDivisor(17));
    }

}

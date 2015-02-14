package com.janosgyerik.codereview.Legato;

import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromicNumberTest {
    private static int reverse(int num) {
        long reversed = 0;
        int work = num;
        while (work != 0) {
            reversed *= 10;
            reversed += work % 10;
            if (reversed > Integer.MAX_VALUE) {
                throw new IllegalArgumentException("Reversing this number will cause integer overflow: " + num);
            }
            work /= 10;
        }
        return (int) reversed;
    }

    private static boolean isPalindromic(int num) {
        int digits = (int) Math.log10(Math.abs(num)) + 1;
        int firstDivisor = (int) Math.pow(10, digits - 1);
        int lastDivisor = 1;
        while (firstDivisor > lastDivisor) {
            int firstDigit = num / firstDivisor % 10;
            int lastDigit = num / lastDivisor % 10;
            if (firstDigit != lastDigit) {
                return false;
            }
            firstDivisor /= 10;
            lastDivisor *= 10;
        }
        return true;
    }

    private static String stepsToPalindromic(int num) {
        int steps = 0;
        int currentNum = num;

        while (!isPalindromic(currentNum)) {
            ++steps;
            currentNum += reverse(currentNum);
        }

        return steps + " " + currentNum;
    }

    @Test
    public void testStepsToPalindromic_8() {
        assertEquals("0 8", stepsToPalindromic(8));
    }

    @Test
    public void testStepsToPalindromic_125() {
        assertEquals("1 646", stepsToPalindromic(125));
    }

    @Test
    public void testStepsToPalindromic_195() {
        assertEquals("4 9339", stepsToPalindromic(195));
    }

    @Test
    public void testStepsToPalindromic_0() {
        assertEquals("0 0", stepsToPalindromic(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStepsToPalindromic_max() {
        stepsToPalindromic(Integer.MAX_VALUE);
    }

    @Test
    public void testStepsToPalindromic_minus1() {
        assertEquals("0 -1", stepsToPalindromic(-1));
    }

    @Test
    public void testStepsToPalindromic_minus195() {
        assertEquals("4 -9339", stepsToPalindromic(-195));
    }

    @Test
    public void testStepsToPalindromic_9998() {
        assertEquals("6 8836388", stepsToPalindromic(9998));
    }

    @Test
    public void testPalindromic_3() {
        assertTrue(isPalindromic(3));
    }

    @Test
    public void testPalindromic_13() {
        assertFalse(isPalindromic(13));
    }

    @Test
    public void testPalindromic_22() {
        assertTrue(isPalindromic(22));
    }

    @Test
    public void testPalindromic_333() {
        assertTrue(isPalindromic(333));
    }

    @Test
    public void testPalindromic_1991() {
        assertTrue(isPalindromic(1991));
    }

    @Test
    public void testPalindromic_334() {
        assertFalse(isPalindromic(334));
    }

    @Test
    public void testPalindromic_minus12321() {
        assertTrue(isPalindromic(-12321));
    }

    @Test
    public void testReverse_3() {
        assertEquals(3, reverse(3));
    }

    @Test
    public void testReverse_34() {
        assertEquals(43, reverse(34));
    }

    @Test
    public void testReverse_41() {
        assertEquals(14, reverse(41));
    }

    @Test
    public void testReverse_123() {
        assertEquals(321, reverse(123));
    }

    @Test
    public void testReverse_100() {
        assertEquals(1, reverse(100));
    }

    @Test
    public void testReverse_123456789() {
        assertEquals(987654321, reverse(123456789));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testReverse_1234567899() {
        reverse(1234567899);
    }
}

package com.janosgyerik.codereview.enderland;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class PalindromeChecker {

    public boolean isPalindrome(int input) {
        String str = Integer.toString(input);
        int len = str.length();

        for (int i = 0; i < Math.ceil(len / 2.0); i++) {
            if (str.charAt(i) != str.charAt(len - i - 1)) {
                return false;
            }
        }

        return true;
    }
}

public class PalindromeCheckerTest {
    private boolean isPalindrome(int num) {
        PalindromeChecker checker = new PalindromeChecker();
        return checker.isPalindrome(num);
    }

    @Test
    public void test10() {
        assertFalse("10 failed", isPalindrome(10));
    }

    @Test
    public void test112() {
        assertFalse("112 failed", isPalindrome(112));
    }

    @Test
    public void test900() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse("900 failed", checker.isPalindrome(900));
    }

    @Test
    public void test1000() {
        PalindromeChecker checker = new PalindromeChecker();
        assertFalse("1000 failed", checker.isPalindrome(1000));
    }

    @Test
    public void test909() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(909));
    }

    @Test
    public void test1001() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(1001));
    }

    @Test
    public void test11() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(11));
    }

    @Test
    public void test111() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(111));
    }

    @Test
    public void testm111() {
        PalindromeChecker checker = new PalindromeChecker();
        assertTrue(checker.isPalindrome(-111));
    }

}
package com.janosgyerik.stackoverflow.winterbash;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeTest {
    public boolean palCheck(String s) {
        String end = "";
        String result = s.replaceAll(" ", "");
        for (int i = result.length() - 1; i >= 0; i--) {
            end += result.charAt(i);
        }

        return result.equalsIgnoreCase(end);
    }

    private boolean isPalindrome(String string) {
        String text = string.replaceAll("\\W+", "").toLowerCase();
        int len = text.length();
        for (int i = 0; i < len / 2; ++i) {
            if (text.charAt(i) != text.charAt(len - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void test_hello_is_not_palindrome() {
        assertFalse(isPalindrome("hello"));
    }

    @Test
    public void test_hello_olleh_is_palindrome() {
        assertTrue(isPalindrome("hello olleh"));
    }

    @Test
    public void test_aba_is_palindrome() {
        assertTrue(isPalindrome("aba"));
    }

    @Test
    public void test_x_palindrome() {
        assertTrue(isPalindrome("x"));
    }

    @Test
    public void test_xx_palindrome() {
        assertTrue(isPalindrome("xx"));
    }

    @Test
    public void test_empty_palindrome() {
        assertTrue(isPalindrome("xax"));
    }

    @Test
    public void test_hello() {
        assertTrue(isPalindrome(""));
    }
}

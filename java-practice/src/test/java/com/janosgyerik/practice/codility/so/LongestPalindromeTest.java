package com.janosgyerik.practice.codility.so;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class LongestPalindromeTest {
    public String longestPalindromeFromCenter(String s, int center, int minLength) {
        if (center == 0) {
            return "";
        }
        if (center == 1) {
            if (s.length() > 1 && s.charAt(0) == s.charAt(1)) {
                return s.substring(0, 2);
            }
            return s.substring(0, 1);
        }
        int longest = minLength;
//        for (int i = longest; center + i < s.length(); ++i) {
//            if (isPalindrome(s, i, longest + 1)) {
//                longest = s.substring(i, longest + 1);
//            } else {
//                ++i;
//            }
//        }
//        return longest;
        return null;
    }

    private boolean isPalindrome(String s, int start, int length) {
        return isPalindrome(s.substring(start, start + length));
    }

    public String longestPalindrome(String s) {
        String longestPalindrome = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = s.length() - 1; j >= 0 && j != i; j--) {
                if (isPalindrome(s.substring(i, j + 1))) {
                    if (s.substring(i, j + 1).length() > longestPalindrome.length()) {
                        longestPalindrome = s.substring(i, j + 1);
                        return longestPalindrome;
                    }
                }
            }
        }
        return longestPalindrome;
    }

    public boolean isPalindrome(String s) {
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void testIsPalindrome() {
        Assert.assertTrue(isPalindrome("", 0, 0));
        Assert.assertTrue(isPalindrome("hello", 1, 1));
        Assert.assertTrue(isPalindrome("hello", 2, 2));
        Assert.assertFalse(isPalindrome("hello", 2, 3));
        Assert.assertFalse(isPalindrome("hello", 1, 3));
        Assert.assertFalse(isPalindrome("abaz"));
        Assert.assertTrue(isPalindrome("abaz", 0, 3));
        Assert.assertFalse(isPalindrome("abaz", 0, 2));
        Assert.assertFalse(isPalindrome("abaz", 0, 4));
    }

    @Test
    public void testBizarreCases() {
        Assert.assertEquals("", longestPalindrome(""));
        Assert.assertEquals(1, longestPalindrome("helo").length());
    }

    @Test
    public void testSimplePalindromes() {
        Assert.assertEquals("ll", longestPalindrome("hello"));
        Assert.assertEquals("aba", longestPalindrome("aba"));
        Assert.assertEquals("aba", longestPalindrome("abaaa"));
        Assert.assertEquals("aaaa", longestPalindrome("abaaaa"));
        Assert.assertEquals("abacaba", longestPalindrome("zabacabaxy"));
    }
}

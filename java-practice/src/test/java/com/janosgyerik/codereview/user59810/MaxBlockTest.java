package com.janosgyerik.codereview.user59810;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MaxBlockTest {

    public int maxBlock(String str) {
        return maxBlock_orig_ish(str);
    }

    public int maxBlock_orig_ish(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        int longest = 0;
        int length = 1;
        for (int i = 0; i < str.length() - 1; i++) {
            if (str.charAt(i) == str.charAt(i + 1)) {
                ++length;
            } else {
                if (length > longest) {
                    longest = length;
                }
                length = 1;
            }
        }
        if (length > longest) {
            longest = length;
        }
        return longest;
    }

    public int maxBlock_mine(String str) {
        if (str.isEmpty()) {
            return 0;
        }
        char[] chars = str.toCharArray();
        char prev = chars[0];
        int longest = 1;
        int len = 1;
        for (int i = 1; i < chars.length; ++i) {
            char current = chars[i];
            if (prev == current) {
                ++len;
            } else {
                prev = current;
                if (len > longest) {
                    longest = len;
                }
                len = 1;
            }
        }
        if (len > longest) {
            longest = len;
        }
        return longest;
    }

    @Test
    public void test_hoopla() {
        assertEquals(2, maxBlock("hoopla"));
    }

    @Test
    public void test_abbCCCddBBBxx() {
        assertEquals(3, maxBlock("abbCCCddBBBxx"));
    }

    @Test
    public void test_empty() {
        assertEquals(0, maxBlock(""));
    }

    @Test
    public void test_xyz() {
        assertEquals(1, maxBlock("xyz"));
    }

    @Test
    public void test_xxyz() {
        assertEquals(2, maxBlock("xxyz"));
    }

    @Test
    public void test_longer_nonrepeating_sequence() {
        assertEquals(1, maxBlock("abcdefghijkl"));
    }

    @Test
    public void test_other() {
        assertEquals(2, maxBlock("xyzz"));
        assertEquals(3, maxBlock("abbbcbbbxbbbx"));
        assertEquals(3, maxBlock("XXBBBbbxx"));
        assertEquals(4, maxBlock("XXBBBBbbxx"));
        assertEquals(4, maxBlock("XXBBBbbxxXXXX"));
        assertEquals(4, maxBlock("XX2222BBBbbXX2222"));
        assertEquals(5, maxBlock("aaaaa"));
        assertEquals(5, maxBlock("xaaaaa"));
        assertEquals(5, maxBlock("aaaaax"));
        assertEquals(2, maxBlock("aaAaa"));
        assertEquals(1, maxBlock("a"));
        assertEquals(2, maxBlock("aa"));
        assertEquals(1, maxBlock("ab"));
    }
}

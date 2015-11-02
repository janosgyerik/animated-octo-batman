package com.janosgyerik.practice.codility.so;

import org.junit.Assert;
import org.junit.Test;

public class LongestCommonPrefixTest {
    @Test
    public void testExample() {
        Assert.assertEquals("aa", findLongestCommonPrefix(new String[]{"aa", "aab", "aaasdd"}));
        Assert.assertEquals("", findLongestCommonPrefix(new String[]{"apple", "pear"}));
        Assert.assertEquals("", findLongestCommonPrefix(new String[]{"apple", "pear", "aaa"}));
    }

    private String findLongestCommonPrefix(String[] strings) {
        String longest = strings[0];
        int length = longest.length();
        for (String str : strings) {
            while (!str.startsWith(longest)) {
                longest = longest.substring(0, --length);
            }
        }
        return longest;
    }
}

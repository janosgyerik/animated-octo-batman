package com.janosgyerik.codereview.TehanFrago;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class Anagram {
    public static boolean isAnagram(String string1, String string2) {
        char[] str1 = string1.toCharArray();
        char[] str2 = string2.toCharArray();

        if (str1.length != str2.length) {
            return false;
        }
        for (char c : str1) {
            int j = 0;
            while (j < str2.length) {
                if (c == str2[j]) {
                    str2[j] = '\0';
                    break;
                }
                j++;
            }
            if (j == str2.length) {
                return false;
            }
        }
        return true;
    }
}

public class AnagramTest {
    @Test
    public void test() {
        assertTrue(Anagram.isAnagram("", ""));
        assertTrue(Anagram.isAnagram("a", "a"));
        assertFalse(Anagram.isAnagram("a", "b"));
        assertFalse(Anagram.isAnagram("a", "aa"));
        assertFalse(Anagram.isAnagram("a", ""));
        assertTrue(Anagram.isAnagram("baa", "aba"));
        assertFalse(Anagram.isAnagram("hello", "hell"));
        assertTrue(Anagram.isAnagram("hello", "olleh"));
        assertFalse(Anagram.isAnagram("hello", "there"));
    }
}

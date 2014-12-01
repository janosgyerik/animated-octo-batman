package com.janosgyerik.stackoverflow.SaraChatila;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HappyStringTest {
    public boolean gHappy(String str) {
        return isHappyChar(str, 'g');
    }

    public boolean isHappyChar(String str, char c) {
        if (str.isEmpty()) {
            return true;
        }
        char[] letters = str.toCharArray();
        if (letters.length == 1) {
            return letters[0] != c;
        }
        for (int i = 0; i < letters.length - 1; i++) {
            char x = letters[i];
            if (x == c) {
                char next = letters[i + 1];
                if (next != c) {
                    return false;
                }
                ++i;
            }
        }
        if (letters[letters.length - 1] == c && letters[letters.length - 2] != c) {
            return false;
        }

        return true;
    }

    @Test
    public void testEmpty() {
        assertTrue(gHappy(""));
    }

    @Test
    public void testSingleLetterG() {
        assertFalse(gHappy("g"));
    }

    @Test
    public void testSingleLetterNotG() {
        assertTrue(gHappy("x"));
    }

    @Test
    public void testTwoLetters() {
        assertTrue(gHappy("gg"));
        assertTrue(gHappy("aa"));
    }

    @Test
    public void testExamples() {
        assertTrue(gHappy("xxggxx"));
        assertFalse(gHappy("xxgxx"));
        assertFalse(gHappy("xxggyygxx"));
    }

    @Test
    public void testLonelyLastG() {
        assertFalse(gHappy("abcg"));
        assertFalse(gHappy("something"));
    }
}

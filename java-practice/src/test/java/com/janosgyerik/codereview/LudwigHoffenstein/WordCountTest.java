package com.janosgyerik.codereview.LudwigHoffenstein;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class WordCountTest {
    public static int countWords(String sentence, int minWordLength) {
        int count = 0;
        for (String part : sentence.trim().split("\\s+")) {
            int letterCount = countLetters(part);
            if (letterCount >= minWordLength) {
                ++count;
            }
        }
        return count;
    }

    public static int countLetters(String word) {
        int count = 0;
        for (char c : word.toCharArray()) {
            if (Character.isLetter(c)) {
                ++count;
            }
        }
        return count;
    }

    @Test
    public void test() {
        assertEquals(3, countWords("hello there jack", 3));
        assertEquals(3, countWords("hello there jack", 4));
        assertEquals(2, countWords("hello there jack", 5));
        assertEquals(0, countWords("hello there jack", 6));
        assertNotEquals(Color.White, Color.Black);
        Color col = null;
    }

    private static enum Color {
        Black, White
    }
}

package com.janosgyerik.codereview.ivocoumans;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KeysTest {
    private void putButton(Map<Character, Integer> keys, String letters, int button) {
        for (char c : letters.toCharArray()) {
            keys.put(c, button);
        }
    }

    public boolean isValid(String line) {
        Map<Character, Integer> keys = new HashMap<>();
        putButton(keys, "abc", 2);
        putButton(keys, "def", 3);
        putButton(keys, "ghi", 4);
        putButton(keys, "jkl", 5);
        putButton(keys, "mno", 6);
        putButton(keys, "pqrs", 7);
        putButton(keys, "tuv", 8);
        putButton(keys, "wxyz", 9);
        putButton(keys, " ", 0);

        int previousButton = -1;

        for (char currentLetter : line.toCharArray()) {
            int currentButton = keys.get(currentLetter);

            if (previousButton == currentButton) {
                return false;
            }

            previousButton = currentButton;
        }

        return true;
    }

    @Test
    public void testHas() {
        assertFalse(isValid("hello"));
        assertFalse(isValid("heloab"));
    }

    @Test
    public void testHasNot() {
        assertTrue(isValid("helo"));
    }
}

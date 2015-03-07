package com.janosgyerik.codereview.Legato;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

class NoRepeat {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner input = new Scanner(new File(args[0]));

        while (input.hasNextLine()) {
            System.out.println(retrieveFirstNonRepeatedLetter(input.nextLine()));
        }
    }

    public static char retrieveFirstNonRepeatedLetter(String input) {
        Map<Character, Boolean> letters = new LinkedHashMap<>();

        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            letters.put(current, !letters.containsKey(current));
        }

        for (Map.Entry<Character, Boolean> entry : letters.entrySet()) {
            if (entry.getValue()) {
                return entry.getKey();
            }
        }

        throw new IllegalStateException("No non-repeated characters");
    }
}

public class FirstAndUniqueTest {
    void assertFirstNonRepeatedLetter(char expected, String string) {
        assertEquals(expected, NoRepeat.retrieveFirstNonRepeatedLetter(string));
    }

    @Test
    public void toothyTest() {
        assertFirstNonRepeatedLetter('h', "tooth");
    }

    @Test
    public void smellyTest() {
        assertFirstNonRepeatedLetter('d', "odor");
    }

    @Test(expected = IllegalStateException.class)
    public void nothingUnique() {
        NoRepeat.retrieveFirstNonRepeatedLetter("abab");
    }
}
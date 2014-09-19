package com.janosgyerik.stackoverflow.user3904388;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

class Key {

    private final int[] possibleNumbers = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private final char[] possibleLetters = "ABCDEFGHIJKLMOPQRSTUVWXYZ".toCharArray();

    private final Random random;
    private final int targetLength;

    public Key(int targetLength) {
        this(targetLength, new Random());
    }

    public Key(int targetLength, Random random) {
        this.random = random;
        this.targetLength = targetLength;
    }

    public String createKey() {
        int letterWeighting = determineLetterWeigthing();
        int numberWeighting = determineNumberWeigthing();

        int[] chosenNumbers = selectNumbers();
        char[] chosenLetters = selectLetters();

        StringBuilder key = new StringBuilder();

        while (key.length() < targetLength) {
            for (int j = 0; j < letterWeighting && key.length() < targetLength; j++) {
                int randomIndex = random.nextInt(chosenLetters.length - 1);
                key.append(chosenLetters[randomIndex]);
            }
            for (int k = 0; k < numberWeighting && key.length() < targetLength; k++) {
                int randomIndex = random.nextInt(chosenNumbers.length - 1);
                key.append(chosenNumbers[randomIndex]);
            }
        }
        return key.toString();
    }

    private char[] selectLetters() {
        char[] chosenLetters = new char[10];

        for (int j = 0; j < chosenLetters.length; j++) {
            int randomIndex = random.nextInt(possibleLetters.length);
            chosenLetters[j] = possibleLetters[randomIndex];
        }

        return chosenLetters;
    }

    private int[] selectNumbers() {
        int[] chosenNumbers = new int[10];

        for (int j = 0; j < chosenNumbers.length; j++) {
            int randomIndex = random.nextInt(possibleNumbers.length);
            chosenNumbers[j] = possibleNumbers[randomIndex];
        }

        return chosenNumbers;
    }

    private int determineLetterWeigthing() {
        return random.nextInt(4) + 1;
    }

    private int determineNumberWeigthing() {
        return random.nextInt(4) + 1;
    }
}

public class KeyGenTest {
    @Test
    public void testExampleKeys() {
        assertEquals("VAT3735TVV4949RVJ793", new Key(20, new Random(0)).createKey());
        assertEquals("SBM8PBJ8PSJ6MWS2SBO2", new Key(20, new Random(1)).createKey());
        assertEquals("DGG77DJS28SDP13GGD77", new Key(20, new Random(2)).createKey());
        assertEquals("PGB844MGM477GCG728PL", new Key(20, new Random(3)).createKey());
    }

    @Test
    public void testCreateMultipleKeysFromOneGenerator() {
        Key keygen = new Key(20, new Random(10));
        assertEquals("YTD15DTT52YTO12IRL21", keygen.createKey());
        assertEquals("IK617LP637LG611IK619", keygen.createKey());
        assertEquals("VH1WY5YO1YP8SO9YO1YW", keygen.createKey());
    }
}

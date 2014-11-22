package com.janosgyerik.stackoverflow.user2999980;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

class CheckScores {

    private static final int REQUIRED_SCORE_COUNT = 6;

    private static final Pattern ILLEGAL_SYMBOLS = Pattern.compile("[^0-9,]");
    private static final Pattern STARTING_COMMAS = Pattern.compile("^,+");  // to replace with ""
    private static final Pattern TOO_MANY_COMMAS = Pattern.compile(",,+");  // to replace with ","

    public String checkScores(String textInput) {
        if (ILLEGAL_SYMBOLS.matcher(textInput).find()) {
            throw new IllegalArgumentException("Invalid Input. (Only digits and commas are allowed)");
        }

        textInput = textInput.replaceFirst(STARTING_COMMAS.pattern(), "");
        textInput = textInput.replaceAll(TOO_MANY_COMMAS.pattern(), ",");

        String[] scoresArray = textInput.split(",");
        if (scoresArray.length != REQUIRED_SCORE_COUNT) {
            throw new IllegalArgumentException("You have " + scoresArray.length + " scores. Acceptable amount is " + REQUIRED_SCORE_COUNT);
        }

        int[] uiArray = new int[scoresArray.length];
        for (int i = 0; i < scoresArray.length; i++) {
            uiArray[i] = Integer.parseInt(scoresArray[i]);
        }

        int sum = 0;
        for (int item : uiArray) {
            sum += item;
        }
        return "Scores sum:" + sum + " Number of scores:" + uiArray.length + " Number of ends:" + uiArray.length / 6;
    }
}

public class CheckScoresTest {
    CheckScores cs = new CheckScores();

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput_IllegalSymbols_Letters() {
        cs.checkScores("1,M,7,10,,4,8,");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput_IllegalSymbols_Space() {
        cs.checkScores("6,2, ,,5,6,1");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidInput_TooManyScores() {
        cs.checkScores("1,6,7,10,,4,8,,,,,,,1,2,6,10,2,10");
    }

    @Test
    public void testValidInput_31() {
        assertEquals("Scores sum:31 Number of scores:6 Number of ends:1", cs.checkScores(",,,,,,,1,2,6,10,2,10"));
    }

    @Test
    public void testValidInput_26() {
        assertEquals("Scores sum:26 Number of scores:6 Number of ends:1", cs.checkScores("10,2,1,5,7,1"));
    }
}

package com.janosgyerik.codereview.HassanAlthaf;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

class GuessMyNumber {

    public static final int CLOSE_LIMIT = 5;
    public static final String MSG_TOO_HIGH = "You're too high!";
    public static final String MSG_TOO_LOW = "You're too low!";
    public static final String MSG_CLOSE = "You're close!";
    public static final String MSG_WIN = "Congratulations! You guessed my number!";

    private final int randomNumber;
    private boolean numberFound = false;

    public GuessMyNumber(int min, int max) {
        Random random = new Random();
        this.randomNumber = random.nextInt((max - min) + 1) + min;
    }

    public String validateInput(int guessedNumber) {
        int diff = guessedNumber - randomNumber;
        if (diff == 0) {
            this.numberFound = true;
            return MSG_WIN;
        }
        int absoluteDiff = Math.abs(diff);
        if (absoluteDiff <= 5) {
            return MSG_CLOSE;
        }
        if (diff < 0) {
            return MSG_TOO_LOW;
        }
        return MSG_TOO_HIGH;
    }
}

public class GuessMyNumberTest {
    private static final int NUMBER = 55;

    private final GuessMyNumber game = new GuessMyNumber(NUMBER, NUMBER);

    @Test
    public void testTooHigh() {
        assertEquals(GuessMyNumber.MSG_TOO_HIGH, game.validateInput(NUMBER + GuessMyNumber.CLOSE_LIMIT + 1));
    }

    @Test
    public void testTooLow() {
        assertEquals(GuessMyNumber.MSG_TOO_LOW, game.validateInput(NUMBER - GuessMyNumber.CLOSE_LIMIT - 1));
    }

    @Test
    public void testCloseHigh() {
        assertEquals(GuessMyNumber.MSG_CLOSE, game.validateInput(NUMBER + GuessMyNumber.CLOSE_LIMIT));
    }

    @Test
    public void testCloseLow() {
        assertEquals(GuessMyNumber.MSG_CLOSE, game.validateInput(NUMBER - GuessMyNumber.CLOSE_LIMIT));
    }

    @Test
    public void testWin() {
        assertEquals(GuessMyNumber.MSG_WIN, game.validateInput(NUMBER));
    }
}

package com.janosgyerik.stackoverflow.jcob;

import org.junit.Test;

import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertTrue;

public class GuessMyNumberTest {
    public static int guess(int num) {
        int upperLimit = 100;
        int lowerLimit = 1;
        String myAnswer;

        int count = 0;

        do {
            ++count;
            int randNum = (upperLimit + lowerLimit + 1) / 2;
            if (randNum > num) {
                myAnswer = "th";
            } else if (randNum < num) {
                myAnswer = "tl";
            } else {
                myAnswer = "y";
            }

            if (myAnswer.equals("tl")) {  //too low
                lowerLimit = randNum + 1;
            } else if (myAnswer.equals("th")) { // too high
                upperLimit = randNum - 1;
            }
        } while (!myAnswer.equals("y"));

        return count;
    }

    private static final String TOO_HIGH_MARKER = "th";
    private static final String TOO_LOW_MARKER = "tl";
    private static final String CORRECT_MARKER = "y";

    private void orig() {
        Scanner in = new Scanner(System.in);
        Random rand = new Random();
        int randNum;
        int upperLimit = 100;
        int lowerLimit = 1;
        String myAnswer;

        do {
            randNum = rand.nextInt(upperLimit - lowerLimit + 1) + lowerLimit;
            System.out.println("I think it's " + randNum);
            myAnswer = in.nextLine();

            if (myAnswer.equals(TOO_LOW_MARKER)) {  //too low
                lowerLimit = randNum + 1;
            } else if (myAnswer.equals(TOO_HIGH_MARKER)) { // too high
                upperLimit = randNum - 1;
            }
        } while (!myAnswer.equals(CORRECT_MARKER));

        in.close();
        System.out.println("YAAAY! :D");
    }

    @Test
    public void test() {
        int count = 0;
        for (int num = 1; num <= 100; ++num) {
            if (guess(num) == 7) {
                //                System.out.println(num);
                ++count;
            }
            assertTrue(guess(num) < 8);
        }
        System.out.println(count);
    }
}

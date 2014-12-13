package com.janosgyerik.codereview.borderlinecool;

import java.util.Random;
import java.util.Scanner;

class GuessGame {

    public static Scanner scanKeyboard = new Scanner(System.in);

    public static void main(String[] args) {

        Handler gameHandler = new Handler();

        System.out.println("Start guessing! You have " + gameHandler.guessLeft + " guesses left.");
        System.out.println(gameHandler.goal);

        while (!gameHandler.gameOver) {
            if (gameHandler.guessLeft == 0) {
                gameHandler.gameOver = true;
                gameHandler.gameOver();
            } else if (gameHandler.isGuessRight()) {
                gameHandler.gameWin();
                gameHandler.gameOver = true;
            } else {

                gameHandler.guessCount++;
                System.out.println("Give me your " + gameHandler.guessCount + ". guess!");
                gameHandler.guesses[gameHandler.guessCount] = scanKeyboard.nextInt();
                gameHandler.guessLeft--;
            }
        }

        scanKeyboard.close();

    }
}

class Handler {

    public int guessLeft = 5;
    public int guessCount = 0;
    public int[] guesses = new int[guessLeft + 1];

    public boolean gameOver = false;

    public Random rand = new Random();
    public int goal = rand.nextInt(100);

    public String PrintGuesses() {
        String str = " ";
        for (int i = 1; i < guessCount; i++) {
            str += guesses[i] + " ";
        }
        return str;
    }

    public void gameOver() {
        System.out.println("You suck!");
        System.out.println("The answer was: " + goal);
        System.out.println("Your miserable guesses were:" + PrintGuesses() + guesses[guessCount]);

    }

    public void gameWin() {
        System.out.println("You won!");
        System.out.println("You guessed it on your " + guessCount + ". guess. With the guess " + guesses[guessCount] + ".");
        System.out.println("Your glorious guesses were:" + PrintGuesses() + guesses[guessCount]);
    }

    public boolean isGuessRight() {
        if (guessCount != 0) {
            if (guesses[guessCount] == goal) {
                return true;
            } else if (guesses[guessCount] < goal) {
                System.out.println("Too low.");
            } else if (guesses[guessCount] > goal) {
                System.out.println("Too high.");
            }
        }
        return false;

    }
}

public class GuessingGameTest {
}

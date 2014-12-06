package com.janosgyerik.codereview.user243872;

import java.util.Random;
import java.util.Scanner;

class Game implements Runnable {
    private final long gameTime;

    public Game(int gameTime) {
        this.gameTime = gameTime;
    }

    public void run() {
        long start = System.currentTimeMillis();
        while (true) {
            long elapsedTime = (System.currentTimeMillis() - start) / 1000;
            if (elapsedTime >= gameTime) {
                System.out.println("Oops! Time is up - try again.");
                break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // it's okay, just awakened early
            }
        }
    }
}

class Hilo {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Must enter time");
        }
        Random rand = new Random();
        int max = 100;
        int min = 1;
        int number = rand.nextInt((max - min) + 1) + min;
        int gameTime = Integer.parseInt(args[0]);
        System.out.println("Welcome to HiLo!");
        System.out.println("You have " + gameTime + " seconds to guess a number between 1 and 100.");
        Thread clock1 = new Thread(new Game(gameTime));
        clock1.start();

        while (clock1.isAlive()) {
            System.out.println(">");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();

            if (clock1.isAlive() && input == number) {
                System.out.println("You Win!");
                break;
            } else if (clock1.isAlive() && input < number) {
                System.out.println("Higher!");
            } else if (clock1.isAlive() && input > number) {
                System.out.println("Lower!");
            }
        }
    }
}

public class HiLoTest {
}

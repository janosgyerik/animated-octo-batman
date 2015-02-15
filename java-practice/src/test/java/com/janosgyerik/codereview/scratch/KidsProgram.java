package com.janosgyerik.codereview.scratch;

import java.util.Random;
import java.util.Scanner;

public class KidsProgram {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random rand = new Random();
        int number = rand.nextInt(100) + 1;
        System.out.println("Gissa på ett nr mellan 1 och 100");
        for (int i = 1; ; i++) {
            int guess = input.nextInt();
            if (guess < number) {
                System.out.println("Du gissade för lågt");
            } else if(guess > number) {
                System.out.println("Du gissade för högt");
            } else {
                System.out.println("Antal gissningar: " + i);
                System.out.println("Grattis du gissade rätt!");
                break;
            }
        }
    }
}
package com.janosgyerik.codereview.kleinfreund;

import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

class RationalNumber {
    private final int numerator;
    private final int denominator;

    RationalNumber(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("The denominator must be non-zero");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        return Double.toString((double) numerator / denominator);
    }
}

class GetRationalNumber {

    public static void main(String[] args) {
//        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

        Scanner userInput = new Scanner("3 4");
//        Scanner userInput = new Scanner(System.in);

        System.out.println("Numerator: ");
        int p = userInput.nextInt();

        System.out.println("Denominator: ");
        int q = userInput.nextInt();

        if (q == 0) {
            System.out.println("Divison by zero can cause serious trouble to the universe!");
            System.exit(0);
        }

        System.out.println("Result:\n" + (double) p / (double) q);
        System.out.println("Result:\n" + p / (double) q);
        System.out.println("Result:\n" + (double) p / q);

        RationalNumber rationalNumber = new RationalNumber(p, q);
        System.out.println("Result:\n" + rationalNumber);

        System.out.println("Result:\n" + new RationalNumber(p, q));
    }
}

public class GetRationalNumberTest {
    @Test
    public void test() throws IOException {
        GetRationalNumber.main(new String[0]);
    }
}

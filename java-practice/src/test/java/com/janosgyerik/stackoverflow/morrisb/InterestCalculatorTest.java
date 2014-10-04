package com.janosgyerik.stackoverflow.morrisb;

import java.text.NumberFormat;
import java.util.Scanner;

class InterestCalculator {

    enum Coin {
        NICKLE(.05),
        DIME(.1),
        QUARTER(.25);

        private final double value;

        Coin(double value) {
            this.value = value;
        }
    }

    private static class CoinAdder {
        private double value = 0;

        CoinAdder addCoins(Coin coin, int number) {
            value += coin.value * number;
            return this;
        }

        public double getValue() {
            return value;
        }
    }

    private static void calculateDollarValueOfCoins(Scanner scanner, NumberFormat moneyFormat) {
        System.out.println("How many quarters do you have?");
        int quarters = scanner.nextInt();

        System.out.println("How many dimes do you have?");
        int dimes = scanner.nextInt();

        System.out.println("How many nickles do you have?");
        int nickles = scanner.nextInt();

        double total = new CoinAdder()
                .addCoins(Coin.QUARTER, quarters)
                .addCoins(Coin.DIME, dimes)
                .addCoins(Coin.NICKLE, nickles)
                .getValue();
        System.out.println("You have: " + moneyFormat.format(total));
    }

    private static void calculateCompoundInterest(Scanner scanner, NumberFormat moneyFormat) {
        System.out.println("What is the initial investment?");
        double investment = scanner.nextDouble();

        System.out.println("At what intrest rate is the intrest compounded annually?");
        double intrestRate = scanner.nextDouble();

        double futureValueFive = investment * Math.pow(1 + intrestRate, 5.);
        System.out.println("In five years the investment will be worth : " + moneyFormat.format(futureValueFive));

        double futureValueTen = investment * Math.pow(1 + intrestRate, 10.);
        System.out.println("In ten years the investment will be worth : " + moneyFormat.format(futureValueTen));

        double futureValueTwenty = investment * Math.pow(1 + intrestRate, 20.);
        System.out.println("In twenty years the investment will be worth : " + moneyFormat.format(futureValueTwenty));
    }

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        NumberFormat moneyFormat = NumberFormat.getCurrencyInstance();

        calculateDollarValueOfCoins(scanner, moneyFormat);
        calculateCompoundInterest(scanner, moneyFormat);
    }
}

public class InterestCalculatorTest {
}

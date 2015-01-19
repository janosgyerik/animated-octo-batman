package com.janosgyerik.codereview.HenrikBohlin;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

class tenxten {
    static int numberRows = 10;
    static int numberColumns = 10;
    static int[][] grid = new int[numberColumns][numberRows];

    private static int randomInt(int from, int to) {
        Random rand = new Random();
        return rand.nextInt(to - from + 1) + from;
    }

    private static void amountOfSpecificNumbers() {
        int[] numbers = new int[numberColumns * numberRows];
        for (int i = 1; i < 10; i++) {
            for (int y = 0; y < 10; y++) {
                for (int x = 0; x < 10; x++) {
                    if (grid[y][x] == i) {
                        numbers[i] += i;
                    }
                }
            }
            System.out.println(" " + numbers[i] / i + " " + i + "s");
        }
    }

    private static void sumOfColumns() {
        int sumOfColumns[] = new int[numberColumns];
        for (int x = 0; x < numberColumns; x++) {
            for (int y = 0; y < numberRows; y++) {
                sumOfColumns[y] += grid[x][y];
            }
        }
        System.out.println(Arrays.toString(sumOfColumns));
    }

    private static void sumOfRows() {
        int sumOfRows[] = new int[numberColumns];
        for (int x = 0; x < numberColumns; x++) {
            for (int y = 0; y < numberRows; y++) {
                sumOfRows[x] += grid[x][y];
            }
        }
        System.out.println(Arrays.toString(sumOfRows));
    }

    private static void newField() {
        for (int x = 0; x < numberColumns; x++) {
            for (int y = 0; y < numberRows; y++) {
                int randomNumber = (randomInt(1, 10));
                grid[x][y] = randomNumber;
                if (randomNumber < 10) {
                    System.out.print(" " + randomNumber + " ");
                } else {
                    System.out.print(randomNumber + " ");
                }
            }
            System.out.println();
        }
    }

    private static void showField() {
        for (int x = 0; x < numberColumns; x++) {
            for (int y = 0; y < numberRows; y++) {
                if (grid[x][y] < 10) {
                    System.out.print(" " + grid[x][y] + " ");
                } else {
                    System.out.print(grid[x][y] + " ");
                }
            }
            System.out.println();
        }
    }

    private static int readInt(Scanner scanner) {
        String prompt = "Please enter number 1, 2, 3, 4, 5, or 6";
        System.out.println(prompt);
        while (!scanner.hasNext("[1-6]")) {
            System.out.println(prompt);
            scanner.next();
        }
        return scanner.nextInt();
    }

    public static void main(String[] args) {
        readInt(new Scanner(System.in));
        if (true) return;
        newField();
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("1. Get a new field");
            System.out.println("2. Show current field");
            System.out.println("3. Count the numbers in the current field");
            System.out.println("4. Sum all rows");
            System.out.println("5. Sum all columns");
            System.out.println("6. Exit program");
            Scanner scanner = new Scanner(System.in);

            int choice = readInt(scanner);

            switch (choice) {
                case 1:
                    newField();
                    break;
                case 2:
                    showField();
                    break;
            }
            if (choice == 1) {
                newField();
            } else if (choice == 2) {
                showField();
            } else if (choice == 3) {
                amountOfSpecificNumbers();
            } else if (choice == 4) {
                sumOfRows();
            } else if (choice == 5) {
                sumOfColumns();
            } else {
                return;
            }
        }
    }
}

public class GridTest {
}

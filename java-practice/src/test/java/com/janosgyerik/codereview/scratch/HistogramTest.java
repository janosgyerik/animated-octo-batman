package com.janosgyerik.codereview.scratch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

class VerticalHistogram {
    public static void main(String args[]) {
        int ranges[] = generateRandomNumbers(amountOfRandomNumbersToGenerate());
        printVerticalHistogram(largestValueInArray(ranges), ranges);
    }

    public static int amountOfRandomNumbersToGenerate() {
        return 45;
//        Scanner input = new Scanner(System.in);
//        System.out.print("Enter amount of random numbers to generate: ");
//        return Integer.parseInt(input.nextLine());
    }

    public static int[] generateRandomNumbers(int amount) {
        Random random = new Random(1);
        int ranges[] = new int[10];
        for (int i = 0; i < amount; i++) {
            int num = random.nextInt(100) + 1;
            ranges[Math.min(9, num / 10)]++;
        }
        return ranges;
    }

    public static int largestValueInArray(int ranges[]) {
        int largest = ranges[0];
        for (int i = 1; i < ranges.length; i++) {
            if (ranges[i] > largest) {
                largest = ranges[i];
            }
        }
        return largest;
    }

    public static void printVerticalHistogram(int rows, int asterisks[]) {

        System.out.printf(" %-7d%-7d%-7d%-7d%-7d%-7d%-7d%-7d%-7d%d", asterisks[0], asterisks[1], asterisks[2], asterisks[3], asterisks[4], asterisks[5], asterisks[6], asterisks[7], asterisks[8], asterisks[9]);
        System.out.println();
        while (rows > 0) {
            for (int i = 0; i < asterisks.length; i++) {
                if (i == 0) {
                    if (asterisks[i] < rows)
                        System.out.printf(" %-7s", " ");
                    else
                        System.out.printf(" %-7s", "*");
                } else {
                    if (asterisks[i] < rows)
                        System.out.printf("%-7s", " ");
                    else
                        System.out.printf("%-7s", "*");
                }
            }
            System.out.println();
            rows--;
        }
        System.out.printf("%-6s%-7s%-7s%-7s%-7s%-7s%-7s%-7s%-7s%s", "1-10", "11-20", "21-30", "31-40", "41-50", "51-60", "61-70", "71-80", "81-90", "91-100");
    }
}

public class HistogramTest {
    @Test
    public void test() {
        int[] result = VerticalHistogram.generateRandomNumbers(45);
        assertEquals("[6, 4, 1, 6, 2, 4, 6, 8, 2, 6]", Arrays.toString(result));
//        VerticalHistogram.printVerticalHistogram(VerticalHistogram.largestValueInArray(result), result);
    }
}

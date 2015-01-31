package com.janosgyerik.stackoverflow;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Scanner;

public class AnswerSession20150131 {
    public int missingNumber(int[] arr) {
        int length = arr.length;

        int indexes = 8;
        int values = 0;

        for (int i = 0; i < length; i++) {
            indexes += i + 1;
            values += arr[i];
        }

        int result = indexes - values;

        System.out.println("Indexes:" + indexes);
        System.out.println("Values:" + values);
        System.out.println("Missing number is: " + result);

        return result;
    }

    @Test
    public void testCase1() {
        int result = missingNumber(new int[]{1, 2, 3, 5, 6, 7, 8});
        Assert.assertEquals(4, result);
    }

    @Test
    public void test2() {
        Scanner in = new Scanner("0000000000000000\n" +
                "0000000000000000\n" +
                "0000000400000000\n" +
                "0001111111160000\n" +
                "0001115111110000\n" +
                "0001161121510000\n" +
                "0001111511110000\n" +
                "0001110001110000\n" +
                "0000011311000000\n" +
                "0000011111000000\n" +
                "0000000000000000\n" +
                "0000000000000000\n" +
                "0000000000000000\n" +
                "0000000000000000\n" +
                "0000000000000000\n" +
                "0000000000000000\n");
        String s = in.nextLine();
        int largo = s.length();

        String[][] matrix = new String[largo][largo];

        for (int j = 0; j < 16; j++) {
            matrix[0][j] = String.valueOf(s.charAt(j));
        }
        for (int i = 1; in.hasNextLine(); ++i) {
            String line = in.nextLine();
            for (int j = 0; j < 16; j++) {
                matrix[i][j] = String.valueOf(line.charAt(j));
            }
        }

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 16; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }
    }

    @Test
    public void test3() {
        String[] names = {"mckeownl", "heardj", "williamsc"};
        String[] attendance = {"yes", "no", "yes"};
        int[] grade = {96, 66, 73};

        boolean loggedin = false;

        Scanner user_input = new Scanner("mckeownl");
        String login;
        login = user_input.next();

        do {   // beginning of while - login
            System.out.println("Insert student's surname followed by the first letter");
            System.out.print("of their first name (e.g John Smith = smithj): ");

            if (Arrays.asList(names).contains(login)) {
                System.out.println("Student selected: "+login+".");
                loggedin = true;
            }
            else {
                System.out.println("Incorrect student name! Please try again.");
                loggedin = false;
            }

        } while ( ! loggedin);

        if (login.equals(names[0])) {
            System.out.println("Attend today: "+attendance[0]);
            System.out.println("Grade: ");
        }
        else {
            System.out.println("poo");
        }


    }
}

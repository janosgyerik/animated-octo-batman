package com.janosgyerik.stackoverflow.winterbash;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;


public class AnswerSession20141214 {
    @Test
    public void test1() {
        assertEquals("a%20b", "a b".replaceAll(" ", "%20"));
        assertEquals("a%20b", replace("a b"));
    }

    public String replace(String str) {
        String[] words = str.split(" ");
        StringBuilder sentence = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; ++i) {
            sentence.append("%20");
            sentence.append(words[i]);
        }

        return sentence.toString();
    }

    @Test
    public void test2() {
        assertEquals("12:34", "12:34:36".replaceAll(":[^:]+$", ""));
        String str = "12:34:56";
        assertEquals("12:34", str.substring(0, str.lastIndexOf(":")));
        String[] parts = str.split(":");
        assertEquals("12:34", String.format("%02d:%02d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        str = "1:70:00";
        parts = str.split(":");
        assertEquals("01:70", String.format("%02d:%02d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
    }

    @Test
    public void testDate() throws ParseException {
        String input = "2014-12-12T10:39:40Z";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = parser.parse(input);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy H:mm:ss a");
        System.out.println(formatter.format(date));
    }

    @Test
    public void testDate2() throws ParseException {
        String input = "1:70:00";
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
        Date date = parser.parse(input);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        System.out.println(formatter.format(date));
    }

    @Test
    public void testScanInteger() {
        Scanner scanner = new Scanner("3");
        String line = scanner.nextLine();
        int choice = Integer.parseInt(line);
        assertEquals(3, choice);
    }

    @Test
    public void testScanIntegerFails() {
        Scanner scanner = new Scanner("x3");
        String line = scanner.nextLine();
        try {
            int choice = Integer.parseInt(line);
            assertEquals(3, choice);
        } catch (NumberFormatException e) {

        }
        //        assertTrue(new Scanner("x3").hasNextInt());
    }

    @Test
    public void answer() {
        boolean loop = false;
        Scanner scanner = new Scanner("");
        int choice;
        while (loop && scanner.hasNextLine()) {
            String line = scanner.nextLine();
            try {
                choice = Integer.parseInt(line);
                assertEquals(3, choice);
            } catch (NumberFormatException e) {
                System.out.println("That is not a correct choice. Please try again!");
                continue;
            }

            switch (choice) {
                case 1:
                    //                    language = "FRENCH";
                    loop = false;
                    break;
                case 2:
                    //                    language = "GERMAN";
                    loop = false;
                    break;
                case 3:
                    //                    language = "SPANISH";
                    loop = false;
                    break;
                default:
                    System.out.println("That is not a correct choice. Please try again!");
                    break;
            }
        }
    }

    class Player {

        private String name;

        public void setName(String pName) {
            name = pName;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return "Player{name='" + name + "'}";
        }
    }

    @Test
    public void test6() {
        for (int i = 10; i > 1; --i) {
            if (i % 2 == 0) {
                System.out.print(i / 2);
                if (i != 2) System.out.print(",");
            }
        }
    }
}

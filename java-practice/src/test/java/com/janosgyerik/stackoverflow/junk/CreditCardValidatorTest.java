package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class CreditCardValidator {

    public static void read(Scanner scanner) {
        while (scanner.hasNextLine()) {
            String str = scanner.nextLine();
            if (isValid(str)) {
                System.out.println(getCreditCardType(str));
            } else {
                System.out.println("Invalid");
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(args[0])); // read from a file
        read(scan);
    }

    static boolean isValid(String str) {
        String reverse = new StringBuilder().append(str).reverse().toString();
        int[] array = new int[str.length()];
        for (int i = 0; i < array.length; i++) {
            array[i] = reverse.charAt(i) - '0';
            if (array[i] < 0 || array[i] > 9) {
                return false;
            }
        }
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (i % 2 == 1) {
                array[i] *= 2;
                if (array[i] > 9) {
                    array[i] -= 9;
                }
            }
            sum += array[i];
        }
        return sum % 10 == 0;
    }

    static String getCreditCardType(String str) {
        int firstDigit = Integer.parseInt(str.substring(0, 1));
        int first2 = Integer.parseInt(str.substring(0, 2));
        int first3 = Integer.parseInt(str.substring(0, 3));
        int first4 = Integer.parseInt(str.substring(0, 4));
        int first6 = Integer.parseInt(str.substring(0, 6));
        
        int length = str.length();

        if (first2 == 34 || first2 == 37) {
            if (length == 15) {
                return "American Express";
            }
        }

        if (first4 == 6011
                || first6 >= 622126 && first6 <= 622925
                || first3 >= 644 && first3 <= 649
                || first2 == 65) {
            if (length == 16) {
                return "Discover";
            }
        }

        if (first2 >= 51 && first2 <= 55) {
            if (length >= 16 && length <= 19) {
                return "MasterCard";
            }
        }

        if (firstDigit == 4) {
            if (length >= 13 && length <= 16) {
                return "Visa";
            }
        }
        return "Invalid Credit Card Type";
    }
}

public class CreditCardValidatorTest {
    @Test
    public void testValidVisa() {
        assertTrue(CreditCardValidator.isValid("1234567812342222"));
    }

    @Test
    public void testVisa() {
        assertEquals("Visa", CreditCardValidator.getCreditCardType("4234567812342222"));
        assertEquals("Invalid Credit Card Type", CreditCardValidator.getCreditCardType("1234567812342222"));
    }

    @Test
    public void testMaster() {
        assertEquals("MasterCard", CreditCardValidator.getCreditCardType("5134567812342222"));
        assertEquals("Invalid Credit Card Type", CreditCardValidator.getCreditCardType("51345678123422221111"));
    }

    @Test
    public void testDiscover() {
        assertEquals("Discover", CreditCardValidator.getCreditCardType("6011513456781234"));
        assertEquals("Discover", CreditCardValidator.getCreditCardType("6221263456781234"));
        assertEquals("Discover", CreditCardValidator.getCreditCardType("6441263456781234"));
        assertEquals("Invalid Credit Card Type", CreditCardValidator.getCreditCardType("6741263456781234"));
    }
}

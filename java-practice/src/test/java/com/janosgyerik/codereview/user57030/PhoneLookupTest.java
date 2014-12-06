package com.janosgyerik.codereview.user57030;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class PhoneLookup {

    private static Map<String, String> namesToNumbers = new HashMap<>();
    private static Map<String, String> numbersToNames = new HashMap<>();

    private static void registerEntry(String name, String number) {
        namesToNumbers.put(name, number);
        numbersToNames.put(number, name);
    }

    static {
        registerEntry("Tom", "6037571122");
        registerEntry("Alice", "6037779057");
        registerEntry("George", "6037779103");
        registerEntry("Ben", "6031234322");
        registerEntry("Jack", "6037764522");
        registerEntry("Greg", "6039985434");
        registerEntry("Franklin", "60344578993");
        registerEntry("Sam", "6033458765");
        registerEntry("Mike", "6036673322");
        registerEntry("Alex", "6035561199");
    }

    public static String getName(String number) {
        String name = numbersToNames.get(number);
        return name == null ? "" : name;
    }

    public static String getNumber(String name) {
        String number = namesToNumbers.get(name);
        return number == null ? "" : outputFormat(number);
    }

    public static String outputFormat(String phone) {
        String temp = "(";

        for (int i = 0; i < phone.length(); i++) {
            if (i < 2)
                temp += phone.charAt(i);

            else if (i == 2)
                temp += phone.charAt(i) + ") ";

            else if (i < 5)
                temp += phone.charAt(i);

            else if (i == 5)
                temp += phone.charAt(i) + " - ";

            else
                temp += phone.charAt(i);
        }

        return temp;

    }

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to the Phone Look up Program ");

        char ch = ' ';

        while (true) {
            System.out.print("please enter the name or phone number ( no delemeters please) (q to quit) : ");
            String input = sc.next();

            ch = input.charAt(0);

            if (ch == 'q')
                break;

            // Finding whether given input is Name or Phone Number

            if (Character.isDigit(ch)) {
                String name = getName(input);

                if (!name.equals(""))
                    System.out.println("Number : " + outputFormat(input) + " belongs to : " + name);

                else
                    System.out.println("There is no listing for : " + outputFormat(input));
            } else {
                String phone = getNumber(input);

                if (!phone.equals(""))
                    System.out.println(input + "'s" + " phone number is : " + phone);

                else
                    System.out.println("There is no listing for : " + input);
            }
        }
    }
}

public class PhoneLookupTest {
    @Test
    public void test() {
        assertEquals("(603) 776 - 4522", PhoneLookup.getNumber("Jack"));
        assertEquals("", PhoneLookup.getNumber("Jackson"));
        assertEquals("", PhoneLookup.getName("(603) 776 - 4522"));
        assertEquals("Jack", PhoneLookup.getName("6037764522"));
    }
}

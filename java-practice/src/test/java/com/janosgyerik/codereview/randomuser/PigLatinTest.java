package com.janosgyerik.codereview.randomuser;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

class Assignment9 {
    public static void main(String[] args) {
        // Variables
        String sentence, revisedSentence, latin = " ";

        // Create a Scanner object for keyboard input
        Scanner keyboard = new Scanner(System.in);

        // Get the input string
        System.out.print("Enter sentence: ");
        sentence = keyboard.nextLine();

        //Close keyboard
        keyboard.close();

        // Task 1
        revisedSentence = WordSeparator(sentence);

        System.out.print("Revised Sentence: " + revisedSentence);

        // Task 2
        PigLatin(revisedSentence, latin);

        System.exit(0);
    }

    public static String WordSeparator(String sentence) {
        StringBuilder str = new StringBuilder(sentence);
        int i = 1;

        // While loop repeats until the end of the sentence
        while (i < str.length()) {
            char ch = str.charAt(i);
            if (Character.isUpperCase(ch)) {
                str.insert(i, ' ');
                str.setCharAt(i + 1, Character.toLowerCase(ch));
            }
            i++;
        }
        return str.toString();
    }

    public static void PigLatin(String revisedSentence, String latin) {
        // Split sentence by spaces
        String[] tokens = revisedSentence.split(" ");

        // Convert English sentence into Pig Latin
        for (String str : tokens) {
            // Get string from array
            // Get first letter from string
            String str1 = str.substring(0, 1);

            // Get substring from string
            String str2 = str.substring(1, str.length());

            // Concatenate the two strings in a required format
            str2 = str2.concat(str1);

            // Concatenate the result and "AY"
            str2 = str2.concat("ay");

            // Make a sentence with all the words
            latin = latin.concat(str2 + " ");
        }

        // Display pig latin verison
        System.out.println("\nPig Latin Version:" + latin);
    }

}

public class PigLatinTest {
    @Test
    public void testStopAndSmellTheRose() {
        assertEquals("Stop and smell the rose", Assignment9.WordSeparator("StopAndSmellTheRose"));
        Assignment9.PigLatin("Stop and smell the rose", "ay");
    }

    //    @Test
    public void testStopAndSmell_The_Rose() {
        assertEquals("Stop and smell the rose", Assignment9.WordSeparator("StopAndSmell The Rose"));
    }
}

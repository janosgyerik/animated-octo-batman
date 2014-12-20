package com.janosgyerik.stackoverflow.winterbash;

import org.junit.Test;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class WordGuessingGame3 {
    private static String playerInput;
    private static String randomWord;

    static class RandomWordProvider {

        public final List<String> words;

        public RandomWordProvider() {
            words = readFile();
        }

        public int randomInteger() {
            int randomInt = (int) (Math.random() * words.size());
            return randomInt;
        }

        public String getWord() {
            int randomPosition = randomInteger();
            String randomWord = words.get(randomPosition);
            return randomWord;
        }

        private List<String> readFile() {

            List<String> wordsList = new ArrayList<>();

            try {
                File fourLetterWords = new File(System.getProperty("user.home"), "Documents/FourLetterWords.txt");
                Scanner in = new Scanner(fourLetterWords);

                while (in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line != null && !line.isEmpty()) {
                        wordsList.add(line);
                    }
                }
            } catch (FileNotFoundException ex) {
                System.out.println("\nFile not found.");
                System.exit(0);
            }
            return wordsList;
        }
    }

    public WordGuessingGame3(String playerInput) {
        WordGuessingGame3.playerInput = playerInput;
    }

    public String getPlayerInput() {
        return playerInput;
    }

    public void setPlayerInput(String playerInput) {
        WordGuessingGame3.playerInput = playerInput;
    }

    public static class PlayerCharacterEntry {
        private String playerEntry() {
            Scanner characterEntry = new Scanner(System.in);
            System.out.print("Enter a character: ");
            String playerInput = characterEntry.next();
            playerInput = playerInput.toUpperCase();
            return playerInput;
        }
    }

    @Test
    public void test() {

        RandomWordProvider randomWordProvider = new RandomWordProvider();
        PlayerCharacterEntry playerCharacterEntry = new PlayerCharacterEntry();

        randomWordProvider.getWord();
//        playerCharacterEntry.playerEntry();


        if (randomWord.contains(playerInput)) {
            System.out.println(playerInput);
        } else {
            System.out.println("That letter is not in the word!");
        }

    }
}



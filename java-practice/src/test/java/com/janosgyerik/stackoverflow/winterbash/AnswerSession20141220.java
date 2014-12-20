package com.janosgyerik.stackoverflow.winterbash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AnswerSession20141220 {
}

class RandomWordProvider {

    public final List<String> words;

    public RandomWordProvider() {
        words = readFile();
    }

    public int randomInteger() {
        int randomInt = (int) (Math.random() * words.size());
        return randomInt;
    }

    String getWord() {
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
            System.out.println("File not found.");
        }
        return wordsList;
    }
}

class PlayerCharacterEntry {
    String playerEntry() {
        Scanner characterEntry = new Scanner(System.in);
        System.out.print("Enter a character: ");
        String playerInput = characterEntry.next();
        playerInput = playerInput.toUpperCase();
        return playerInput;
    }
}

class WordGuessingGame2 {

    public static void main(String[] args) {

        Scanner wantToPlay = new Scanner(System.in);
        System.out.print("Welcome to the word guessing game! Would you like to play? ");
        String playerAnswer = wantToPlay.next();

        if (playerAnswer.equalsIgnoreCase("Yes")) {
            System.out.print("\nYour objective is to guess a four letter word by entering"
                    + "\nletters on your keyboard. If you can not guess the word in seven attempts,"
                    + "\nyou lose! You will be told if the letter you entered is in the word, and"
                    + "\nyou will be told if the letter you entered is not in the word. You will be"
                    + "\nallowed to guess the word any time during your seven attempts. If at anytime"
                    + "\nyou would like to terminate the game, enter the word 'terminate'. Good Luck!"
                    + "\n \n");
        }
        if (playerAnswer.equalsIgnoreCase("No")) {
            System.out.print("Maybe another time!");
            System.exit(0);
        }

        RandomWordProvider randomWordProvider = new RandomWordProvider();
        PlayerCharacterEntry playerCharacterEntry = new PlayerCharacterEntry();

        randomWordProvider.getWord();
        playerCharacterEntry.playerEntry();

        String[][] choices = new String[4][];
        choices[0] = new String[10];
        choices[1] = new String[30];
        choices[2] = new String[20];
        choices[3] = new String[20];

    }
}
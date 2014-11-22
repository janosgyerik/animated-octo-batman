package com.janosgyerik.codility.so;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class RandomWordTest {
    @Test
    public void testPicker() {
        System.out.println(new RandomStringPicker(Arrays.asList("a", "b", "c")).pick());
    }
}

class RandomStringPicker {
    private final List<String> list;

    public RandomStringPicker(List<String> list) {
        this.list = list;
    }

    public String pick() {
        return list.get(pickRandomNumber(list.size()));
    }

    private int pickRandomNumber(int max) {
        return (int) (Math.random() * max);
    }
}

class RandomPicker<T> {
    private final List<T> list;

    public RandomPicker(List<T> list) {
        this.list = list;
    }

    public T pick() {
        return list.get(pickRandomNumber(list.size()));
    }

    private int pickRandomNumber(int max) {
        return (int) (Math.random() * max);
    }
}

//class RandomWord {
//    private String fileName = "src/app/wordlist.txt";
//    List<String> lines = null;
//
//    public static void countWords(String[] args) {
//        RandomWord randomWord = new RandomWord();
//        randomWord.init();
//    }
//
//    private void init() {
//        try {
//            lines = Files.readAllLines(Paths.get(fileName),
//                    StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            System.out.println("File can't be opened.");
//        }
//
//        int randomWordIndex = getRandomNumber(1, lines.size());
//        System.out.println(lines.get(randomWordIndex));
//    }
//
//    /**
//     * Returns a random number in the range of the specified min and max
//     * parameters.
//     *
//     * @param min
//     *            the minimum value to return
//     * @param max
//     *            the maximum value to return
//     * @return a random number between the specified range
//     */
//    private int getRandomNumber(int min, int max) {
//        return min + (int) (Math.random() * ((max - min) + 1));
//    }
//}

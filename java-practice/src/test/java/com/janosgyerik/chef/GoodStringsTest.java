package com.janosgyerik.chef;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

import static org.junit.Assert.assertEquals;

public class GoodStringsTest {
    private int solve(String string, char start, char end, int startIndex, int endIndex) {
        int countOfStartLetters = 0;
        int count = 0;
        for (int pos = startIndex - 1; pos < endIndex; ++pos) {
            char c = string.charAt(pos);
            if (c == start) {
                ++countOfStartLetters;
            } else if (c == end) {
                count += countOfStartLetters;
            }
        }
        return count;
    }

    @Test
    public void test_checfcheff_c_h_1_10() {
        assertEquals(4, solve("checfcheff", 'c', 'h', 1, 10));
    }

    @Test
    public void test_checfcheff_c_f_1_10() {
        assertEquals(8, solve("checfcheff", 'c', 'f', 1, 10));
    }

    @Test
    public void test_checfcheff_e_c_1_10() {
        assertEquals(2, solve("checfcheff", 'e', 'c', 1, 10));
    }

    @Test
    public void test_checfcheff_c_f_1_5() {
        assertEquals(2, solve("checfcheff", 'c', 'f', 1, 5));
    }

    @Test
    public void test_checfcheff_c_f_6_10() {
        assertEquals(2, solve("checfcheff", 'c', 'f', 6, 10));
    }
}

class ChefAndStrings {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String specialString = "";

    public static void main(String[] args) {
        PrintStream out = System.out;
        try {
            specialString = br.readLine();
            int numberOfQueries = Integer.parseInt(br.readLine());

            for (int i = 0; i < numberOfQueries; i++) {
                String nextQuery = nextQuery();
                out.println(getGoodStringCounts(nextQuery));
            }
        } catch (IOException ex) {
            out.println("Exception occurred while reading input");
        }
    }

    private static String nextQuery() throws IOException {
        return br.readLine();
    }

    private static int getGoodStringCounts(String query) {
        StringTokenizer tokenizer = new StringTokenizer(query, " ");
        char startLetter = tokenizer.nextToken().charAt(0);
        char endLetter = tokenizer.nextToken().charAt(0);
        int startIndex = Integer.parseInt(tokenizer.nextToken()) - 1;
        int endIndex = Integer.parseInt(tokenizer.nextToken()) - 1;

        int[] startLetterIndices = allIndicesGreaterThanMin(startLetter, startIndex);
        int[] endLetterIndices = allIndicesLessThanMax(endLetter, endIndex);

        int total = 0;
        int beginEndIndexFrom = 0;

        for (int index : startLetterIndices) {
            for (int j = beginEndIndexFrom; j < endLetterIndices.length; j++) {
                if (endLetterIndices[j] > index) {
                    beginEndIndexFrom = j;
                    total += (endLetterIndices.length - beginEndIndexFrom);
                    break;
                }
            }
        }

        return total;
    }

    private static int[] allIndicesGreaterThanMin(char letter, int startIndex) {
        int[] indices = new int[specialString.length() / 2];
        int currentIndex = 0;
        int index = specialString.indexOf(letter, startIndex);
        while (index != -1) {
            indices[currentIndex++] = index;
            startIndex = index + 1;
            index = specialString.indexOf(letter, startIndex);
        }
        int[] result = new int[currentIndex];
        System.arraycopy(indices, 0, result, 0, result.length);
        return result;
    }

    private static int[] allIndicesLessThanMax(char letter, int endIndex) {
        int[] indices = new int[specialString.length() / 2];
        int currentIndex = 0;
        int index = specialString.indexOf(letter);
        while (index != -1 && index <= endIndex) {
            indices[currentIndex++] = index;
            index = specialString.indexOf(letter, index + 1);
        }
        int[] result = new int[currentIndex];
        System.arraycopy(indices, 0, result, 0, result.length);
        return result;
    }
}
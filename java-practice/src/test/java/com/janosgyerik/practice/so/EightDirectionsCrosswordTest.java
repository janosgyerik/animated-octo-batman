package com.janosgyerik.practice.so;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EightDirectionsCrosswordTest {
    private int countSolutions(String word, char[] line) {
        return countSolutions(word, line, 0);
    }

    private int countSolutions(String word, String line) {
        return countSolutions(word, line.toCharArray());
    }

    private int countSolutions(String word, char[] line, int from) {
        for (int i = from; i < line.length && word.length() <= line.length - i; ++i) {
            if (word.charAt(0) == line[i]) {
                int count = countSolutions(word, line, i + 1);
                if (word.length() > 1) {
                    return count + countSolutions(word.substring(1), line, i + 1);
                }
                return count + 1;
            }
        }
        return 0;
    }

    private int countSolutions(String word, String[] puzzle) {
        int count = 0;
        for (char[] line : getHorizontalLines(puzzle)) {
            count += countSolutions(word, line);
            count += countSolutions(word, reverse(line));
        }
        for (char[] line : getVerticalLines(puzzle)) {
            count += countSolutions(word, line);
            count += countSolutions(word, reverse(line));
        }
        for (char[] line : getDiagonalDownwardLines(puzzle, word.length())) {
            count += countSolutions(word, line);
            count += countSolutions(word, reverse(line));
        }
        for (char[] line : getDiagonalUpwardLines(puzzle, word.length())) {
            count += countSolutions(word, line);
            count += countSolutions(word, reverse(line));
        }
        return count;
    }

    private char[] reverse(char[] line) {
        char[] reversed = new char[line.length];
        for (int i = 0; i < reversed.length / 2 + 1; ++i) {
            reversed[i] = line[line.length - i - 1];
            reversed[line.length - i - 1] = line[i];
        }
        return reversed;
    }

    @Test
    public void testCountSolutions() {
        Assert.assertEquals(1, countSolutions("hello", "hello"));
        Assert.assertEquals(2, countSolutions("hello", "helloo"));
        Assert.assertEquals(2, countSolutions("hello", "heello"));
        Assert.assertEquals(3, countSolutions("hello", "hellooo"));
        Assert.assertEquals(6, countSolutions("hello", "heellooo"));
        Assert.assertEquals(6, countSolutions("hello", "xheellooo"));
        Assert.assertEquals(6, countSolutions("hello", "heexxllooo"));
        Assert.assertEquals(6, countSolutions("hello", "heelloooxx"));
    }

    @Test
    public void testGivenExamples() {
        Assert.assertEquals(12, countSolutions("aa", new String[]{"aa", "aa"}));
        Assert.assertEquals(56, countSolutions("aa", new String[]{"aaa", "aaa", "aaa"}));
        Assert.assertEquals(4, countSolutions("silba", new String[]{
                "siolobba",
                "oooaoooo",
                "oooboooo",
                "aooooooo",
                "oboloooo",
                "oolooooo",
                "oooioooo",
                "ooossooo"
        }));
    }

    char[][] getHorizontalLines(String[] puzzle) {
        char[][] lines = new char[puzzle.length][];
        int i = 0;
        for (String line : puzzle) {
            lines[i++] = line.toCharArray();
        }
        return lines;
    }

    char[][] getVerticalLines(String[] puzzle) {
        char[][] lines = new char[puzzle.length][];
        for (int i = 0; i < puzzle.length; ++i) {
            lines[i] = new char[puzzle.length];
            for (int j = 0; j < puzzle.length; ++j) {
                lines[i][j] = puzzle[j].charAt(i);
            }
        }
        return lines;
    }

    char[][] getDiagonalDownwardLines(String[] puzzle, int minLength) {
        if (puzzle.length < minLength) {
            return new char[0][];
        }
        int num = (puzzle.length - minLength) * 2 + 1;
        char[][] lines = new char[num][];
        lines[0] = new char[puzzle.length];
        for (int i = 0; i < puzzle.length; ++i) {
            lines[0][i] = puzzle[i].charAt(i);
        }
        for (int i = minLength, j = 1; i < puzzle.length; ++i, ++j) {
            int k = 2 * j - 1;
            lines[k] = new char[i];
            lines[k + 1] = new char[i];
            for (int m = 0; m < i; ++m) {
                lines[k][m] = puzzle[puzzle.length - i + m].charAt(m);
                lines[k + 1][m] = puzzle[m].charAt(puzzle.length - i + m);
            }
        }
        return lines;
    }

    char[][] getDiagonalUpwardLines(String[] puzzle, int minLength) {
        if (puzzle.length < minLength) {
            return new char[0][];
        }
        int num = (puzzle.length - minLength) * 2 + 1;
        char[][] lines = new char[num][];
        lines[0] = new char[puzzle.length];
        for (int i = 0; i < puzzle.length; ++i) {
            lines[0][i] = puzzle[puzzle.length - i - 1].charAt(i);
        }
        for (int i = minLength, j = 1; i < puzzle.length; ++i, ++j) {
            int k = 2 * j - 1;
            lines[k] = new char[i];
            lines[k + 1] = new char[i];
            for (int m = 0; m < i; ++m) {
                lines[k][m] = puzzle[i - m - 1].charAt(m);
                lines[k + 1][m] = puzzle[puzzle.length - 1 - m].charAt(puzzle.length - i + m);
            }
        }
        return lines;
    }

    private List<String> charMatrixToStrings(char[][] matrix) {
        List<String> strings = new ArrayList<String>();
        for (char[] arr : matrix) {
            strings.add(new String(arr));
        }
        return strings;
    }

    private void assertEquals(String[] expected, char[][] actual) {
        Assert.assertNotNull("expected String[] should not be null", expected);
        Assert.assertNotNull("actual char[][] should not be null", actual);

        List<String> expectedStrings = new ArrayList<String>();
        Collections.addAll(expectedStrings, expected);
        List<String> actualStrings = charMatrixToStrings(actual);
        Assert.assertEquals(expectedStrings, actualStrings);
    }

    @Test
    public void testGetDiagonalDownwardLines() {
        assertEquals(new String[]{"aa", "a", "a"}, getDiagonalDownwardLines(new String[]{"aa", "aa"}, 1));
        assertEquals(new String[]{"aa",}, getDiagonalDownwardLines(new String[]{"aa", "aa"}, 2));
        assertEquals(new String[]{"aaa",}, getDiagonalDownwardLines(new String[]{"aaa", "aaa", "aaa"}, 3));
        assertEquals(new String[]{"aei"}, getDiagonalDownwardLines(new String[]{"abc", "def", "ghi"}, 3));
        assertEquals(new String[]{"aei", "dh", "bf"}, getDiagonalDownwardLines(new String[]{"abc", "def", "ghi"}, 2));
        assertEquals(new String[]{"aei", "g", "c", "dh", "bf"}, getDiagonalDownwardLines(new String[]{"abc", "def", "ghi"}, 1));
        assertEquals(new String[]{"aei4", "dh3", "bfz"}, getDiagonalDownwardLines(new String[]{"abcx", "defy", "ghiz", "1234"}, 3));
    }

    @Test
    public void testGetDiagonalUpwardLines() {
        assertEquals(new String[]{"aa", "a", "a"}, getDiagonalUpwardLines(new String[]{"aa", "aa"}, 1));
        assertEquals(new String[]{"aa",}, getDiagonalUpwardLines(new String[]{"aa", "aa"}, 2));
        assertEquals(new String[]{"aaa",}, getDiagonalUpwardLines(new String[]{"aaa", "aaa", "aaa"}, 3));
        assertEquals(new String[]{"gec"}, getDiagonalUpwardLines(new String[]{"abc", "def", "ghi"}, 3));
        assertEquals(new String[]{"gec", "db", "hf"}, getDiagonalUpwardLines(new String[]{"abc", "def", "ghi"}, 2));
        assertEquals(new String[]{"1hfx", "gec", "2iy"}, getDiagonalUpwardLines(new String[]{"abcx", "defy", "ghiz", "1234"}, 3));
        assertEquals(new String[]{"1hfx", "db", "3z", "gec", "2iy"}, getDiagonalUpwardLines(new String[]{"abcx", "defy", "ghiz", "1234"}, 2));
        assertEquals(new String[]{"gec", "a", "i", "db", "hf"}, getDiagonalUpwardLines(new String[]{"abc", "def", "ghi"}, 1));
    }

    @Test
    public void testGetVerticalLines() {
        assertEquals(new String[] {"ac", "bd" }, getVerticalLines(new String[]{"ab", "cd"}));
        assertEquals(new String[] {"adg", "beh", "cfi" }, getVerticalLines(new String[]{"abc", "def", "ghi"}));
    }

     @Test
    public void testGetHorizontalLines() {
        assertEquals(new String[] {"ab", "cd" }, getHorizontalLines(new String[]{"ab", "cd"}));
        assertEquals(new String[] {"abc", "def", "ghi" }, getHorizontalLines(new String[]{"abc", "def", "ghi"}));
    }
}

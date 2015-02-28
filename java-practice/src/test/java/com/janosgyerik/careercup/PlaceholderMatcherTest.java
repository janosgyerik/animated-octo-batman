package com.janosgyerik.careercup;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class Matcher {
    final Set<String> index = new HashSet<>();

    Matcher(List<String> words) {
        words.forEach(this::addPermutations);
    }

    final void addPermutations(String word) {
        index.add(word);
        char[] letters = word.toCharArray();
        for (List<Integer> starPositions : enumerateStarPositions(word.length())) {
            String wordWithStars = getWordWithStars(letters, starPositions);
            index.add(wordWithStars);
        }
    }

    private static class ListWithIndex {
        final List<Integer> list;
        final int index;

        private ListWithIndex(List<Integer> list, int index) {
            this.list = list;
            this.index = index;
        }
    }

    List<List<Integer>> enumerateStarPositions(int length) {
        List<List<Integer>> results = new LinkedList<>();

        Queue<ListWithIndex> canGrow = new LinkedList<>();
        canGrow.add(new ListWithIndex(new LinkedList<>(), 0));

        while (!canGrow.isEmpty()) {
            ListWithIndex listWithIndex = canGrow.poll();
            for (int i = listWithIndex.index; i < length - 1; ++i) {
                List<Integer> copy = new LinkedList<>(listWithIndex.list);
                copy.add(i);
                results.add(copy);
                canGrow.add(new ListWithIndex(copy, i + 1));
            }
            List<Integer> copy = new LinkedList<>(listWithIndex.list);
            copy.add(length - 1);
            results.add(copy);
        }
        return results;
    }

    private String getWordWithStars(char[] letters, List<Integer> starPositions) {
        char[] copy = letters.clone();
        for (int pos : starPositions) {
            copy[pos] = '*';
        }
        return new String(copy);
    }

    public boolean hasMatch(String word) {
        return index.contains(word);
    }

}

public class PlaceholderMatcherTest {
    private Matcher matcher = new Matcher(Arrays.asList("hazem", "ahmed", "moustafa", "fizo"));

    @Test
    public void test_enumerateStarPos_1() {
        assertEquals("[[0]]", matcher.enumerateStarPositions(1).toString());
    }

    @Test
    public void test_enumerateStarPos_2() {
        assertEquals("[[0], [1], [0, 1]]", matcher.enumerateStarPositions(2).toString());
    }

    @Test
    public void test_enumerateStarPos_3() {
        assertEquals("[[0], [1], [2], [0, 1], [0, 2], [1, 2], [0, 1, 2]]", matcher.enumerateStarPositions(3).toString());
    }

    @Test
    public void test_enumerateStarPos_4() {
        assertEquals("[[0], [1], [2], [3], [0, 1], [0, 2], [0, 3], [1, 2], [1, 3], [2, 3], [0, 1, 2], [0, 1, 3], [0, 2, 3], [1, 2, 3], [0, 1, 2, 3]]",
                matcher.enumerateStarPositions(4).toString());
    }

    @Test
    public void test_ahmed() {
        assertTrue(matcher.hasMatch("ahmed"));
    }

    @Test
    public void test_mxxstafa() {
        assertTrue(matcher.hasMatch("m**stafa"));
    }

    @Test
    public void test_zzzzz() {
        assertTrue(matcher.hasMatch("*****"));
    }

    @Test
    public void test_zzzz() {
        assertTrue(matcher.hasMatch("****"));
    }

    @Test
    public void test_zz() {
        assertFalse(matcher.hasMatch("**"));
    }

    @Test
    public void test_fizoo() {
        assertFalse(matcher.hasMatch("fizoo"));
    }

    @Test
    public void test_fizd() {
        assertFalse(matcher.hasMatch("fizd"));
    }
}

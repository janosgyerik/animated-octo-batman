package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class DictionaryValidWords {
    private static final Set<String> dictionary = new TreeSet<String>();

    static {
        dictionary.add("this");
        dictionary.add("his");
        dictionary.add("is");
        dictionary.add("awe");
        dictionary.add("we");
        dictionary.add("some");
        dictionary.add("awesome");
        dictionary.add("foo");
        dictionary.add("bar");
    }

    private DictionaryValidWords() {}

    /**
     * Returns set of valid words given an input string.
     * It eliminates duplicates.
     *
     * @param str   The input string whose valid words need to be found out.
     * @return      List of valid words nested in the string.
     */
    public static Set<String> findValidStrings(String str) {
//        if (str.length() ==  0) {
//            throw new IllegalArgumentException("Strings of length 0 are illegal");
//        }

        final Set<String> validWords = new HashSet<String>();
        for (int i = 0; i < str.length(); i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < str.length(); j++) {
                sb.append(str.charAt(j)); // O(1) complexity.
                if (dictionary.contains(sb.toString())) {
                    validWords.add(sb.toString());
                }
            }
        }
        return validWords;
    }
}

class FindValidWords {
    private static final Set<String> dictionary = new TreeSet<String>();

    static {
        dictionary.add("this");
        dictionary.add("his");
        dictionary.add("is");
        dictionary.add("awe");
        dictionary.add("we");
        dictionary.add("some");
        dictionary.add("awesome");
        dictionary.add("foo");
        dictionary.add("bar");
    }

    public Set<String> findValidWords(String str) {
        final Set<String> validWords = new HashSet<String>();
        for (int i = 0; i < str.length() - 1; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < str.length(); j++) {
                sb.append(str.charAt(j));
                if (dictionary.contains(sb.toString())) {
                    validWords.add(sb.toString());
                }
            }
        }
        return validWords;
    }
}

public class FindValidWordsTest {
    @Test
    public void testValidWords() {
        Set<String> expectedSet = new HashSet<String>(Arrays.asList("awe", "is", "his", "awesome", "some", "this", "we"));
        assertEquals(expectedSet, DictionaryValidWords.findValidStrings("thisisawesome"));
        assertEquals(expectedSet, DictionaryValidWords.findValidStrings("thisisawesomex"));
        assertEquals(expectedSet, DictionaryValidWords.findValidStrings("thisisawesomemagixdrawer"));
        assertEquals(Collections.emptySet().toString(), DictionaryValidWords.findValidStrings("").toString());
    }

    @Test
    public void testFindValidWords() {
        Set<String> expectedSet = new HashSet<String>(Arrays.asList("awe", "is", "his", "awesome", "some", "this", "we"));
        FindValidWords finder = new FindValidWords();
        assertEquals(expectedSet, finder.findValidWords("thisisawesome"));
        assertEquals(expectedSet, finder.findValidWords("thisisawesomex"));
        assertEquals(expectedSet, finder.findValidWords("thisisawesomemagixdrawer"));
        assertEquals(Collections.emptySet().toString(), finder.findValidWords("").toString());
    }
}

package com.janosgyerik.practice.codility.so;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class SameAnagramsTest {
    private List<List<String>> groupAnagramsOrigIsh(List<String> words) {
        List<List<String>> groupedAnagrams = new ArrayList<List<String>>();
        Map<String, String> sortedWords = new HashMap<String, String>();
        for (String word : words) {
            char[] wordToSort = word.toCharArray();
            Arrays.sort(wordToSort);
            sortedWords.put(word, new String(wordToSort));
        }

        Set<Set<String>> sameAnagramsSet = new HashSet<Set<String>>();
        for (Map.Entry<String, String> entry : sortedWords.entrySet()) {
            Set<String> sameAnagrams = new HashSet<String>();
            sameAnagrams.add(entry.getKey());
            for (Map.Entry<String, String> toCompare : sortedWords.entrySet()) {
                if (entry.getValue().equals(toCompare.getValue())) {
                    sameAnagrams.add(toCompare.getKey());
                }
            }
            if (sameAnagrams.size() > 0) {
                sameAnagramsSet.add(sameAnagrams);
            }
        }
        for (Set<String> set : sameAnagramsSet) {
            groupedAnagrams.add(new ArrayList<String>(set));
        }
        return groupedAnagrams;
    }

    private Collection<List<String>> groupAnagrams(Collection<String> words) {
        Map<String, List<String>> groupedAnagramsMap = new HashMap<String, List<String>>();
        for (String word : words) {
            char[] wordToSort = word.toCharArray();
            Arrays.sort(wordToSort);
            String key = new String(wordToSort);
            List<String> anagrams = groupedAnagramsMap.get(key);
            if (anagrams == null) {
                anagrams = new ArrayList<String>();
                groupedAnagramsMap.put(key, anagrams);
            }
            anagrams.add(word);
        }
        return groupedAnagramsMap.values();
    }

    @Test
    public void testExamples() {
        //Assert.assertEquals("[[enslaveidiots], [cosmic, comics]]", groupAnagrams(Arrays.asList("comics", "cosmic", "enslaveidiots")).toString());
        Assert.assertEquals("[[comics, cosmic], [enslaveidiots]]", groupAnagrams(Arrays.asList("comics", "cosmic", "enslaveidiots")).toString());
        //Assert.assertEquals("[[cosmic, comics], [televisionads, enslaveidiots]]", groupAnagrams(Arrays.asList("comics", "cosmic", "enslaveidiots", "televisionads")).toString());
        Assert.assertEquals("[[comics, cosmic], [enslaveidiots, televisionads]]", groupAnagrams(Arrays.asList("comics", "cosmic", "enslaveidiots", "televisionads")).toString());
    }
}

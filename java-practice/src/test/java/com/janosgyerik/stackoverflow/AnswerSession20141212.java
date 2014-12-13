package com.janosgyerik.stackoverflow;

import org.junit.Test;

import java.util.*;

public class AnswerSession20141212 {
    @Test
    public void test() {
        List<String> targetList = Arrays.asList("a", "the", "bird", "animal", "is");
        Map<String, Integer> counts = new HashMap<>(targetList.size());
        for (String word : targetList) {
            counts.put(word, 0);
        }
        String[] lines = new String[]{"the animal is a bird where the animal was not a bird the hello"};

        for (String line : lines) {
            for (String word : line.replaceAll("[!?.,]", "").toLowerCase().split("\\s+")) {
                Integer count = counts.get(word);
                if (count != null) {
                    counts.put(word, count + 1);
                }
            }
        }

        System.out.print(counts.get(targetList.get(0)));
        for (int i = 1; i < targetList.size(); ++i) {
            String word = targetList.get(i);
            System.out.print(" " + counts.get(word));
        }
        System.out.println();
    }
}

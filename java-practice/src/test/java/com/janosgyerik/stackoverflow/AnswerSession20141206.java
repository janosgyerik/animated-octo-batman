package com.janosgyerik.stackoverflow;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class AnswerSession20141206 {
    @Test
    public void test() {
        HashMap<Integer, List<Integer>> testData = new HashMap<>();
        testData.put(1, Arrays.asList(777));

        HashMap<Integer, List<Integer>> testData2 = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : testData.entrySet()) {
            testData2.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        testData2.get(1).add(888);
        System.out.println(testData);
        System.out.println(testData2);
    }

    private String pattern(int num) {
        if (num == 1) {
            return "1";
        }
        String prev = pattern(num - 1);
        return prev + " " + num + " " + prev;
    }

    @Test
    public void test2() {
        assertEquals("1", pattern(1));
        assertEquals("1 2 1", pattern(2));
        assertEquals("1 2 1 3 1 2 1", pattern(3));
        assertEquals("1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 6 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 7 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 6 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1", pattern(7));
    }

}

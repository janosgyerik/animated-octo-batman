package com.janosgyerik.stackoverflow.DCoder;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ConcatMapTest {

    public static List<String> concatMapEntries(Map<String, List<String>> map) {
        List<String> finalList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            for (String string : list) {
                finalList.add(entry.getKey() + " = " + string);
            }
        }
        return finalList;
    }

    public static String concatMapEntriesToString(Map<String, List<String>> map) {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            List<String> list = entry.getValue();
            for (String string : list) {
                builder.append(entry.getKey()).append(" = ").append(string).append(", ");
            }
        }
        return builder.toString();
    }

    @Test
    public void testExample() {
        Map<String, List<String>> map1 = new HashMap<>();

        List<String> map11 = new ArrayList<>();
        map11.add("a");
        map11.add("b");
        map1.put("1", map11);

        List<String> map12 = new ArrayList<>();
        map12.add("c");
        map12.add("d");
        map1.put("2", map12);

        assertEquals("[1 = a, 1 = b, 2 = c, 2 = d]", concatMapEntries(map1).toString());
        assertEquals("[1 = a, 1 = b, 2 = c, 2 = d]", concatMapEntriesToString(map1));
    }
}

package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.util.SortedMap;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TreeMapTest {
    @Test
    public void testClosest() {
        SortedMap<Integer, String> map = new TreeMap<>();
        map.put(3, "Jack");
        map.put(3, "Mike");
        map.put(2, "Alice");
        map.put(1, "Bob");
        assertEquals("Bob", map.get(map.firstKey()));
    }
}

package com.janosgyerik.obscure;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.WeakHashMap;

import static org.junit.Assert.assertEquals;

public class LinkedHashMapTest {
    @Test
    public void test() {
        WeakHashMap<String, Integer> map = new WeakHashMap<>();
        map.put("hello", 3);
        assertEquals(1, map.size());
    }
}

package com.janosgyerik.codereview.FellowRemi;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class HashMap {
    // Srtting table size to a max of 32, value used to modulus for hash value.
    private final static int TABLE_SIZE = 32;

    HashEntry[] table;

    HashMap() {
        table = new HashEntry[TABLE_SIZE];
    }

    private int calculateHashCode(String key) {
        int mod = key.hashCode() % TABLE_SIZE;
        return mod < 0 ? mod + TABLE_SIZE : mod;
    }

    private int findIndex(String key) {
        int index = calculateHashCode(key);
        while (table[index] != null && !table[index].getKey().equals(key)) {
            index = (index + 1) % TABLE_SIZE;
        }
        return index;
    }

    public int get(String key) {
        int index = findIndex(key);
        return table[index] == null ? -1 : table[index].getValue();
    }

    public void put(String key, int value) {
        table[findIndex(key)] = new HashEntry(key, value);
    }

    private static class HashEntry {
        private String key;
        private int value;

        HashEntry(String key, int value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }
    }

}

public class HashMapTest {
    @Test
    public void example() {
        HashMap map = new HashMap();
        map.put("Wasif", 36100);
        map.put("Stephen Hughes", 22100);
        map.put("Stephen Hughes", 22101);
        assertEquals(22101, map.get("Stephen Hughes"));
        assertEquals(36100, map.get("Wasif"));
        assertEquals(-1, map.get("Wasig"));
    }
}

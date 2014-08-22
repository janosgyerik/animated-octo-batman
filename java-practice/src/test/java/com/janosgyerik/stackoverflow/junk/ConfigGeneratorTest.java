package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class ConfigGenerator<K, V> {
    private final Map<K, Set<V>> lists = new LinkedHashMap<>();

    public ConfigGenerator(Map<K, List<V>> lists) {
        for (Map.Entry<K, List<V>> entry : lists.entrySet()) {
            K key = entry.getKey();
            List<V> list = entry.getValue();
            this.lists.put(key, new TreeSet<>(list));
        }
    }

    public Map<K, V> next() {
        Map<K, V> output = new LinkedHashMap<>();
        Set<V> taken = new HashSet<>();
        for (Map.Entry<K, Set<V>> entry : lists.entrySet()) {
            K key = entry.getKey();
            Set<V> values = entry.getValue();
            for (V value : values) {
                if (!taken.contains(value)) {
                    taken.add(value);
                    values.remove(value);
                    output.put(key, value);
                    break;
                }
            }
        }
        return output;
    }
}

public class ConfigGeneratorTest {
    private String resultToString(Map<String, String> map) {
        return map.toString();
    }

    @Test
    public void testBasicExampleWithSameHosts() {
        Map<String, List<String>> lists = new LinkedHashMap<>();
        lists.put("dc1", Arrays.asList("h1", "h2", "h3", "h4"));
        lists.put("dc2", Arrays.asList("h1", "h2", "h3", "h4"));
        lists.put("dc3", Arrays.asList("h1", "h2", "h3", "h4"));

        ConfigGenerator<String, String> generator = new ConfigGenerator<>(lists);
        assertEquals("{dc1=h1, dc2=h2, dc3=h3}", resultToString(generator.next()));
        assertEquals("{dc1=h2, dc2=h1, dc3=h4}", resultToString(generator.next()));
        assertEquals("{dc1=h3, dc2=h4, dc3=h1}", resultToString(generator.next()));
        assertEquals("{dc1=h4, dc2=h3, dc3=h2}", resultToString(generator.next()));
        assertTrue(generator.next().isEmpty());
    }

    @Test
    public void testExampleWithEmptyHosts() {
        Map<String, List<String>> lists = new LinkedHashMap<>();
        lists.put("dc1", Arrays.asList("h1", "h2", "h3"));
        lists.put("dc2", Arrays.asList());

        ConfigGenerator<String, String> generator = new ConfigGenerator<>(lists);
        assertEquals("{dc1=h1}", resultToString(generator.next()));
        assertEquals("{dc1=h2}", resultToString(generator.next()));
        assertEquals("{dc1=h3}", resultToString(generator.next()));
        assertTrue(generator.next().isEmpty());
    }

    @Test
    public void testExampleWithVariableHosts() {
        Map<String, List<String>> lists = new LinkedHashMap<>();
        lists.put("dc1", Arrays.asList("h1", "h2"));
        lists.put("dc2", Arrays.asList("h1", "h2", "h3"));
        lists.put("dc3", Arrays.asList("h3", "h4", "h5"));

        ConfigGenerator<String, String> generator = new ConfigGenerator<>(lists);
        assertEquals("{dc1=h1, dc2=h2, dc3=h3}", resultToString(generator.next()));
        assertEquals("{dc1=h2, dc2=h1, dc3=h4}", resultToString(generator.next()));
        assertEquals("{dc2=h3, dc3=h5}", resultToString(generator.next()));
        assertTrue(generator.next().isEmpty());
    }
}
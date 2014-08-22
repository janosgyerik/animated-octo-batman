package com.janosgyerik.stackoverflow.trie1;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

class Trie {
    private final Map<Character, HashMap> root = new HashMap<>();
    private final Character END_CHARACTER = '$';

    public Trie(String s) {
        add(s);
    }

    public Trie(Collection<String> collection) {
        for (String s : collection) {
            add(s);
        }
    }

    public void add(String s) throws Error {
        Map<Character, HashMap> node = root;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (!node.containsKey(character)) {
                node.put(character, new HashMap<Character, HashMap>());
            }
            node = node.get(character);
        }
        node.put(END_CHARACTER, new HashMap<>());
    }

    public boolean contains(String s) {
        Map<Character, HashMap> node = root;
        s = s.toLowerCase();
        for (int i = 0; i < s.length(); i++) {
            Character character = s.charAt(i);
            if (node.containsKey(character)) {
                node = node.get(character);
            } else {
                return false;
            }
        }
        return node.containsKey(END_CHARACTER);
    }
}

public class TrieTest {
    @Test
    public void testDummy() {
//        new Trie();
    }
}

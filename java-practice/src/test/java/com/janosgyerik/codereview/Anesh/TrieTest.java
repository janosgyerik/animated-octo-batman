package com.janosgyerik.codereview.Anesh;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TrieTest {

    private Trie trie;

    public TrieTest() {
        trie = new Trie();
        trie.build(new HashSet<>(Arrays.asList("CAT", "CATWOMAN", "CATERER", "DOT", "DOG", "DOC")));
    }

    @Test
    public void test_CAX() {
        assertFalse(trie.search("CAX"));
    }

    @Test
    public void test_CATER() {
        assertTrue(trie.search("CATER"));
    }

    @Test
    public void test_ELVIS() {
        assertFalse(trie.search("ELVIS"));
    }
}



class Trie {

    private static class TrieNode {

        private final char value;
        private final Map<Character, TrieNode> children;

        public TrieNode(char value) {
            this.value = value;
            this.children = new HashMap<>();
        }

        public char getValue() {
            return value;
        }

        public Map<Character, TrieNode> getChildren() {
            return children;
        }
    }

    private final TrieNode root = new TrieNode('$');

    public void build(Set<String> wordSet) {
        for (String word : wordSet) {
            int wordLength = word.length();
            TrieNode crawl = root;
            for (int i = 0; i < wordLength; ++i) {
                crawl = traverse(crawl, word.charAt(i));
            }
        }
    }

    public TrieNode traverse(TrieNode node, char input) {
        TrieNode nextNode = node.getChildren().get(input);
        if (nextNode == null) {
            nextNode = new TrieNode(input);
            node.getChildren().put(input, nextNode);
        }
        return nextNode;
    }

    public boolean search(String word) {
        int wordLength = word.length();
        TrieNode crawler = root;
        for (int i = 0; i < wordLength; ++i) {
            char character = word.charAt(i);
            crawler = crawler.getChildren().get(character);
            if (crawler == null) {
                return false;
            }
        }
        return true;
    }
}
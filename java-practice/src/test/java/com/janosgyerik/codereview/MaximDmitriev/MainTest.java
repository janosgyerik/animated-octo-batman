package com.janosgyerik.codereview.MaximDmitriev;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MainTest {
    private boolean hasMatch(String name) {
        return Main.hasName(name);
    }

    @Test
    public void test_ahmed() {
        assertTrue(hasMatch("ahmed"));
    }

    @Test
    public void test_mxustafa() {
        assertTrue(hasMatch("m*ustafa"));
    }

    @Test
    public void test_mxxstafa() {
        assertTrue(hasMatch("m**stafa"));
    }

    @Test
    public void test_xoxstafa() {
        assertTrue(hasMatch("*o*stafa"));
    }

    @Test
    public void test_zzzzz() {
        assertTrue(hasMatch("*****"));
    }

    @Test
    public void test_zzzz() {
        assertTrue(hasMatch("****"));
    }

    @Test
    public void test_zz() {
        assertFalse(hasMatch("**"));
    }

    @Test
    public void test_fizoo() {
        assertFalse(hasMatch("fizoo"));
    }

    @Test
    public void test_fizd() {
        assertFalse(hasMatch("fizd"));
    }

    @Test
    public void test_haszz() {
        assertTrue(hasMatch("has**"));
    }

}

class Main {

    /*
     * This class offers constant time performance for the basic operations (add, remove, contains
     * and size), assuming the hash function disperses the elements properly among the buckets.
     */
    private static final Set<String> NAMES = new HashSet<>();

    static {
        NAMES.add("hasad");
        NAMES.add("ahmed");
        NAMES.add("moustafa");
        NAMES.add("fizo");
    }

    static boolean generate(String query, int asteriskCount, String prefix) {
        for (char letter = 'a'; letter <= 'z'; letter++) {
            if (asteriskCount > 1) {
                boolean result = generate(query, asteriskCount - 1, prefix + letter);
                if (result) {
                    return true;
                }
            } else {
                char[] queryAsArray = query.toCharArray();
                char[] generated = (prefix + letter).toCharArray();
                for (int i = 0, j = 0; i < queryAsArray.length; i++) {
                    if (queryAsArray[i] == '*') {
                        queryAsArray[i] = generated[j];
                        j++;
                    }
                }
//                System.out.println(new String(queryAsArray));
                if (NAMES.contains(new String(queryAsArray))) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean hasName(String query) {
        int fromIndex = 0;
        int asteriskCount = 0;
        while ((fromIndex = query.indexOf("*", fromIndex)) != -1) {
            asteriskCount++;
            fromIndex++;
        }
        if (asteriskCount == 0) {
            return NAMES.contains(query);
        } else {
            return generate(query, asteriskCount, "");
        }
    }
}

package com.janosgyerik.codereview.shree;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class Pangram {

    private final Set<Character> missingLetters;

    public Pangram(String text) {
        boolean[] lettersUsed = new boolean['z' - 'a' + 1];
        for (char c : text.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                lettersUsed[c - 'a'] = true;
            }
        }

        missingLetters = new HashSet<>();
        for (int i = 0; i < lettersUsed.length; ++i) {
            if (!lettersUsed[i]) {
                missingLetters.add((char) ('a' + i));
            }
        }
    }

    public boolean isPangram() {
        return missingLetters.isEmpty();
    }

    public Set<Character> getMissingAlphabets() {
        return missingLetters;
    }
}

public class PangramTest {
    @Test
    public void checkPangram_Test1(){
        Pangram pangram = new Pangram("The quick brown fox jumps over a lazy dog.");
        assertTrue(pangram.isPangram());
    }

    @Test
    public void checkPangram_Test2(){
        Pangram pangram = new Pangram("The quick red fox jumps over a lazy dog.");
        assertFalse(pangram.isPangram());
    }

    @Test
    public void checkPangram_WithReallyBigString(){
        Pangram pangram = new Pangram("Forsaking monastic tradition, twelve jovial friars gave up their vocation for a questionable existence on the flying trapeze");
        assertTrue(pangram.isPangram());
    }

    @Test
    public void checkPangram_Test3(){
        Pangram pangram = new Pangram("Crazy Fredericka bought many very exquisite opal jewels");
        assertTrue(pangram.isPangram());
    }

    @Test
    public void checkPangram_Test4(){
        Pangram pangram = new Pangram("Honest Fredericka bought many very exquisite opal jewels");
        assertFalse(pangram.isPangram());
    }

    @Test
    public void forPangramStringShouldReturnEmptySet(){
        Pangram pangram = new Pangram("The quick brown fox jumps over a lazy dog.");
        assertTrue(pangram.getMissingAlphabets().isEmpty());
    }

    @Test
    public void forNonPangramStringShouldReturnMissingAlphabets(){
        Pangram pangram = new Pangram("The quick brown fox jumps over busy dog.");
        Set<Character> actual = pangram.getMissingAlphabets();
        Set <Character>expected = new TreeSet<>();
        expected.add('a');
        expected.add('l');
        expected.add('z');

        assertEquals(expected,actual);
    }


    @Test
    public void forNonPangramStringShouldReturnMissingAlphabets_Test2(){
        Pangram pangram = new Pangram(" b cd x rs  ijk pno f vu");
        Set<Character> actual = pangram.getMissingAlphabets();
        Set <Character>expected = new HashSet<>();
        expected.add('a');
        expected.add('e');
        expected.add('g');
        expected.add('h');
        expected.add('l');
        expected.add('m');
        expected.add('q');
        expected.add('t');
        expected.add('w');
        expected.add('y');
        expected.add('z');

        assertEquals(expected,actual);
    }
}

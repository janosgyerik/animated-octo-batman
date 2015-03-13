package com.janosgyerik.codereview.giulio;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class PairsSummingToElementTest {
    @Test
    public void testExample() {
        PairsSummingToElement e = new PairsSummingToElement();
        int[] input = new int[] { 2, 5, 3, 7, 9, 8 };
        int sum = 11;
        assertEquals(new HashSet<>(Arrays.asList(new Pair(2, 9), new Pair(3, 8))), e.findAllPairs(input, sum));
    }
}

class PairsSummingToElement {

    public Set<Pair> findAllPairs(int[] inputList, int sum) {
        Set<Integer> numbers = new HashSet<>(inputList.length);
        for (int num : inputList) {
            numbers.add(num);
        }

        Set<Integer> usedNumbers = new HashSet<>();
        Set<Pair> pairs = new HashSet<>();
        for (int num : inputList) {
            int pair = sum - num;
            if (numbers.contains(pair) && !usedNumbers.contains(pair)) {
                usedNumbers.add(num);
                pairs.add(new Pair(num, pair));
            }
        }

        return pairs;
    }

    public HashSet<Pair> findAllPairs0(int[] inputList, int sum) {
        HashSet<Integer> allElements = new HashSet<Integer>();
        HashSet<Integer> substracted = new HashSet<Integer>();
        HashSet<Pair> result = new HashSet<Pair>();

        for (int i : inputList) {
            allElements.add(i);
            substracted.add(i - sum);
        }

        for (int i : substracted) {
            if (allElements.contains(-1 * i)) {
                addToSet(result, new Pair(-i, i + sum));
            }
        }

        return result;

    }

    public void addToSet(HashSet<Pair> original, Pair toAdd) {
        if (!original.contains(toAdd) && !original.contains(reversePair(toAdd))) {
            original.add(toAdd);
        }
    }

    public Pair reversePair(Pair original) {
        return new Pair(original.getElement2(), original.getElement1());
    }

}

class Pair {
    private int element1;

    private int element2;

    public Pair(int e1, int e2) {
        element1 = e1;
        element2 = e2;
    }

    public int getElement1() {
        return element1;
    }

    public int getElement2() {
        return element2;
    }

    public int hashCode() {
        return (element1 + element2) * element2 + element1;
    }

    public boolean equals(Object other) {
        if (other instanceof Pair) {
            Pair otherPair = (Pair) other;
            return ((this.element1 == otherPair.element1) && (this.element2 == otherPair.element2));
        }
        return false;
    }

}

package com.janosgyerik.codereview.junk;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertArrayEquals;

class GenRandomArray {

    private final Random rand;

    public GenRandomArray() {
        rand = new Random();
    }

    public GenRandomArray(int seed) {
        rand = new Random(seed);
    }

    public int[] generateSorted(final int length, final int minVal, final int maxVal) {
        List<Integer> data = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            int rndNum = getRandomVal(minVal, maxVal);
            data.add(rndNum);
//            int insertionPoint = Collections.binarySearch(data, rndNum);
//            data.add(insertionPoint > -1 ? insertionPoint : -insertionPoint - 1, rndNum);
        }
        Collections.sort(data);

        return data.stream().mapToInt(i -> i).toArray();
    }

    private int getRandomVal(int min, int max) {
        return min + rand.nextInt(max - min + 1);
    }
}

public class GenRandomArrayTest {
    @Test
    public void testGen10_Between_0_and_100() {
        assertArrayEquals(new int[]{5, 9, 42, 54, 67, 72, 82, 84, 93, 98},
                new GenRandomArray(0).generateSorted(10, 0, 100));
    }
}

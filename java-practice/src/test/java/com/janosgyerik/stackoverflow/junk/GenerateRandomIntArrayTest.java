package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;

class GenerateRandomIntArray {

    private static Random random = new Random();

    public static void setRandom(Random random_) {
        random = random_;
    }

    /**
     * @param length length of array to be generated
     * @param minVal minimum value (inclusive) that may be used in array
     * @param maxVal maximum value (inclusive) that may be used in array
     * @return array with random values but in sorted order
     */
    public static int[] generateSorted(final int length, final int minVal, final int maxVal) {
        List<Integer> list = generateRandomList(length, minVal, maxVal);
        Collections.sort(list);
        return listToArray(list);
    }

    public static int[] generateReverseSorted(final int length, final int minVal, final int maxVal) {
        List<Integer> list = generateRandomList(length, minVal, maxVal);
        Collections.sort(list, Collections.reverseOrder());
        return listToArray(list);
    }

    private static int[] listToArray(List<Integer> list) {
        return list.stream().mapToInt(i -> i).toArray();
    }

    /**
     * @param length length of arrayList to generate
     * @param minVal minimum value (inclusive) that can appear in list
     * @param maxVal maximum value (inclusive) that can appear in list
     * @return arrayList containing elements in sorted order
     */
    private static List<Integer> generateRandomList(final int length, final int minVal, final int maxVal) {
        List<Integer> randomList = new ArrayList<>(length);

        for (int i = 0; i < length; i++) {
            int rndNum = getRandomVal(minVal, maxVal);
            randomList.add(rndNum);
        }

        return randomList;
    }

    private static int getRandomVal(int minVal, int maxVal) {
        return minVal + random.nextInt(maxVal - minVal);
    }
}

public class GenerateRandomIntArrayTest {
    @Test
    public void testSorted() {
        GenerateRandomIntArray.setRandom(new Random(0));
        assertArrayEquals(new int[]{3, 3, 3, 4, 5, 7, 7, 8, 9, 9},
                GenerateRandomIntArray.generateSorted(10, 1, 10));
    }

    @Test
    public void testReverseSorted() {
        GenerateRandomIntArray.setRandom(new Random(0));
        assertArrayEquals(new int[]{9, 9, 8, 7, 7, 5, 4, 3, 3, 3},
                GenerateRandomIntArray.generateReverseSorted(10, 1, 10));
    }
}

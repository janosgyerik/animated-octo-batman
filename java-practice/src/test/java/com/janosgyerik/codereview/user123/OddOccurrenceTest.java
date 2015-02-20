package com.janosgyerik.codereview.user123;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class OddOccurrenceTest {
    public static int[] allOdd(int[] array) {
        if (array.length < 2) {
            return array;
        }

        int[] sorted = array.clone();
        Arrays.sort(sorted);

        boolean odd = true;
        int len = 0;
        for (int i = 1; i < sorted.length; ++i) {
            if (sorted[i] == sorted[i - 1]) {
                odd = !odd;
            } else {
                if (odd) {
                    sorted[len++] = sorted[i - 1];
                }
                odd = true;
            }
        }

        if (odd) {
            sorted[len++] = sorted[sorted.length - 1];
        }

        return Arrays.copyOf(sorted, len);
    }

    public static int[] allOdd2(int[] array) {
        Map<Integer, Integer> hm = new HashMap<>();
        for (int num : array) {
            Integer count = hm.get(num);
            if (count == null) {
                count = 0;
            }
            hm.put(num, count + 1);
        }
        List<Integer> allOdds = new ArrayList<>();
        for (int key : hm.keySet()) {
            if ((hm.get(key) & 1) == 1) {
                allOdds.add(key);
            }
        }
        int[] allOs = new int[allOdds.size()];
        for (int c = 0; c < allOdds.size(); c++) {
            allOs[c] = allOdds.get(c);
        }
        return allOs;
    }

    private void assertEqualSet(int[] expected, int[] actual) {
        assertEquals(arrayToSet(expected), arrayToSet(actual));
    }

    private Set<Integer> arrayToSet(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }
        return set;
    }

    @Test
    public void test_empty() {
        assertEqualSet(new int[0], allOdd(new int[0]));
    }

    @Test
    public void test_1() {
        assertEqualSet(new int[]{1}, allOdd(new int[]{1}));
    }

    @Test
    public void test_1_2() {
        assertEqualSet(new int[]{1, 2}, allOdd(new int[]{1, 2}));
    }

    @Test
    public void test_1_1() {
        assertEqualSet(new int[0], allOdd(new int[]{1, 1}));
    }

    @Test
    public void test_1_2_1() {
        assertEqualSet(new int[]{2}, allOdd(new int[]{1, 2, 1}));
    }

    @Test
    public void test_2_3_2_4_4_4_6_8_8() {
        assertEqualSet(new int[]{3, 4, 6}, allOdd(new int[]{2, 3, 2, 4, 4, 4, 6, 8, 8}));
    }

    @Test
    public void test_90_91_91_93_93_93_95_98_98_97() {
        assertEqualSet(new int[]{97, 93, 95, 90}, allOdd(new int[]{90, 91, 91, 93, 93, 93, 95, 98, 98, 97}));
    }
}

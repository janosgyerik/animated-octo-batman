package com.janosgyerik.codereview.SaraChatila;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AlternatingTest {
    public static boolean alternating(int[] sequence) {
        return alternating_cr(sequence);
    }

    public static boolean alternating_cr(int[] sequence) {
        if (sequence.length < 2) {
            return false;
        }
        int prevCompare = Integer.compare(sequence[0], sequence[1]);
        if (prevCompare == 0) {
            return false;
        }
        for (int i = 1; i < sequence.length - 1; ++i) {
            int compare = Integer.compare(sequence[i], sequence[i + 1]);
            if (prevCompare != -compare) {
                return false;
            }
            prevCompare = compare;
        }
        return true;
    }

    public static boolean alternating_orig(int[] sequence) {
        for (int i = 0; i < sequence.length - 2; i++) {
            if (sequence[i] <= sequence[i + 1]) {
                if (sequence[i + 1] <= sequence[i + 2])
                    return false;
            } else {
                if (sequence[i] >= sequence[i + 1])
                    if (sequence[i + 1] >= sequence[i + 2])
                        return false;

            }
        }
        return true;
    }

    @Test
    public void testEmptyList_Is_NotAlternating() {
        assertFalse(alternating(new int[0]));
    }

    @Test
    public void testSingletonList_Is_NotAlternating() {
        assertFalse(alternating(new int[]{1}));
    }

    @Test
    public void test2Items_Increasing_Is_Alternating() {
        assertTrue(alternating(new int[]{1, 2}));
    }

    @Test
    public void test2Items_Decreasing_Is_Alternating() {
        assertTrue(alternating(new int[]{3, 2}));
    }

    @Test
    public void test2Items_Same_Is_NotAlternating() {
        assertFalse(alternating(new int[]{3, 3}));
    }

    @Test
    public void test3Items_Same_Is_NotAlternating() {
        assertFalse(alternating(new int[]{3, 3, 3}));
    }

    @Test
    public void test4Items_Same_Is_NotAlternating() {
        assertFalse(alternating(new int[]{3, 3, 3, 3}));
    }

    @Test
    public void test3Items_Increasing_Is_NotAlternating() {
        assertFalse(alternating(new int[]{3, 4, 5}));
    }

    @Test
    public void test4Items_Increasing_Is_NotAlternating() {
        assertFalse(alternating(new int[]{3, 4, 5, 6}));
    }

    @Test
    public void test3Items_Decreasing_Is_NotAlternating() {
        assertFalse(alternating(new int[]{43, 34, 25}));
    }

    @Test
    public void test4Items_Decreasing_Is_NotAlternating() {
        assertFalse(alternating(new int[]{43, 34, 25, 16}));
    }

    @Test
    public void test4Items_AlternatingThenMonotonic_Is_NotAlternating() {
        assertFalse(alternating(new int[]{43, 34, 35, 16, 16, 16}));
    }

    @Test
    public void test4Items_Alternating_Is_Alternating() {
        assertTrue(alternating(new int[]{43, 34, 35, 16}));
    }

}

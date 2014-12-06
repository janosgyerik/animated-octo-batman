package com.janosgyerik.codereview.user2773985;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;

public class ThreeSumTest {
    int[] maxTwoDups(int[] nums) {
        List<Integer> maxTwoDups = new ArrayList<>(nums.length);
        int prev1 = nums[0];
        int prev2 = nums[1];
        maxTwoDups.add(prev1);
        maxTwoDups.add(prev2);
        for (int i = 2; i < nums.length; ++i) {
            if (nums[i] != prev1) {
                maxTwoDups.add(nums[i]);
                prev1 = prev2;
                prev2 = nums[i];
            }
        }
        int[] result = new int[maxTwoDups.size()];
        for (int i = 0; i < maxTwoDups.size(); ++i) {
            result[i] = maxTwoDups.get(i);
        }
        return result;
    }

    @Test
    public void test() {
        assertArrayEquals(new int[]{1, 2, 3}, maxTwoDups(new int[]{1, 2, 3}));
        assertArrayEquals(new int[]{1, 2, 2, 3}, maxTwoDups(new int[]{1, 2, 2, 3}));
        assertArrayEquals(new int[]{1, 2, 2, 3}, maxTwoDups(new int[]{1, 2, 2, 2, 3}));
        assertArrayEquals(new int[]{1, 1, 2, 3}, maxTwoDups(new int[]{1, 1, 2, 3}));
        assertArrayEquals(new int[]{1, 1, 2, 3}, maxTwoDups(new int[]{1, 1, 1, 2, 3}));
        assertArrayEquals(new int[]{1, 2, 3, 3}, maxTwoDups(new int[]{1, 2, 3, 3}));
        assertArrayEquals(new int[]{1, 2, 3, 3}, maxTwoDups(new int[]{1, 2, 3, 3, 3}));
        assertArrayEquals(new int[]{1, 2, 3, 3}, maxTwoDups(new int[]{1, 2, 3, 3, 3, 3}));
        assertArrayEquals(new int[]{1, 1, 3}, maxTwoDups(new int[]{1, 1, 1, 1, 3 }));
        assertArrayEquals(new int[]{1, 1}, maxTwoDups(new int[]{1, 1, 1, 1 }));
    }
}

package com.janosgyerik.practice.oj.leetcode.medium.ContainerWithMostWater;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContainerWithMostWaterTest {
    private final Solution solution = new Solution();

    private int solve(int ... values) {
        return solution.maxArea(values);
    }

    @Test
    public void empty_gives_0() {
        assertEquals(0, solve());
    }

    @Test
    public void singletonlist_gives_0() {
        assertEquals(0, solve(1));
        assertEquals(0, solve(2));
        assertEquals(0, solve(3));
    }

    @Test
    public void items_2_3_gives_2() {
        assertEquals(2, solve(2, 3));
    }

    @Test
    public void items_3_2_gives_2() {
        assertEquals(2, solve(3, 2));
    }

    @Test
    public void items_1_2_3_gives_3() {
        assertEquals(3, solve(1, 2, 3));
    }

    @Test
    public void items_2_2_2_gives_4() {
        assertEquals(4, solve(2, 2, 2));
    }
}

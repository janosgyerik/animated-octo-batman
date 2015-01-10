package com.janosgyerik.codingame.medium.worstloss;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WorstLossTest2 {
    int solution(int[] curve) {
        return Solution.solution(curve);
    }

    @Test
    public void testExample1() {
        assertEquals(-3, solution(new int[]{3, 2, 4, 2, 1, 5}));
    }

    @Test
    public void testExample2() {
        assertEquals(-4, solution(new int[]{5, 3, 4, 2, 3, 1}));
    }

    @Test
    public void testExample3() {
        assertEquals(0, solution(new int[]{1, 2, 4, 4, 5}));
    }
}

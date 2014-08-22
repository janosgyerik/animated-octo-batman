package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class SumUpToTest {
    public Collection<List<Integer>> getSums(int[] values, int targetSum) {
        List<List<Integer>> solutions = new ArrayList<List<Integer>>();
        return solutions;
    }

    @Test
    public void testExample1() {
        assertEquals(Arrays.asList(Arrays.asList(1, 2, 2), Arrays.asList(1, 4), Arrays.asList(5), Arrays.asList(2, 3)),
                getSums(new int[]{1, 2, 2, 3, 4, 5}, 5));
    }
}

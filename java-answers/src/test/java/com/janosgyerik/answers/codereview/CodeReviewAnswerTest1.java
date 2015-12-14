package com.janosgyerik.answers.codereview;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CodeReviewAnswerTest1 {

    private interface MazeRunner {
        boolean move(int direction);
    }

    @Test
    public void test_0() {
        assertEquals(4, 4);
        assertArrayEquals(new int[0], new int[0]);
        assertTrue(true);
        assertFalse(false);
        assertNull(null);

        MazeRunner runner = mock(MazeRunner.class);
        when(runner.move(1)).thenReturn(true);
    }

    @Test
    public void test_001() {

    }
}

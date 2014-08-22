package com.janosgyerik.stackoverflow;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScreenWrapTest {

    static final int WIDTH = 5;

    public int wrap2(int x) {
        if (x > WIDTH) {
            x = 0;
        } else if (x < 0) {
            x = WIDTH;
        }

        return x;
    }

    public int wrap3(int x) {
        x %= WIDTH + 1;
        if (x < 0) {
            x += WIDTH + 1;
        }
        return x;
    }

    public int wrap(int x) {
        return (x % (WIDTH + 1) + WIDTH + 1) % (WIDTH + 1);
    }

    @Test
    public void testNormalMoves() {
        for (int i = 0; i < WIDTH; ++i) {
            assertEquals(i, wrap(i));
        }
    }

    @Test
    public void testJustBelow() {
        assertEquals(WIDTH, wrap(-1));
    }

    @Test
    public void testFarBelow() {
        assertEquals(WIDTH - 1, wrap(-2));
        assertEquals(WIDTH - 2, wrap(-3));
        assertEquals(WIDTH - 3, wrap(-4));
        assertEquals(WIDTH - 4, wrap(-5));
        assertEquals(WIDTH - 5, wrap(-6));
        assertEquals(WIDTH, wrap(-7));
        assertEquals(WIDTH - 1, wrap(-8));
    }

    @Test
    public void testJustAbove() {
        assertEquals(0, wrap(WIDTH + 1));
    }

    @Test
    public void testFarAbove() {
        assertEquals(1, wrap(WIDTH + 2));
    }
}

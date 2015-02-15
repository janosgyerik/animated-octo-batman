package com.janosgyerik.codereview.RandomMath;

import org.junit.Test;

enum Neighbor {
    North(0, 1),
    NorthEast(1, 1),
    East(1, 0);

    private final int dx;
    private final int dy;

    Neighbor(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
}

public class GameOfLifeTest {
    @Test
    public void test() {
    }
}

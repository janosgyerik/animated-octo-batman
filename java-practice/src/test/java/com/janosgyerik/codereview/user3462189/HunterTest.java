package com.janosgyerik.codereview.user3462189;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class Point {

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Point point = (Point) o;

        if (x != point.x) {
            return false;
        }
        return y == point.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}

class Map {
    private final boolean collision;

    public Map(boolean collision) {
        this.collision = collision;
    }

    public boolean checkCollision(int x, int y) {
        return collision;
    }
}

public class HunterTest {
    private Point runAwayFromHunter(int origX, int origY, boolean origDirection, Map map, int hunterX, int hunterY) {
        int x = origX;
        int y = origY;
        boolean direction = origDirection;

        int dx = x < hunterX ? -1 : 1;
        int dy = y < hunterY ? -1 : 1;

        if (direction) {
            if (map.checkCollision(x + dx, y)) x += dx;
            else if (map.checkCollision(x, y + dy)) y += dy;
        } else {
            if (map.checkCollision(x, y + dy)) y += dy;
            else if (map.checkCollision(x + dx, y)) x += dx;
        }

        return new Point(x, y);
    }

    @Test
    public void test_LowerX_LowerY_True_NoCollision() {
        assertEquals(new Point(1, 1), runAwayFromHunter(1, 1, true, new Map(false), 2, 2));
    }

    @Test
    public void test_LowerX_SameY_True_NoCollision() {
        assertEquals(new Point(1, 1), runAwayFromHunter(1, 1, true, new Map(false), 2, 1));
    }

    @Test
    public void test_SameX_LowerY_True_NoCollision() {
        assertEquals(new Point(1, 1), runAwayFromHunter(1, 1, true, new Map(false), 1, 2));
    }

    @Test
    public void test_LowerX_LowerY_False_NoCollision() {
        assertEquals(new Point(1, 1), runAwayFromHunter(1, 1, false, new Map(false), 2, 2));
    }

    @Test
    public void test_LowerX_SameY_False_NoCollision() {
        assertEquals(new Point(1, 1), runAwayFromHunter(1, 1, false, new Map(false), 2, 1));
    }

    @Test
    public void test_SameX_LowerY_False_NoCollision() {
        assertEquals(new Point(1, 1), runAwayFromHunter(1, 1, false, new Map(false), 1, 2));
    }

    @Test
    public void test_LowerX_LowerY_True_Collision() {
        assertEquals(new Point(0, 1), runAwayFromHunter(1, 1, true, new Map(true), 2, 2));
    }

    @Test
    public void test_LowerX_SameY_True_Collision() {
        assertEquals(new Point(0, 1), runAwayFromHunter(1, 1, true, new Map(true), 2, 1));
    }

    @Test
    public void test_SameX_LowerY_True_Collision() {
        assertEquals(new Point(2, 1), runAwayFromHunter(1, 1, true, new Map(true), 1, 2));
    }

    @Test
    public void test_LowerX_LowerY_False_Collision() {
        assertEquals(new Point(1, 0), runAwayFromHunter(1, 1, false, new Map(true), 2, 2));
    }

    @Test
    public void test_LowerX_SameY_False_Collision() {
        assertEquals(new Point(1, 2), runAwayFromHunter(1, 1, false, new Map(true), 2, 1));
    }

    @Test
    public void test_SameX_LowerY_False_Collision() {
        assertEquals(new Point(1, 0), runAwayFromHunter(1, 1, false, new Map(true), 1, 2));
    }
}

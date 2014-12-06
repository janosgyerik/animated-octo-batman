package com.janosgyerik.codereview.EthanBierlein;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class Vector {
    private final int x;
    private final int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector add(Vector other) {
        return new Vector(x + other.x, y + other.y);
    }

    public Vector sub(Vector other) {
        return new Vector(x - other.x, y - other.y);
    }

    public Vector mul(Vector other) {
        return new Vector(x * other.x, y * other.y);
    }

//    public void div(Vector vector) {
//        this.x /= vector.x;
//        this.y /= vector.y;
//    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}

public class VectorTest {
    @Test
    public void test() {
        assertEquals("(1, 2)", new Vector(1, 2).toString());
        assertEquals("(4, 6)", new Vector(1, 2).add(new Vector(3, 4)).toString());
        assertEquals("(-1, 0)", new Vector(1, 2).add(new Vector(3, 4)).sub(new Vector(5, 6)).toString());
        assertEquals("(-7, 0)", new Vector(1, 2).add(new Vector(3, 4)).sub(new Vector(5, 6)).mul(new Vector(7, 8)).toString());
    }
}

package com.janosgyerik.codereview.scratch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ScratchTest2 {
    @Test
    public void testCapitalize() {
        assertEquals("Dark", Element.DARK.toString());
    }

    static class StringUtils {
        private StringUtils() {
            // utility class, forbidden constructor
        }

        public static String toCapitalized(String label) {
            return label.substring(0, 1).toUpperCase() + label.substring(1).toLowerCase();
        }
    }

    public enum Element {
        LIGHT, DARK, FIRE, WATER, AIR, EARTH, NEUTRAL;

        @Override
        public String toString() {
            return StringUtils.toCapitalized(super.toString());
        }
    }
}

class BoundedDouble {
    private final double value;

    public BoundedDouble(double value) {
        this.value = value;
    }
}

class Point {
    private double x;
    private double y;

    public Point(double x, double y) {
        constrain("x", x);
        constrain("y", y);
        this.x = x;
        this.y = y;
    }

    // is there a cleaner/shorter way of handling this, such as a direct way of declaring a
    // subtype of double that I could use in method signatures?
    private final void constrain(String name, double val) {
        if ( val < -10 || val > 10 ) {
            throw new IllegalArgumentException(name + " must be between -10 and 10");
        }
    }

    public double getX() { return x; }

    public void setX(double x) {
        constrain("x", x);
        this.x = x;
    }

    public double getY() { return y; }

    public void setY(double y) {
        constrain("y", y);
        this.y = y;
    }

    @Override
    public String toString() {
        return ("[" + x + "," + y + "]");
    }
}
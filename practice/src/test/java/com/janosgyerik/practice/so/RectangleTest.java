package com.janosgyerik.practice.so;

import org.junit.Assert;
import org.junit.Test;

class Rectangle {
    private final int topLeftX;
    private final int topLeftY;
    private final int bottomRightX;
    private final int bottomRightY;

    private Rectangle(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        this.topLeftX = topLeftX;
        this.topLeftY = topLeftY;
        this.bottomRightX = bottomRightX;
        this.bottomRightY = bottomRightY;
    }

    public static Rectangle createWithPoints(int topLeftX, int topLeftY, int bottomRightX, int bottomRightY) {
        return new Rectangle(topLeftX, topLeftY, bottomRightX, bottomRightY);
    }

    public static Rectangle createWithHeightAndWidth(int bottomLeftX, int bottomLeftY, int height, int width) {
        return new Rectangle(bottomLeftX, bottomLeftY + height, bottomLeftX + width, bottomLeftY);
    }

    public int getTopLeftX() {
        return topLeftX;
    }

    public int getTopLeftY() {
        return topLeftY;
    }

    public int getBottomRightX() {
        return bottomRightX;
    }

    public int getBottomRightY() {
        return bottomRightY;
    }

    @Override
    public String toString() {
        return String.format("TopLeftPoint: (%s,%s)  BottomRightPoint: (%s,%s)",
                topLeftX, topLeftY, bottomRightX, bottomRightY);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + topLeftX;
        result = prime * result + topLeftY;
        result = prime * result + bottomRightX;
        result = prime * result + bottomRightY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            Rectangle other = (Rectangle) obj;
            return topLeftX == other.topLeftX
                    && topLeftY == other.topLeftY
                    && bottomRightX == other.bottomRightX
                    && bottomRightY == other.bottomRightY;
        }
        return false;
    }

    public Rectangle getIntersectingRectangle(Rectangle other) {
        if (!intersects(other)) {
            throw new IllegalArgumentException("The rectangles don't intersect.");
        }

        return Rectangle.createWithPoints(
                Math.max(topLeftX, other.getTopLeftX()),
                Math.min(topLeftY, other.getTopLeftY()),
                Math.min(bottomRightX, other.getBottomRightX()),
                Math.max(bottomRightY, other.getBottomRightY()));
    }

    private boolean intersects(Rectangle other) {
        int maxLeftX = Math.max(topLeftX, other.getTopLeftX());
        int minTopY = Math.min(topLeftY, other.getTopLeftY());
        int minRightX = Math.min(bottomRightX, other.getBottomRightX());
        int maxBottomY = Math.max(bottomRightY, other.getBottomRightY());
        int centerX = (maxLeftX + minRightX) / 2;
        int centerY = (maxBottomY + minTopY) / 2;
        return centerX > topLeftX && centerX < bottomRightX && centerY > bottomRightY && centerY < topLeftY;
    }
}

public class RectangleTest {
    @Test
    public void testOverlappingCorners() {
        // case:  AA
        //        AXB
        //         BB
        Assert.assertEquals(Rectangle.createWithHeightAndWidth(20, 20, 10, 10),
                Rectangle.createWithHeightAndWidth(10, 20, 20, 20).getIntersectingRectangle(
                        Rectangle.createWithHeightAndWidth(20, 10, 20, 20)));

        // case:   BB
        //        AXB
        //        AA
        Assert.assertEquals(Rectangle.createWithHeightAndWidth(20, 30, 10, 10),
                Rectangle.createWithHeightAndWidth(10, 20, 20, 20).getIntersectingRectangle(
                        Rectangle.createWithHeightAndWidth(20, 30, 20, 20)));
    }

    @Test
    public void testOverlappingSides() {
        // case:  AA
        //        XX
        //        BB
        Assert.assertEquals(Rectangle.createWithHeightAndWidth(20, 20, 10, 20),
                Rectangle.createWithHeightAndWidth(20, 20, 20, 20).getIntersectingRectangle(
                        Rectangle.createWithHeightAndWidth(20, 10, 20, 20)));

        // case:  AXB
        //        AXB
        Assert.assertEquals(Rectangle.createWithHeightAndWidth(20, 10, 20, 10),
                Rectangle.createWithHeightAndWidth(10, 10, 20, 20).getIntersectingRectangle(
                        Rectangle.createWithHeightAndWidth(20, 10, 20, 20)));
    }

    @Test
    public void testOverlapAndContain() {
        // case:  AAA
        //        AXX
        //        AXX
        Assert.assertEquals(Rectangle.createWithHeightAndWidth(20, 10, 20, 20),
                Rectangle.createWithHeightAndWidth(10, 10, 30, 30).getIntersectingRectangle(
                        Rectangle.createWithHeightAndWidth(20, 10, 20, 20)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoOverlapIfDisjoint() {
        Rectangle.createWithHeightAndWidth(10, 10, 10, 10).getIntersectingRectangle(
                Rectangle.createWithHeightAndWidth(120, 120, 20, 20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoOverlapByTouchingSides() {
        Rectangle.createWithHeightAndWidth(10, 10, 10, 10).getIntersectingRectangle(
                Rectangle.createWithHeightAndWidth(20, 10, 20, 20));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNoOverlapByTouchingCorners() {
        Rectangle.createWithHeightAndWidth(10, 10, 10, 10).getIntersectingRectangle(
                Rectangle.createWithHeightAndWidth(20, 20, 20, 20));
    }
}



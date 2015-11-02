package com.janosgyerik.practice.codility.so;

import org.junit.Assert;
import org.junit.Test;

class Rectangle {
    private final int left;
    private final int top;
    private final int right;
    private final int bottom;

    private Rectangle(int left, int top, int right, int bottom) {
        if (left >= right) {
            throw new IllegalArgumentException("Width must be positive");
        }
        if (bottom >= top) {
            throw new IllegalArgumentException("Height must be positive");
        }
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
    }

    public static Rectangle createWithPoints(int left, int top, int right, int bottom) {
        return new Rectangle(left, top, right, bottom);
    }

    public static Rectangle createWithHeightAndWidth(int left, int bottom, int height, int width) {
        return new Rectangle(left, bottom + height, left + width, bottom);
    }

    public int getLeft() {
        return left;
    }

    public int getTop() {
        return top;
    }

    public int getRight() {
        return right;
    }

    public int getBottom() {
        return bottom;
    }

    @Override
    public String toString() {
        return String.format("TopLeftPoint: (%s,%s)  BottomRightPoint: (%s,%s)",
                left, top, right, bottom);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + left;
        result = prime * result + top;
        result = prime * result + right;
        result = prime * result + bottom;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rectangle) {
            Rectangle other = (Rectangle) obj;
            return left == other.left
                    && top == other.top
                    && right == other.right
                    && bottom == other.bottom;
        }
        return false;
    }

    public Rectangle getIntersectingRectangle(Rectangle other) {
        if (!intersects(other)) {
            throw new IllegalArgumentException("The rectangles don't intersect.");
        }

        return Rectangle.createWithPoints(
                Math.max(left, other.getLeft()),
                Math.min(top, other.getTop()),
                Math.min(right, other.getRight()),
                Math.max(bottom, other.getBottom()));
    }

    private boolean intersects(Rectangle other) {
        int maxLeftX = Math.max(left, other.getLeft());
        int minTopY = Math.min(top, other.getTop());
        int minRightX = Math.min(right, other.getRight());
        int maxBottomY = Math.max(bottom, other.getBottom());
        int centerX = (maxLeftX + minRightX) / 2;
        int centerY = (maxBottomY + minTopY) / 2;
        return centerX > left && centerX < right && centerY > bottom && centerY < top;
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

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidWidth() {
        Rectangle.createWithHeightAndWidth(10, 10, 10, 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidHeight() {
        Rectangle.createWithHeightAndWidth(10, 10, 0, 10);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBottom() {
        Rectangle.createWithPoints(20, 20, 30, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidRight() {
        Rectangle.createWithPoints(20, 20, 20, 10);
    }
}



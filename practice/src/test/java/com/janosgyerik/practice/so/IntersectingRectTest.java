package com.janosgyerik.practice.so;

import org.junit.Assert;
import org.junit.Test;

public class IntersectingRectTest {
    @Test
    public void testRectangles() {
        //  top-left corner, of right rectangle intersects
        Assert.assertEquals(new RectangleWithPoints(15, 25, 25, 5),
                OverlappingRectangles.fecthIntersectingRectangle(new RectangleWithPoints(10, 35, 25, 15),
                        new RectangleWithPoints(15, 25, 35, 5)));

        // bottom-left corner of right rectangle intersects
        Assert.assertEquals(new RectangleWithPoints(20, 40, 30, 30),
                OverlappingRectangles.fecthIntersectingRectangle(new RectangleWithPoints(10, 40, 30, 20),
                        new RectangleWithPoints(20, 50, 40, 30)));

        //  top-left corner, of right rectangle intersects
        Assert.assertEquals(new RectangleWithHeightAndWidth(20, 20, 10, 10),
                OverlappingRectangles.fetchIntersectingRectangle(new RectangleWithHeightAndWidth(10, 20, 20, 20),
                        new RectangleWithHeightAndWidth(20, 10, 20, 20)));

        // bottom-left corner of right rectangle intersects
        Assert.assertEquals(new RectangleWithHeightAndWidth(20, 30, 10, 10),
                OverlappingRectangles.fetchIntersectingRectangle(new RectangleWithHeightAndWidth(10, 20, 20, 20),
                        new RectangleWithHeightAndWidth(20, 30, 20, 20)));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNonIntersectingRectangles() {
        OverlappingRectangles.fetchIntersectingRectangle(new RectangleWithHeightAndWidth(10, 20, 20, 20),
                new RectangleWithHeightAndWidth(120, 130, 20, 20));
    }
}

final class RectangleWithPoints {
    private final int leftTopX;
    private final int leftTopY;

    private final int rightBottomX;
    private final int rightBottomY;

    RectangleWithPoints(int leftTopX, int leftTopY, int rightBottomX, int rightBottomY) {
        this.leftTopX = leftTopX;
        this.leftTopY = leftTopY;
        this.rightBottomX = rightBottomX;
        this.rightBottomY = rightBottomY;
    }

    public int getTopLeftX() {
        return leftTopX;
    }

    public int getTopLeftY() {
        return leftTopY;
    }

    public int getBottomRightX() {
        return rightBottomX;
    }

    public int getBottomRightY() {
        return rightBottomY;
    }

    @Override
    public String toString() {
                /*
                 * http://stackoverflow.com/questions/1532461/stringbuilder-vs-string-concatenation-in-tostring-in-java
                 */
        return "TopLeftPoint: (" + leftTopX + "," + leftTopY + ")" + "  BottomRightPoint: (" + rightBottomX + "," + rightBottomY + ")";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + leftTopX;
        result = prime * result + leftTopY;
        result = prime * result + rightBottomX;
        result = prime * result + rightBottomY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof RectangleWithPoints) {
            RectangleWithPoints other = (RectangleWithPoints) obj;
            return leftTopX == other.leftTopX
                    && leftTopY == other.leftTopY
                    && rightBottomX == other.rightBottomX
                    && rightBottomY == other.rightBottomY;
        }
        return false;
    }
}

final class RectangleWithHeightAndWidth {
    private final int leftBottomX;
    private final int leftBottomY;
    private final int height;
    private final int width;

    RectangleWithHeightAndWidth(int leftBottomX, int leftBottomY, int height, int width) {
        this.leftBottomX = leftBottomX;
        this.leftBottomY = leftBottomY;
        this.height = height;
        this.width = width;
    }

    public int getLeftBottomX() {
        return leftBottomX;
    }

    public int getLeftBottomY() {
        return leftBottomY;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


    @Override
    public String toString() {
                /*
                 * http://stackoverflow.com/questions/1532461/stringbuilder-vs-string-concatenation-in-tostring-in-java
                 */
        return "BottomLeftPoint: (" + leftBottomX + "," + leftBottomY + "), Height: " + height + " Width: " + width;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + height;
        result = prime * result + leftBottomX;
        result = prime * result + leftBottomY;
        result = prime * result + width;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        RectangleWithHeightAndWidth other = (RectangleWithHeightAndWidth) obj;
        if (height != other.height)
            return false;
        if (leftBottomX != other.leftBottomX)
            return false;
        if (leftBottomY != other.leftBottomY)
            return false;
        if (width != other.width)
            return false;
        return true;
    }
}

final class OverlappingRectangles {

    private OverlappingRectangles() {
    }

    private static boolean intersects(RectangleWithPoints rect1, RectangleWithPoints rect2) {
        // is one rectangle on left side of other
        if (rect1.getBottomRightX() < rect2.getTopLeftX() || rect2.getBottomRightX() < rect1.getTopLeftX()) {
            return false;
        }

        // is one of the rectangle is below the other.
        if (rect1.getTopLeftY() < rect2.getBottomRightY() || rect2.getTopLeftY() < rect1.getBottomRightY()) {
            return false;
        }

        return true;
    }


    /**
     * Given two overlapping rectangles, returns the intersecting rectangle.
     * If rectangles are not overlapping then exception is thrown.
     * It is clients responsibility to pass in the valid Rectangle object, else results are unpredictable.
     *
     * @param rect1 The rectangle
     * @param rect2 The second rectangle
     * @return The intersecting rectangle.
     * @throws IllegalArgumentException if it occurs.
     */
    public static RectangleWithPoints fecthIntersectingRectangle(RectangleWithPoints rect1, RectangleWithPoints rect2) {
        if (!intersects(rect1, rect2)) throw new IllegalArgumentException("The rectangles cannot intersect.");

        RectangleWithPoints rectLeft;
        RectangleWithPoints rectRight;

        if (rect1.getTopLeftX() < rect2.getTopLeftX()) {
            rectLeft = rect1;
            rectRight = rect2;
        } else {
            rectLeft = rect2;
            rectRight = rect1;
        }

        // check if the 'top y' axis of 'right' rectangle falls in range of 'height' of 'left' rectangle
        if (rectRight.getTopLeftY() < rectLeft.getTopLeftY() && rectRight.getTopLeftY() > rectLeft.getBottomRightY()) {
            return new RectangleWithPoints(rectRight.getTopLeftX(), rectRight.getTopLeftY(), rectLeft.getBottomRightX(), rectRight.getBottomRightY());
        }
        // means the 'bottom y' axis of the 'right' rectangle falls in the range of 'hieght' of 'left rantangle'
        return new RectangleWithPoints(rectRight.getTopLeftX(), rectLeft.getTopLeftY(), rectLeft.getBottomRightX(), rectRight.getBottomRightY());
    }

    private static boolean intersects(RectangleWithHeightAndWidth rect1, RectangleWithHeightAndWidth rect2) {
        // is one rectangle on left side of other
        if ((rect1.getLeftBottomX() + rect1.getWidth() < rect2.getLeftBottomX())
                || (rect2.getLeftBottomX() + rect2.getWidth() < rect1.getLeftBottomX())) {
            return false;
        }

        // is one rectangle is below the other
        if ((rect1.getLeftBottomY() + rect1.getHeight() < rect2.getLeftBottomY()) ||
                (rect2.getLeftBottomY() + rect2.getHeight() < rect1.getLeftBottomY())) {
            return false;
        }

        return true;
    }


    /**
     * Given two overlapping rectangles, returns the intersecting rectangle.
     * If rectangles are not overlapping then exception is thrown.
     * It is clients responsibility to pass in the valid Rectangle object, else results are unpredictable.
     *
     * @param rect1 The rectangle
     * @param rect2 The second rectangle
     * @return The intersecting rectangle.
     * @throws IllegalArgumentException if it occurs.
     */
    public static RectangleWithHeightAndWidth fetchIntersectingRectangle(RectangleWithHeightAndWidth rect1, RectangleWithHeightAndWidth rect2) {
        if (!intersects(rect1, rect2)) throw new IllegalArgumentException("The rectangles cannot intersect.");

        RectangleWithHeightAndWidth rectLeft = null;
        RectangleWithHeightAndWidth rectRight = null;

        if (rect1.getLeftBottomX() < rect2.getLeftBottomX()) {
            rectLeft = rect1;
            rectRight = rect2;
        } else {
            rectLeft = rect2;
            rectRight = rect1;
        }


        int rectRightSmallX = rectRight.getLeftBottomX();
        int rectRightTopY = rectRight.getLeftBottomY() + rectRight.getHeight();
        int rectRightBottomY = rectRight.getLeftBottomY();

        int rectLeftBigX = rectLeft.getLeftBottomX() + rectLeft.getWidth();
        int rectLeftTopY = rectLeft.getLeftBottomY() + rectLeft.getHeight();
        int rectLeftBottomY = rectLeft.getLeftBottomY();

        // check if the 'top y' axis of 'right' rectangle falls in range of 'height' of 'left' rectangle
        if ((rectRightTopY < rectLeftTopY) && (rectRightTopY > rectLeftBottomY)) {
            return new RectangleWithHeightAndWidth(rectRightSmallX,
                    rectLeftBottomY,
                    rectRightTopY - rectLeftBottomY,
                    rectLeftBigX - rectRightSmallX);
        } else {
            return new RectangleWithHeightAndWidth(rectRightSmallX,
                    rectRightBottomY,
                    rectLeftTopY - rectRightBottomY,
                    rectLeftBigX - rectRightSmallX);
        }
    }

}

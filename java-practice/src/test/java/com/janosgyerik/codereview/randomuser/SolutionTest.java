package com.janosgyerik.codereview.randomuser;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class SolutionTest {
    @Test
    public void test_overlap_bottom_contained() {
        assertEquals(24, totalArea(-2, -2, 2, 2, -3, -3, 3, -1));
    }

    @Test
    public void test_overlap_bottom_right() {
        assertEquals(19, totalArea(-2, -2, 2, 2, 1, -3, 3, -1));
    }

    @Test
    public void test_no_overlap() {
        assertEquals(5, totalArea(0, 0, 2, 2, 4, 0, 5, 1));
    }

    @Test
    public void test_touching_edge() {
        assertEquals(5, totalArea(0, 0, 2, 2, 2, 0, 3, 1));
    }

    @Test
    public void test_first_contains_second() {
        assertEquals(4, totalArea(0, 0, 2, 2, 1, 1, 2, 2));
    }

    @Test
    public void test_second_contains_first() {
        assertEquals(9, totalArea(0, 0, 2, 2, 0, 0, 3, 3));
    }

    @Test
    public void test_second_same_as_first() {
        assertEquals(4, totalArea(0, 0, 2, 2, 0, 0, 2, 2));
    }

    @Test
    public void test_overlapping_corner() {
        assertEquals(7, totalArea(0, 0, 2, 2, 1, 1, 3, 3));
    }

    @Test
    public void test_overlapping_edge() {
        assertEquals(6, totalArea(0, 0, 2, 2, 1, 0, 3, 2));
    }

    private int totalArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        Rectangle r1 = new Rectangle(A, B, C, D);
        Rectangle r2 = new Rectangle(E, F, G, H);
        return r1.area() + r2.area() - r1.overlap(r2);
    }

    static class Point {
        private final int x;
        private final int y;

        private Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Rectangle {
        private final Point bottomLeft;
        private final Point topRight;
        private final Collection<Point> corners;

        private Rectangle(Point bottomLeft, Point topRight) {
            this.bottomLeft = bottomLeft;
            this.topRight = topRight;
            corners = Arrays.asList(
                    bottomLeft,
                    new Point(bottomLeft.x, topRight.y),
                    new Point(topRight.x, bottomLeft.y),
                    topRight
            );
        }

        public Rectangle(int a, int b, int c, int d) {
            this(new Point(a, b), new Point(c, d));
        }

        private int overlap(Rectangle other) {
            Collection<Point> otherCornersInThis = getContainedCorners(other);
            Collection<Point> thisCornersInOther = other.getContainedCorners(this);
            if (otherCornersInThis.size() == 4) {
                return other.area();
            }
            if (thisCornersInOther.size() == 4) {
                return area();
            }
            if (thisCornersInOther.size() == 0 && otherCornersInThis.size() == 0) {
                return 0;
            }
            Collection<Point> points = new ArrayList<>();
            points.addAll(otherCornersInThis);
            points.addAll(thisCornersInOther);
            return create(new ArrayList<>(points)).area();
        }

        private Rectangle create(List<Point> points) {
            Collections.sort(points, new Comparator<Point>() {
                @Override
                public int compare(Point o1, Point o2) {
                    int xdiff = o1.x - o2.x;
                    if (xdiff != 0) {
                        return xdiff;
                    }
                    return o1.y - o2.y;
                }
            });
            return new Rectangle(points.get(0), points.get(points.size() - 1));
        }

        private int area() {
            return Math.abs(topRight.x - bottomLeft.x) * Math.abs(topRight.y - bottomLeft.y);
        }

        private Collection<Point> getContainedCorners(Rectangle other) {
            Collection<Point> containedCorners = new ArrayList<>();
            for (Point point : other.corners) {
                if (contains(point)) {
                    containedCorners.add(point);
                }
            }
            return containedCorners;
        }

        private boolean contains(Point point) {
            return bottomLeft.x <= point.x && point.x <= topRight.x
                    && bottomLeft.y <= point.y && point.y <= topRight.y;
        }
    }
}

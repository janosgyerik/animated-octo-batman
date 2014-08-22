package com.janosgyerik.stackoverflow;

import org.junit.Test;

import java.util.ArrayList;


enum Quadrant {
    NORTHEAST(1), NORTHWEST(2), SOUTHWEST(3), SOUTHEAST(4);
    int value;

    private Quadrant(int value) {
        // not sure what the point of this is
        this.value = value;
    }
}

enum ColliderType {

}

interface iCollider {
    void update(double delta, QuadTree current);

    void resetUpdate();

    Circle getBody();

    ColliderType getType();
}

class SFAServer {

    public static int quadCapacity;
}

class QuadTree {
    final Point center;
    final Double halfDimension;
    final Double northBound, eastBound, southBound, westBound;
    final ArrayList<iCollider> Objects;
    private QuadTree root, parent, northEast, southWest, northWest, southEast;

    public QuadTree(Point center, Double halfDimension, QuadTree parent) {
        this.center = center;
        this.halfDimension = halfDimension;
        northBound = center.y + halfDimension;
        southBound = center.y - halfDimension;
        eastBound = center.x + halfDimension;
        westBound = center.x - halfDimension;
        this.parent = parent;
        northEast = null;
        southWest = null;
        northWest = null;
        southEast = null;
        Objects = new ArrayList<>();
        if (this.parent == null) {
            root = this;
        } else {
            root = parent.root;
        }
    }

    public ArrayList<iCollider> getColliders(Circle area) {

        ArrayList<iCollider> results = new ArrayList<>();
        for (iCollider c : Objects) {
            if (Point.getDistanceBetweenPoints(area.center, c.getBody().center) < (area.radius + c.getBody().radius)) {
                results.add(c);
            }
        }
        if (area.center.x + area.radius > center.x) {
            if (area.center.y + area.radius > center.y && northEast != null) {
                results.addAll(northEast.getColliders(area));
            }
            if (area.center.y - area.radius < center.y && southEast != null) {
                results.addAll(southEast.getColliders(area));
            }
        }
        if (area.center.x - area.radius < center.x) {
            if (area.center.y + area.radius > center.y && northWest != null) {
                results.addAll(northWest.getColliders(area));
            }
            if (area.center.y - area.radius < center.y && southWest != null) {
                results.addAll(southWest.getColliders(area));
            }
        }
        return results;
    }

    ArrayList<QuadTree> getChildren() {
        ArrayList<QuadTree> children = new ArrayList<>();

        for (Quadrant q : Quadrant.values()) {
            if (this.getSubQuadTree(q) != null) {
                children.add(this.getSubQuadTree(q));
            }
        }
        return children;
    }

    public ArrayList<iCollider> getAllColliders() {
        ArrayList<iCollider> results = new ArrayList<>();
        results.addAll(Objects);

        for (QuadTree child : getChildren()) {
            results.addAll(child.getAllColliders());
        }
        return results;
    }

    Quadrant getQuadrant(Point p) {
        if (p.x > center.x) {
            if (p.y > center.y) {
                return Quadrant.NORTHEAST;
                // return northEast;
            } else {
                return Quadrant.NORTHWEST;
                // return northWest;
            }
        } else {
            if (p.y > center.y) {
                return Quadrant.SOUTHEAST;
                // return southEast;
            } else {
                return Quadrant.SOUTHEAST;
                // return southWest;
            }
        }
    }

    void createQuadTreeForQuadrant(Quadrant q) {
        switch (q) {
            case NORTHEAST:
                northEast = new QuadTree(new Point((center.x + halfDimension), (center.y + halfDimension)), (halfDimension / 2), this);
            case NORTHWEST:
                northWest = new QuadTree(new Point((center.x - halfDimension), (center.y + halfDimension)), (halfDimension / 2), this);
            case SOUTHWEST:
                southWest = new QuadTree(new Point((center.x - halfDimension), (center.y - halfDimension)), (halfDimension / 2), this);
            case SOUTHEAST:
                southEast = new QuadTree(new Point((center.x + halfDimension), (center.y - halfDimension)), (halfDimension / 2), this);
            default:
                assert (false);
        }
    }

    QuadTree getSubQuadTree(Quadrant q) {

        switch (q) {
            case NORTHEAST:
                return northEast;
            case NORTHWEST:
                return northWest;
            case SOUTHWEST:
                return southWest;
            case SOUTHEAST:
                return southEast;
            default:
                assert (false);
                return null;
        }
    }

    void removeChild(Quadrant quadrant) {
        switch (quadrant) {
            case NORTHEAST:
                northEast = null;
            case NORTHWEST:
                northWest = null;
            case SOUTHWEST:
                southWest = null;
            case SOUTHEAST:
                southEast = null;
            default:
                assert (false);
        }
    }

    public int numColliders() {
        return Objects.size();
    }

    public boolean insert(iCollider c) {
        if (fitsWithin(c.getBody())) {
            if (Objects.size() < SFAServer.quadCapacity) {
                return Objects.add(c);
            } else {
                Quadrant quadrant = getQuadrant(c.getBody().center);
                QuadTree subQuadTree = getSubQuadTree(quadrant);
                if (subQuadTree == null) {
                    createQuadTreeForQuadrant(quadrant);
                }
                if (subQuadTree.fitsWithin(c.getBody())) {
                    return subQuadTree.insert(c);
                } else {
                    // object does not fit within any of the child QuadTrees
                    this.balance();
                    return Objects.add(c);
                }
            }
        } else {
            if (parent != null) {
                return parent.insert(c);
            } else {
                System.out.println("Error: unable to insert into QuadTree");
                return false;
            }
        }
    }

    private void balance() {

        for (iCollider c : Objects) {
            relocate(c);
        }
    }

    public boolean remove(iCollider c) {
        // not sure if the best case is for it to check itself first or to check
        // the children first
        if (Objects.remove(c)) {
            return true;
        } else {
            if (getSubQuadTree(getQuadrant(c.getBody().center)) != null) {
                return getSubQuadTree(getQuadrant(c.getBody().center)).remove(c);
            }
        }
        return false;
    }

    public void relocate(iCollider c) {
        if (fitsWithin(c.getBody())) {
            if (numColliders() < SFAServer.quadCapacity) {
                return;
            }
        } else {
            assert (remove(c));
            insert(c);
        }
    }

    public boolean fitsWithin(Circle c) {
        if ((c.center.x + c.radius > eastBound) || (c.center.x - c.radius < westBound) || (c.center.x + c.radius > northBound) || (c.center.x + c.radius < southBound)) {
            return false;
        }
        return true;
    }

    public boolean fitsWithin(Point p) {
        if ((p.x > eastBound) || (p.x < westBound) || (p.y > northBound) || (p.x < southBound)) {
            return false;
        }
        return true;
    }

}

class Circle {

    public final Point center;
    public final Double radius;

    public Circle(Point center, Double radius) {
        this.center = center;
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return Math.PI * 2 * radius;
    }
}

class Point {
    public final double x, y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public static double getAngleBetweenPoints(Point point1, Point point2) {
        double angle = Math.toDegrees(Math.atan2(point1.x - point2.x, point1.y - point2.y));
        if (angle < 0) {
            angle += 360;
        }
        return angle;
    }

    public static double getDistanceBetweenPoints(Point point1, Point point2) {
        Double dx = point1.x - point2.x;
        Double dy = point1.y - point2.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static Point getPointfromOffset(Point p, double distance, double offsetAngle) {
        double xOffset = Math.sin(offsetAngle) * distance;
        double yOffset = Math.cos(offsetAngle) * distance;
        Point result = new Point((p.x + xOffset), (p.y + yOffset));
        return result;
    }
}

public class QuadTreeTest {
    enum Move {
        NORTH, SOUTH
    }
    @Test
    public void testEnumValues() {
        System.out.println(Move.NORTH.ordinal());
        System.out.println(Move.SOUTH.ordinal());
        System.out.println(Move.values());
    }
}
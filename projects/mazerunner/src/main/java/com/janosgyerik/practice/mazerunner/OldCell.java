package com.janosgyerik.practice.mazerunner;

class OldCell {
    public final int x;
    public final int y;

    public OldCell(int x, int y) {
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

        OldCell cell = (OldCell) o;

        if (x != cell.x) {
            return false;
        }
        if (y != cell.y) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return String.format("(%s,%s)", x, y);
    }
}

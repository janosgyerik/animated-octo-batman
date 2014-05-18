package com.janosgyerik.stackoverflow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

final class BasinData {

    private final int item;
    private final int count;

    public BasinData(int item, int count) {
        this.item = item;
        this.count = count;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + count;
        result = prime * result + item;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof BasinData) {
            BasinData other = (BasinData) obj;
            return count == other.count && item == other.item;
        }
        return false;
    }
}

final class Basin {

    private Basin() {}

    private static enum Direction {
        NW(-1, -1), N(0, -1), NE(-1, 1), E(0, 1), SE(1, 1), S(1, 0), SW(1, -1), W(-1, 0);

        int rowDelta;
        int colDelta;

        Direction(int rowDelta, int colDelta) {
            this.rowDelta = rowDelta;
            this.colDelta = colDelta;
        }

        public int getRowDelta() {
            return rowDelta;
        }

        public int getColDelta() {
            return colDelta;
        }
    }


    /**
     * Returns the minimum basin.
     * If more than a single minimum basin exists then returns any arbitrary basin.
     *
     * @param m     : the input matrix
     * @return      : returns the basin item and its size.
     */
    public static BasinData getMaxBasin(int[][] m) {
        final List<BasinCount> basinCountList = new ArrayList<BasinCount>();
        final boolean[][] visited = new boolean[m.length][m[0].length];

        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[0].length; j++) {
                if (!visited[i][j]) {
                    basinCountList.add(scan(m, visited, i, j, m[i][j], new BasinCount(0, true, m[i][j])));
                }
            }
        }

        int maxCount = Integer.MIN_VALUE;
        int item = 0;
        for (BasinCount c : basinCountList) {
            if (c.basin) {
                if (c.count > maxCount) {
                    maxCount = c.count;
                    item = c.item;
                }
            }
        }

        return new BasinData(item, maxCount);
    }


    private static class BasinCount {
        int count;
        boolean basin;
        int item;

        BasinCount(int count, boolean basin, int item) {
            this.count = count;
            this.basin = basin;
            this.item = item;
        }
    };


    private static BasinCount scan(int[][] m, boolean[][] visited, int row, int col, int val, BasinCount baseCount) {

        if (row < 0 || row == m.length || col < 0 || col == m[0].length) return baseCount;

        if (m[row][col] < val) {
            baseCount.basin = false;
            return baseCount;
        }

        if (visited[row][col]) {
            return baseCount;
        }

        if (m[row][col] > val) return baseCount;

        visited[row][col] = true;

        baseCount.count++;

        for (Direction dir : Direction.values()) {
            scan(m, visited, row + dir.getRowDelta(), col + dir.getColDelta(), val, baseCount);
        }

        return baseCount;
    }
}

class BasinInfo {
    final int i;
    final int j;
    final int elevation;

    BasinInfo(int i, int j, int elevation) {
        this.i = i;
        this.j = j;
        this.elevation = elevation;
    }
}

class BasinFinder {
    private static final int FLOODFILL_MARKER = Integer.MIN_VALUE;

    public BasinData findBasin(int[][] m) {
        BasinInfo basinInfo = findMinElevation(m);
        return getBasinData(m, basinInfo);
    }

    private BasinInfo findMinElevation(int[][] matrix) {
        int minI = 0;
        int minJ = 0;
        int minValue = matrix[0][0];

        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] < minValue) {
                    minValue = matrix[i][j];
                    minI = i;
                    minJ = j;
                }
            }
        }
        return new BasinInfo(minI, minJ, minValue);
    }

    private BasinData getBasinData(int[][] matrix, BasinInfo basinInfo) {
        int size = getBasinSize(cloneMatrix(matrix), basinInfo.i, basinInfo.j, basinInfo.elevation);
        return new BasinData(basinInfo.elevation, size);
    }

    private int[][] cloneMatrix(int[][] matrix) {
        int[][] newMatrix = new int[matrix.length][];
        for (int i = 0; i < matrix.length; ++i) {
            newMatrix[i] = matrix[i].clone();
        }
        return newMatrix;
    }

    private int getBasinSize(int[][] matrix, int i, int j, int value) {
        if (isValidPosition(matrix, i, j)) {
            if (matrix[i][j] == value) {
                matrix[i][j] = FLOODFILL_MARKER;
                return 1
                        + getBasinSize(matrix, i + 1, j, value)
                        + getBasinSize(matrix, i - 1, j, value)
                        + getBasinSize(matrix, i, j + 1, value)
                        + getBasinSize(matrix, i, j - 1, value)
                        ;
            }
        }
        return 0;
    }

    private boolean isValidPosition(int[][] matrix, int i, int j) {
        return i >= 0 && j >= 0 && i < matrix.length && j < matrix[i].length;
    }
}

public class BasinTest {

    private BasinData getBasinData(int[][] m) {
//        return Basin.getMaxBasin(m);
        return new BasinFinder().findBasin(m);
    }

    @Test
    public void testBlock() {
        int[][] m1 = { {1, 1, 2},
                {1, 1, 3},
                {4, 5, 6}, };
        assertEquals(new BasinData(1, 4), getBasinData(m1));
    }

    @Test
    public void testRandomlyShapedBasin() {
        int[][] m2 = { {1, 1, 1, 1},
                {1, 1, 3, 1},
                {4, 5, 6, 2} };
        assertEquals(new BasinData(1, 7), getBasinData(m2));
    }

    @Test
    public void testSingleElementBasin() {
        int[][] m3 = { {1, 1, 1, 1},
                {1, 1, 3, 1},
                {4, 5, 6, 0} };
        assertEquals(new BasinData(0, 1), getBasinData(m3));
    }

    @Test
    public void testSingleElementBasin2() {
        int[][] m4 = { {1, 0, 0, 1},
                {1, 0, 3, 1},
                {4, 5, 6, 0} };
        assertEquals(new BasinData(0, 3), getBasinData(m4));
    }
}
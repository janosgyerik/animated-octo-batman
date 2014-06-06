package com.janosgyerik.codingame.tron.v2;

import org.junit.Assert;
import org.junit.Test;

public class RectangleGridTest {
    private RectangleGrid createSampleGrid() {
        return new RectangleGrid(30, 20);
    }

    @Test
    public void testContainsPosition() {
        RectangleGrid grid = createSampleGrid();
        Position position = grid.getAnyPosition();
        Assert.assertTrue(grid.containsPosition(position));
        Assert.assertFalse(grid.containsPosition(new Position(-1, -1)));
    }

    @Test
    public void testApplyMoves() {
        RectangleGrid grid = createSampleGrid();
        Position position = grid.getAnyPosition();
        Assert.assertTrue(grid.isAvailablePosition(position));
        grid.addPosition(1, position);
        Assert.assertFalse(grid.isAvailablePosition(position));
    }

    @Test
    public void testPossibleMovesFromAnyPosition() {
        RectangleGrid grid = createSampleGrid();
        Position position = grid.getAnyPosition();
        grid.addPosition(1, position);
        Assert.assertTrue(grid.getPossibleMovesFrom(position).size() > 0);
    }

    @Test
    public void testFourPossibleMovesFromMiddle() {
        RectangleGrid grid = createGridFromString(new String[]{
                "   ",
                " 9 ",
                "   "
        });
        Position position = grid.getFirstPosition(9);
        Assert.assertEquals(4, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testLimitedMovesWhenBlockedByOthers() {
        RectangleGrid grid = createGridFromString(new String[]{
                "   ",
                " 91",
                "   "
        });
        Position position = grid.getFirstPosition(9);
        Assert.assertEquals(3, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testLimitedMovesWhenCorneredByOthers() {
        RectangleGrid grid = createGridFromString(new String[]{
                "  1",
                " 91",
                " 11"
        });
        Position position = grid.getFirstPosition(9);
        Assert.assertEquals(2, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testLimitedMovesWhenCorneredByMultipleOthers() {
        RectangleGrid grid = createGridFromString(new String[]{
                "  1",
                "291",
                "211"
        });
        Position position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testOnePossibleMove() {
        RectangleGrid grid;
        Position position;

        grid = createGridFromString(new String[]{
                "9 ",
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());

        grid = createGridFromString(new String[]{
                " 9",
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());

        grid = createGridFromString(new String[]{
                " ",
                "9",
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());

        grid = createGridFromString(new String[]{
                "9",
                " "
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());

        grid = createGridFromString(new String[]{
                "191",
                "2 2"
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(1, grid.getPossibleMovesFrom(position).size());
    }

    @Test
    public void testRemovePlayer() {
        RectangleGrid grid = createGridFromString(new String[]{
                "  1",
                "291",
        });
        Assert.assertEquals(2, grid.getAvailablePositions().size());

        grid.removePlayer(2);
        Assert.assertEquals(3, grid.getAvailablePositions().size());

        grid.removePlayer(1);
        Assert.assertEquals(5, grid.getAvailablePositions().size());

        grid.removePlayer(9);
        Assert.assertEquals(6, grid.getAvailablePositions().size());

        grid.removePlayer(19);
    }

    @Test
    public void testCountReachablePositionsFrom() {
        RectangleGrid grid;
        Position position;

        grid = createGridFromString(new String[]{
                "9 "
        });
        position = grid.getFirstPosition(9);
        Assert.assertEquals(0, grid.countReachablePositionsFrom(position));
        grid.removePlayer(9);
        Assert.assertEquals(2, grid.countReachablePositionsFrom(position));
    }

    private RectangleGrid createGridFromString(String[] strings) {
        int width = strings[0].length();
        int height = strings.length;
        RectangleGrid grid = new RectangleGrid(width, height);
        for (int i = 0; i < strings.length; ++i) {
            String line = strings[i];
            for (int j = 0; j < line.length(); ++j) {
                if (line.charAt(j) != ' ') {
                    grid.addPosition(line.charAt(j) - '0', new Position(j, i));
                }
            }
        }
        return grid;
    }
}

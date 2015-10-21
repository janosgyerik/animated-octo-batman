package com.janosgyerik.practice.mazerunner;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class MazeSolverTest {

    private final MazeSolver solver = createMazeSolver();

    protected abstract MazeSolver createMazeSolver();

    protected List<Move> solve(String mazeString) {
        GridMaze grid = GridMaze.fromString(mazeString);
        return solver.findPath(grid, grid.findCell(CellType.START), grid.findCell(CellType.GOAL));
    }

    @Test
    public void testTrivialMaze() {
        String mazeString =
                "#####\n" +
                "#G S#\n" +
                "#####\n";
        assertEquals(Arrays.asList(Move.WEST, Move.WEST), solve(mazeString));
    }

    @Test
    public void testSimpleMaze() {
        String mazeString =
                "#####\n" +
                "#G  #\n" +
                "##  #\n" +
                "#S  #\n" +
                "#####\n";
        assertEquals(Arrays.asList(Move.EAST, Move.NORTH, Move.NORTH, Move.WEST), solve(mazeString));
    }

}

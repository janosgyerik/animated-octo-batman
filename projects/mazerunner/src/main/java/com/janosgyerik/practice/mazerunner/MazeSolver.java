package com.janosgyerik.practice.mazerunner;

import java.util.List;

public interface MazeSolver {
    /**
     * Find path from some start to some target in a given grid.
     *
     * @param grid The grid to search in
     * @param from The start position
     * @param to   The target position
     * @return The list of moves to reach the target
     */
    List<Move> findPath(GridMaze grid, Cell from, Cell to);
}

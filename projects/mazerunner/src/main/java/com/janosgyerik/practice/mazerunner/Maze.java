package com.janosgyerik.practice.mazerunner;

import java.util.*;

public class Maze {
    private final Grid grid;

    public Maze(Grid grid) {
        this.grid = grid;
    }

    public static Maze fromString(String string) {
        Grid grid = Grid.fromString(string);
        return new Maze(grid);
    }

    public List<Move> solveUsingDFS() {
        return grid.findPathDFS(grid.findCell(CellType.START), grid.findCell(CellType.GOAL));
    }

    public List<Move> solveUsingBFS() {
        return grid.findPathBFS(grid.findCell(CellType.START), grid.findCell(CellType.GOAL));
    }
}

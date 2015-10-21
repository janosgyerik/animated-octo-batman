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
}

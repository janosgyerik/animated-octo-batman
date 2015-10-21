package com.janosgyerik.practice.mazerunner;

import java.util.HashSet;
import java.util.Set;

public class MazeSolver {

    public void findTarget(Maze maze) {
        Cell start = new Cell(0, 0);
        Set<Cell> visited = new HashSet<>();
        visited.add(start);
        findTarget(maze, start, visited);
    }

    private boolean findTarget(Maze maze, Cell from, Set<Cell> visited) {
        if (maze.isSuccess()) {
            return true;
        }
        for (Direction direction : Direction.values()) {
            if (maze.move(direction)) {
                Cell next = getCellAfterMove(from, direction);
                if (!visited.contains(next)) {
                    visited.add(next);
                    if (findTarget(maze, next, visited)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public Cell getCellAfterMove(Cell cell, Direction direction) {
        switch (direction) {
            case UP:
                return new Cell(cell.x, cell.y + 1);
            case DOWN:
                return new Cell(cell.x, cell.y - 1);
            case LEFT:
                return new Cell(cell.x - 1, cell.y);
            case RIGHT:
                return new Cell(cell.x + 1, cell.y);
        }
        throw new AssertionError("should be unreachable...");
    }

    private static class Cell {
        private final int x;
        private final int y;

        private Cell(int x, int y) {
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

            Cell cell = (Cell) o;

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


}

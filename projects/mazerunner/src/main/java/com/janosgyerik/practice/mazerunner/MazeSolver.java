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
            Cell next = getCellAfterMove(from, direction);
            if (!visited.contains(next)) {
                visited.add(next);
                if (maze.move(direction)) {
                    if (findTarget(maze, next, visited)) {
                        return true;
                    } else {
                        maze.move(getOppositeDirection(direction));
                    }
                }
            }
        }
        return false;
    }

    private Cell getCellAfterMove(Cell cell, Direction direction) {
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

    private Direction getOppositeDirection(Direction direction) {
        switch (direction) {
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case LEFT:
                return Direction.RIGHT;
            case RIGHT:
                return Direction.LEFT;
        }
        throw new AssertionError("should be unreachable...");
    }


}

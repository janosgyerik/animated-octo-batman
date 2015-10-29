package com.janosgyerik.practice.mazerunner;

import java.util.*;

public class GridMaze implements Maze {

    private final Cell startPosition;
    private final Cell targetPosition;
    private Cell currentPosition;

    private final char[][] grid;
    private final int width;
    private final int height;

    private GridMaze(char[][] grid) {
        this.grid = grid;
        width = grid[0].length;
        height = grid.length;

        currentPosition = startPosition = findCell(CellType.START);
        targetPosition = findCell(CellType.GOAL);
    }

    public static GridMaze fromString(String string) {
        List<String> lines = new ArrayList<>();
        Scanner scanner = new Scanner(string);
        while (scanner.hasNextLine()) {
            lines.add(scanner.nextLine());
        }

        int height = lines.size();

        char[][] grid = new char[height][];
        for (int row = 0; row < grid.length; ++row) {
            grid[row] = lines.get(row).toCharArray();
        }

        return new GridMaze(grid);
    }

    private Cell findCell(CellType cellType) {
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[row].length; ++col) {
                if (grid[row][col] == cellType.symbol) {
                    return new Cell(row, col);
                }
            }
        }
        throw new IllegalArgumentException("No such cell in grid: " + cellType);
    }

    @Override
    public void initialize() {
        currentPosition = startPosition;
    }

    @Override
    public boolean move(Direction direction) {
        int x = currentPosition.x;
        int y = currentPosition.y;
        switch (direction) {
            case UP:
                --x;
                break;
            case DOWN:
                ++x;
                break;
            case LEFT:
                --y;
                break;
            case RIGHT:
                ++y;
                break;
            default:
                throw new IllegalStateException("Unsupported direction: " + direction);
        }
        if (0 <= x && x < height && 0 <= y && y < width && grid[x][y] != CellType.WALL.symbol) {
            currentPosition = new Cell(x, y);
            return true;
        }
        return false;
    }

    @Override
    public boolean isSuccess() {
        return currentPosition.equals(targetPosition);
    }
}

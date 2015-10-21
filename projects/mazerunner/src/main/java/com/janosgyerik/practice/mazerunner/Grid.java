package com.janosgyerik.practice.mazerunner;

import java.util.*;

class Grid {
    private final char[][] grid;
    private final int width;
    private final int height;

    private Grid(char[][] grid) {
        this.grid = grid;
        width = grid[0].length;
        height = grid.length;
    }

    public List<Move> getPossibleMoves(Cell cell) {
        List<Move> moves = new ArrayList<>();
        if (cell.x > 0 && grid[cell.x-1][cell.y] != CellType.WALL.symbol) {
            moves.add(Move.NORTH);
        }
        if (cell.x < height - 1 && grid[cell.x+1][cell.y] != CellType.WALL.symbol) {
            moves.add(Move.SOUTH);
        }
        if (cell.y > 0 && grid[cell.x][cell.y-1] != CellType.WALL.symbol) {
            moves.add(Move.WEST);
        }
        if (cell.y < width - 1 && grid[cell.x][cell.y+1] != CellType.WALL.symbol) {
            moves.add(Move.EAST);
        }
        return moves;
    }

    public Cell getCellAfterMove(Cell from, Move move) {
        switch (move) {
            case NORTH:
                return new Cell(from.x - 1, from.y);
            case SOUTH:
                return new Cell(from.x + 1, from.y);
            case WEST:
                return new Cell(from.x, from.y - 1);
            case EAST:
                return new Cell(from.x, from.y + 1);
        }
        throw new IllegalStateException("Unsupported move: " + move);
    }

    public static Grid fromString(String string) {
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

        return new Grid(grid);
    }

    public Cell findCell(CellType cellType) {
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[row].length; ++col) {
                if (grid[row][col] == cellType.symbol) {
                    return new Cell(row, col);
                }
            }
        }
        throw new IllegalArgumentException("No such cell in grid: " + cellType);
    }
}

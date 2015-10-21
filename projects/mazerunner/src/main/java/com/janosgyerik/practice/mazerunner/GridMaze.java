package com.janosgyerik.practice.mazerunner;

import java.util.*;

public class GridMaze implements Maze {

    private final OldCell startPosition;
    private final OldCell targetPosition;
    private OldCell currentPosition;

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

    public List<Move> getPossibleMoves(OldCell cell) {
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

    public OldCell getCellAfterMove(OldCell from, Move move) {
        switch (move) {
            case NORTH:
                return new OldCell(from.x - 1, from.y);
            case SOUTH:
                return new OldCell(from.x + 1, from.y);
            case WEST:
                return new OldCell(from.x, from.y - 1);
            case EAST:
                return new OldCell(from.x, from.y + 1);
        }
        throw new IllegalStateException("Unsupported move: " + move);
    }

    public OldCell findCell(CellType cellType) {
        for (int row = 0; row < grid.length; ++row) {
            for (int col = 0; col < grid[row].length; ++col) {
                if (grid[row][col] == cellType.symbol) {
                    return new OldCell(row, col);
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
        if (0 <= x && x < width && 0 <= y && y < height && grid[x][y] != CellType.WALL.symbol) {
            currentPosition = new OldCell(x, y);
            return true;
        }
        return false;
    }

    @Override
    public boolean isSuccess() {
        return currentPosition.equals(targetPosition);
    }
}
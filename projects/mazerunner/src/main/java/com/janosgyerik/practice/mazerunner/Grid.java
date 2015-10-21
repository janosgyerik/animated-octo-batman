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

    public List<Move> findPathDFS(Cell from, Cell to, Stack<Move> moves, Set<Cell> visited) {
        if (from.equals(to)) {
            return new ArrayList<Move>(moves);
        }
        for (Move move : getPossibleMoves(from)) {
            Cell cell = getCellAfterMove(from, move);
            if (!visited.contains(cell)) {
                moves.push(move);
                visited.add(cell);
                List<Move> path = findPathDFS(cell, to, moves, visited);
                if (!path.isEmpty()) {
                    return path;
                } else {
                    moves.pop();
                }
            }
        }
        return Collections.emptyList();
    }

    private List<Move> getPossibleMoves(Cell cell) {
        List<Move> moves = new ArrayList<Move>();
        if (cell.x > 0 && grid[cell.x-1][cell.y] != CellType.WALL.symbol) {
            moves.add(Move.UP);
        }
        if (cell.x < height - 1 && grid[cell.x+1][cell.y] != CellType.WALL.symbol) {
            moves.add(Move.DOWN);
        }
        if (cell.y > 0 && grid[cell.x][cell.y-1] != CellType.WALL.symbol) {
            moves.add(Move.LEFT);
        }
        if (cell.y < width - 1 && grid[cell.x][cell.y+1] != CellType.WALL.symbol) {
            moves.add(Move.RIGHT);
        }
        return moves;
    }

    private Cell getCellAfterMove(Cell from, Move move) {
        switch (move) {
            case UP:
                return new Cell(from.x - 1, from.y);
            case DOWN:
                return new Cell(from.x + 1, from.y);
            case LEFT:
                return new Cell(from.x, from.y - 1);
            case RIGHT:
                return new Cell(from.x, from.y + 1);
        }
        throw new IllegalStateException("Unsupported move: " + move);
    }

    public List<Move> findPathDFS(Cell from, Cell to) {
        Stack<Move> moves = new Stack<Move>();
        Set<Cell> visited = new HashSet<Cell>();
        return findPathDFS(from, to, moves, visited);
    }

    public List<Move> findPathBFS(Cell from, Cell to) {
        class CellAndMoves {
            private final Cell cell;
            private final List<Move> moves;

            private CellAndMoves(Cell cell, List<Move> moves) {
                this.cell = cell;
                this.moves = moves;
            }
        }

        Queue<CellAndMoves> queue = new LinkedList<CellAndMoves>();
        queue.add(new CellAndMoves(from, new ArrayList<Move>()));

        Set<Cell> visited = new HashSet<Cell>();
        visited.add(from);

        while (!queue.isEmpty()) {
            List<CellAndMoves> level = new ArrayList<CellAndMoves>(queue);
            queue.clear();
            for (CellAndMoves cellAndMoves : level) {
                for (Move move : getPossibleMoves(cellAndMoves.cell)) {
                    Cell cell = getCellAfterMove(cellAndMoves.cell, move);
                    if (cell.equals(to)) {
                        cellAndMoves.moves.add(move);
                        return cellAndMoves.moves;
                    }
                    if (!visited.contains(cell)) {
                        visited.add(cell);
                        List<Move> moves = new ArrayList<Move>(cellAndMoves.moves);
                        moves.add(move);
                        queue.add(new CellAndMoves(cell, moves));
                    }
                }
            }
        }
        return Collections.emptyList();
    }

    public static Grid fromString(String string) {
        List<String> lines = new ArrayList<String>();
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

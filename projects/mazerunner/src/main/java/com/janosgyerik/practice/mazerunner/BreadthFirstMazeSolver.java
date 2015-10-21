package com.janosgyerik.practice.mazerunner;

import java.util.*;

public class BreadthFirstMazeSolver implements MazeSolver {
    @Override
    public List<Move> findPath(GridMaze grid, Cell from, Cell to) {
        class CellAndMoves {
            private final Cell cell;
            private final List<Move> moves;

            private CellAndMoves(Cell cell, List<Move> moves) {
                this.cell = cell;
                this.moves = moves;
            }
        }

        Queue<CellAndMoves> queue = new LinkedList<>();
        queue.add(new CellAndMoves(from, new ArrayList<>()));

        Set<Cell> visited = new HashSet<>();
        visited.add(from);

        while (!queue.isEmpty()) {
            List<CellAndMoves> level = new ArrayList<>(queue);
            queue.clear();
            for (CellAndMoves cellAndMoves : level)
                for (Move move : grid.getPossibleMoves(cellAndMoves.cell)) {
                    Cell cell = grid.getCellAfterMove(cellAndMoves.cell, move);
                    if (cell.equals(to)) {
                        cellAndMoves.moves.add(move);
                        return cellAndMoves.moves;
                    }
                    if (!visited.contains(cell)) {
                        visited.add(cell);
                        List<Move> moves = new ArrayList<>(cellAndMoves.moves);
                        moves.add(move);
                        queue.add(new CellAndMoves(cell, moves));
                    }
                }
        }
        return Collections.emptyList();
    }
}

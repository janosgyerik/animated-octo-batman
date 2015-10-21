package com.janosgyerik.practice.mazerunner;

import java.util.*;

public class BreadthFirstPathFinderSolver implements PathFinderSolver {
    @Override
    public List<Move> findPath(GridMaze grid, OldCell from, OldCell to) {
        class CellAndMoves {
            private final OldCell cell;
            private final List<Move> moves;

            private CellAndMoves(OldCell cell, List<Move> moves) {
                this.cell = cell;
                this.moves = moves;
            }
        }

        Queue<CellAndMoves> queue = new LinkedList<>();
        queue.add(new CellAndMoves(from, new ArrayList<>()));

        Set<OldCell> visited = new HashSet<>();
        visited.add(from);

        while (!queue.isEmpty()) {
            List<CellAndMoves> level = new ArrayList<>(queue);
            queue.clear();
            for (CellAndMoves cellAndMoves : level)
                for (Move move : grid.getPossibleMoves(cellAndMoves.cell)) {
                    OldCell cell = grid.getCellAfterMove(cellAndMoves.cell, move);
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

package com.janosgyerik.practice.mazerunner;

import java.util.*;

public class DepthFirstPathFinderSolver implements PathFinderSolver {

    @Override
    public List<Move> findPath(GridMaze grid, OldCell from, OldCell to) {
        Stack<Move> moves = new Stack<>();
        Set<OldCell> visited = new HashSet<>();
        return findPathDFS(grid, from, to, moves, visited);
    }

    private List<Move> findPathDFS(GridMaze grid, OldCell from, OldCell to, Stack<Move> moves, Set<OldCell> visited) {
        if (from.equals(to)) {
            return new ArrayList<>(moves);
        }
        for (Move move : grid.getPossibleMoves(from)) {
            OldCell cell = grid.getCellAfterMove(from, move);
            if (!visited.contains(cell)) {
                moves.push(move);
                visited.add(cell);
                List<Move> path = findPathDFS(grid, cell, to, moves, visited);
                if (!path.isEmpty()) {
                    return path;
                } else {
                    moves.pop();
                }
            }
        }
        return Collections.emptyList();
    }
}

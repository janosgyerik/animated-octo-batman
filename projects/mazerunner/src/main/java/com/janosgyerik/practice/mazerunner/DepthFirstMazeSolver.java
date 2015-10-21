package com.janosgyerik.practice.mazerunner;

import java.util.*;

public class DepthFirstMazeSolver implements MazeSolver {

    @Override
    public List<Move> findPath(Grid grid, Cell from, Cell to) {
        Stack<Move> moves = new Stack<>();
        Set<Cell> visited = new HashSet<>();
        return findPathDFS(grid, from, to, moves, visited);
    }

    private List<Move> findPathDFS(Grid grid, Cell from, Cell to, Stack<Move> moves, Set<Cell> visited) {
        if (from.equals(to)) {
            return new ArrayList<>(moves);
        }
        for (Move move : grid.getPossibleMoves(from)) {
            Cell cell = grid.getCellAfterMove(from, move);
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

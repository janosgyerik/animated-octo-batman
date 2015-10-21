package com.janosgyerik.practice.mazerunner;

public class DepthFirstPathFinderSolverTest extends MazeSolverTest {
    @Override
    protected PathFinderSolver createMazeSolver() {
        return new DepthFirstPathFinderSolver();
    }
}
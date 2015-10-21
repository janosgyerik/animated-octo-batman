package com.janosgyerik.practice.mazerunner;

public class DepthFirstPathFinderSolverTest extends PathFinderTest {
    @Override
    protected PathFinderSolver createMazeSolver() {
        return new DepthFirstPathFinderSolver();
    }
}
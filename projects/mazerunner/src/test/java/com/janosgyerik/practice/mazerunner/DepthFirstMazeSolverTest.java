package com.janosgyerik.practice.mazerunner;

import static org.junit.Assert.*;

public class DepthFirstMazeSolverTest extends MazeSolverTest {
    @Override
    protected MazeSolver createMazeSolver() {
        return new DepthFirstMazeSolver();
    }
}
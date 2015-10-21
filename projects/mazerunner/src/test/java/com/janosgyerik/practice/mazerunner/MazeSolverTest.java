package com.janosgyerik.practice.mazerunner;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MazeSolverTest {

    private final MazeSolver solver = new MazeSolver();

    private void solve(String mazeString) {
        Maze maze = GridMaze.fromString(mazeString);
        assertFalse(maze.isSuccess());
        solver.findTarget(maze);
        assertTrue(maze.isSuccess());
    }

    @Test
    public void testTrivialMaze() {
        String mazeString =
                "#####\n" +
                "#G S#\n" +
                "#####\n";
        solve(mazeString);
    }

    @Test
    public void testSimpleMaze() {
        String mazeString =
                "#####\n" +
                "#G  #\n" +
                "##  #\n" +
                "#S  #\n" +
                "#####\n";
        solve(mazeString);
    }

    @Test
    public void testEmptyMaze() {
        String mazeString =
                "######\n" +
                "#G   #\n" +
                "#    #\n" +
                "#   S#\n" +
                "######\n";
        solve(mazeString);
    }

    @Test
    public void testMazeWithDeadEnds() {
        String mazeString =
                "#############\n" +
                "#G    ###   #\n" +
                "#####   ### #\n" +
                "#     #    S#\n" +
                "#############\n";
        solve(mazeString);
    }

    @Test
    public void testComplexMaze() {
        String mazeString =
                "#################\n" +
                "#G#    S        #\n" +
                "# #    #####    #\n" +
                "# ## ###   # # ##\n" +
                "#   ## # # # #  #\n" +
                "### ## # # # #  #\n" +
                "#   #  # # # #  #\n" +
                "#        #      #\n" +
                "#################\n";
        solve(mazeString);
    }
}
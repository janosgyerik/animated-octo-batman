package com.janosgyerik.practice.mazerunner;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class MazeSolverTest {

    private final MazeSolver solver = new MazeSolver();

    private void solve(Maze maze) {
        assertFalse(maze.isSuccess());
        solver.findTarget(maze);
        assertTrue(maze.isSuccess());
    }

    private void solve(List<String> lines) {
        solve(GridMaze.fromLines(lines));
    }

    private void solve(String mazeString) {
        solve(GridMaze.fromString(mazeString));
    }

    @Test
    public void testTrivialMaze() {
        solve(Arrays.asList(
                "#####",
                "#G S#",
                "#####"
        ));
    }

    @Test
    public void testSimpleMaze() {
        String mazeString = "#####\n" +
                "#G  #\n" +
                "##  #\n" +
                "#S  #\n" +
                "#####\n";
        solve(mazeString);
    }

    @Test
    public void testEmptyMaze() {
        String mazeString = "######\n" +
                "#G   #\n" +
                "#    #\n" +
                "#   S#\n" +
                "######\n";
        solve(mazeString);
    }

    @Test
    public void testMazeWithDeadEnds() {
        String mazeString = "#############\n" +
                "#G    ###   #\n" +
                "#####   ### #\n" +
                "#     #    S#\n" +
                "#############\n";
        solve(mazeString);
    }

    @Test
    public void testComplexMaze() {
        String mazeString = "#################\n" +
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
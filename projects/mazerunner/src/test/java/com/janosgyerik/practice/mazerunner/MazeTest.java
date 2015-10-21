package com.janosgyerik.practice.mazerunner;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class MazeTest {
    // no solution
    // only one solution
    // more than one solutions

    @Test
    public void testTrivialMaze() {
        String mazeString =
                "#####\n" +
                "#G S#\n" +
                "#####\n";
        Maze maze = Maze.fromString(mazeString);
        assertEquals(Arrays.asList(Move.LEFT, Move.LEFT), maze.solveUsingBFS());
    }

    @Test
    public void testSimpleMaze() {
        String mazeString =
                "#####\n" +
                "#G  #\n" +
                "##  #\n" +
                "#S  #\n" +
                "#####\n";
        Maze maze = Maze.fromString(mazeString);
        assertEquals(Arrays.asList(Move.RIGHT, Move.UP, Move.UP, Move.LEFT), maze.solveUsingBFS());
    }

    @Test
    public void testEmptyMaze() {
        String mazeString =
                "######\n" +
                "#G   #\n" +
                "#    #\n" +
                "#   S#\n" +
                "######\n";
        Maze maze = Maze.fromString(mazeString);
        assertEquals(Arrays.asList(Move.UP, Move.UP, Move.LEFT, Move.LEFT, Move.LEFT), maze.solveUsingBFS());
    }

    @Test
    public void testMazeWithDeadEnds() {
        String mazeString =
                "#############\n" +
                "#G    ###   #\n" +
                "#####   ### #\n" +
                "#     #    S#\n" +
                "#############\n";
        Maze maze = Maze.fromString(mazeString);
        assertEquals(Arrays.asList(
                Move.LEFT, Move.LEFT, Move.LEFT, Move.LEFT,
                Move.UP,
                Move.LEFT, Move.LEFT,
                Move.UP,
                Move.LEFT, Move.LEFT, Move.LEFT, Move.LEFT
        ), maze.solveUsingBFS());
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
        Maze maze = Maze.fromString(mazeString);
//        assertEquals(Arrays.asList(Move.LEFT, Move.LEFT), maze.solveUsingBFS());
    }
}
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
        assertEquals(Arrays.asList(Move.WEST, Move.WEST), maze.solveUsingBFS());
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
        assertEquals(Arrays.asList(Move.EAST, Move.NORTH, Move.NORTH, Move.WEST), maze.solveUsingBFS());
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
        assertEquals(Arrays.asList(Move.NORTH, Move.NORTH, Move.WEST, Move.WEST, Move.WEST), maze.solveUsingBFS());
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
                Move.WEST, Move.WEST, Move.WEST, Move.WEST,
                Move.NORTH,
                Move.WEST, Move.WEST,
                Move.NORTH,
                Move.WEST, Move.WEST, Move.WEST, Move.WEST
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
//        assertEquals(Arrays.asList(Move.WEST, Move.WEST), maze.solveUsingBFS());
    }
}
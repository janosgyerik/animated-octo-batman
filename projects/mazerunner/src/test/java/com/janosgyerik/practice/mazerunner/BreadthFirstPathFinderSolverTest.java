package com.janosgyerik.practice.mazerunner;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BreadthFirstPathFinderSolverTest extends MazeSolverTest {
    @Override
    protected PathFinderSolver createMazeSolver() {
        return new BreadthFirstPathFinderSolver();
    }

    @Test
    public void testEmptyMaze() {
        String mazeString =
                "######\n" +
                "#G   #\n" +
                "#    #\n" +
                "#   S#\n" +
                "######\n";
        assertEquals(Arrays.asList(Move.NORTH, Move.NORTH, Move.WEST, Move.WEST, Move.WEST), solve(mazeString));
    }

    @Test
    public void testMazeWithDeadEnds() {
        String mazeString =
                "#############\n" +
                "#G    ###   #\n" +
                "#####   ### #\n" +
                "#     #    S#\n" +
                "#############\n";
        assertEquals(
                Arrays.asList(Move.WEST, Move.WEST, Move.WEST, Move.WEST, Move.NORTH, Move.WEST, Move.WEST, Move.NORTH,
                        Move.WEST, Move.WEST, Move.WEST, Move.WEST), solve(mazeString));
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
        List<Move> path = solve(mazeString);
        System.out.println(path);
    }
}
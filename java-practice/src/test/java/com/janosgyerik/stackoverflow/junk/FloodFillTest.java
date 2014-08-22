package com.janosgyerik.stackoverflow.junk;

import java.awt.*;
import java.io.IOException;

public class FloodFillTest {
    public static void main(String[] args) throws IOException {
        String[] grid = new String[]{
                "+++++++++++++++",
                "++--+++++++++++",
                "++---++++++++++",
                "+----++++++++++",
                "++++-++++++++++",
                "+++++++++++++++",
                "++++-++++++++++",
                "+++++++++++++++",
                "+++++++++++++++",
                "++++++++------+",
                "+++++--------++",
                "+++-------+++++",
                "++++--------+++",
                "++++++++---++++",
                "+++++++++++++++"
        };
        Character[][] newGrid = new Character[15][15];

        // so far can print out every line listed above
        for (int x = 0; x < grid.length; x++) {
            // for every line in the grid
            for (int y = 0; y < grid[x].length(); y++) {
                newGrid[x][y] = grid[x].charAt(y);
            }
        }


        // Print out the current grid
        displayGrid(newGrid);

        int holes = 0;
        for (int x = 1; x < newGrid.length; x++) {
            for (int y = 1; y < newGrid[x].length; y++) {
                if (newGrid[x][y] == '-') {
                    ++holes;
                    floodFill(x, y, newGrid);
                }
            }
        }
        System.out.println("The file had " + holes + " cancer spots in it");
        System.out.println("The new grid is:");
        // Print out the new grid
        displayGrid(newGrid);
    }

    public static void floodFill(int row, int col, Character[][] newGrid) {
        if (newGrid[row][col].equals('-')) {
            newGrid[row][col] = ' ';
            floodFill(row - 1, col - 1, newGrid);
            floodFill(row - 1, col, newGrid);
            floodFill(row - 1, col + 1, newGrid);
            floodFill(row, col - 1, newGrid);
            floodFill(row, col + 1, newGrid);
            floodFill(row + 1, col - 1, newGrid);
            floodFill(row + 1, col, newGrid);
            floodFill(row + 1, col + 1, newGrid);
        }

    }

    public static void displayGrid(Character[][] newGrid) {
        String output = "";
        for (int row = 0; row <= 14; row++) {
            for (int col = 0; col <= 14; col++) {
                output += newGrid[row][col];
            }
            output += "\n";
        }
        System.out.println(output);
    }
}

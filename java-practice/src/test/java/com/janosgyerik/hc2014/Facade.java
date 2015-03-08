package com.janosgyerik.hc2014;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Facade {
    static final char BLANK = '.';
    private static final char PAINTED = '#';

    static class Cell {
        final int row;
        final int col;

        Cell(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Cell cell = (Cell) o;

            if (col != cell.col) {
                return false;
            }
            if (row != cell.row) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + col;
            return result;
        }
    }

    final int height;
    final int width;
    final char[][] facade;

    Facade(int height, int width) {
        this.height = height;
        this.width = width;
        this.facade = new char[height][width];

        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                facade[i][j] = BLANK;
            }
        }
    }

    private void updateRow(int row, String s) {
        facade[row] = s.toCharArray();
    }

    public static Facade fromScanner(Scanner scanner) {
        int height = scanner.nextInt();
        int width = scanner.nextInt();
        scanner.nextLine();
        Facade facade = new Facade(height, width);
        for (int i = 0; i < height; ++i) {
            facade.updateRow(i, scanner.nextLine());
        }
        return facade;
    }

    public List<Cell> getPaintedCells() {
        List<Cell> painted = new LinkedList<>();
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < width; ++col) {
                if (isPainted(row, col)) {
                    painted.add(new Cell(row, col));
                }
            }
        }
        return painted;
    }

    private boolean isPainted(int row, int col) {
        return facade[row][col] == PAINTED;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder((width + 1) * (height + 1));
        String newline = String.format("%n");
        builder.append(height).append(' ').append(width).append(newline);
        for (char[] row : facade) {
            builder.append(new String(row)).append(newline);
        }
        return builder.toString();
    }

    public void paintArea(int row, int col, int size) {
        int width = size * 2 + 1;
        int startrow = row - size;
        int startcol = col - size;
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < width; ++j) {
                facade[startrow + i][startcol + j] = PAINTED;
            }
        }
    }

    public void paintCell(int row, int col, char brush) {
        facade[row][col] = brush;
    }
}

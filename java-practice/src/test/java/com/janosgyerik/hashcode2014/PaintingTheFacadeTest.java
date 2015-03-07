package com.janosgyerik.hashcode2014;


import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PaintingTheFacadeTest {

    private static final char BLANK = '.';
    private static final char PAINTED = '#';

    private String generateNaiveSolution(Facade facade) {
        String newline = String.format("%n");

        StringBuilder builder = new StringBuilder();

        List<Facade.Cell> cells = facade.getPaintedCells();
        builder.append(cells.size()).append(newline);

        for (Facade.Cell cell : cells) {
            builder.append(new PaintCommand(cell)).append(newline);
        }
        return builder.toString();
    }

    static class Facade {
        static class Cell {

            final int row;
            final int col;

            Cell(int row, int col) {
                this.row = row;
                this.col = col;
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

    interface Command {
        void apply(Facade facade);
    }

    static class Commands {
        static Command fromString(String s) {
            String[] parts = s.split(" ");
            int[] args = parseArgs(parts);

            String name = parts[0];

            switch (name) {
                case PaintCommand.NAME:
                    return new PaintCommand(args[0], args[1], args[2]);
                case EraseCommand.NAME:
                    return new EraseCommand(args[0], args[1]);
            }
            throw new UnsupportedOperationException("No such command: " + name);
        }

        private static int[] parseArgs(String[] parts) {
            int[] args = new int[parts.length - 1];
            for (int i = 1; i < parts.length; ++i) {
                args[i - 1] = Integer.parseInt(parts[i]);
            }
            return args;
        }
    }

    static class PaintCommand implements Command {
        public static final String NAME = "PAINTSQ";

        private final int row;
        private final int col;
        private final int size;

        public PaintCommand(int row, int col, int size) {
            this.row = row;
            this.col = col;
            this.size = size;
        }

        public PaintCommand(Facade.Cell cell) {
            this.row = cell.row;
            this.col = cell.col;
            this.size = 0;
        }

        @Override
        public void apply(Facade facade) {
            if (size == 0) {
                facade.paintCell(row, col, '#');
            } else {
                facade.paintArea(row, col, size);
            }
        }

        @Override
        public String toString() {
            return String.format("%s %d %d %d", NAME, row, col, size);
        }
    }

    static class EraseCommand implements Command {
        public static final String NAME = "ERASECELL";

        private final int row;
        private final int col;

        public EraseCommand(int row, int col) {
            this.row = row;
            this.col = col;
        }

        @Override
        public void apply(Facade facade) {
            facade.paintCell(row, col, BLANK);
        }
    }

    private String execute(Facade facade, Scanner commands) {
        int commandsCount = commands.nextInt();
        commands.nextLine();
        for (int i = 0; i < commandsCount; ++i) {
            String commandString = commands.nextLine();
            Command command = Commands.fromString(commandString);
            command.apply(facade);
        }
        return facade.toString();
    }

    private final String simpleFacadeString = "5 7\n" +
            "....#..\n" +
            "..###..\n" +
            "..#.#..\n" +
            "..###..\n" +
            "..#....\n";

    private final Facade simpleFacade = Facade.fromScanner(new Scanner(simpleFacadeString));

    @Test
    public void testExecuteSimpleExample() {
        assertEquals(simpleFacadeString, execute(simpleFacade,
                new Scanner("4\n" +
                        "PAINTSQ 2 3 1\n" +
                        "PAINTSQ 0 4 0\n" +
                        "PAINTSQ 4 2 0\n" +
                        "ERASECELL 2 3\n")));
    }

    @Test
    public void testNaiveSolution() {
        assertEquals(simpleFacadeString, execute(simpleFacade,
                new Scanner(generateNaiveSolution(simpleFacade))));
    }
}

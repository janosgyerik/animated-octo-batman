package com.janosgyerik.hc2014;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class PaintingTest {

    private String generateNaiveSolution(Facade facade) {
        String newline = String.format("%n");

        StringBuilder builder = new StringBuilder();

        Set<Facade.Cell> cells = facade.getPaintedCells();
        builder.append(cells.size()).append(newline);

        for (Facade.Cell cell : cells) {
            builder.append(new Commands.PaintCommand(cell)).append(newline);
        }
        return builder.toString();
    }

    private String generateSolutionUsing3x3(Facade facade) {
        String newline = String.format("%n");

        Set<Facade.Cell> originalPaintedCells = facade.getPaintedCells();
        Set<Facade.Cell> paintedCells = new HashSet<>(originalPaintedCells);
        SortedSet<Facade.CellWithPaintedNeighborCount> cellsWithPaintedNeighborCount =
                facade.getCellsWithPaintedNeighborCount(paintedCells, 6);

        List<Commands.Command> commands = new LinkedList<>();
        for (Facade.CellWithPaintedNeighborCount cellWithPaintedNeighborCount : cellsWithPaintedNeighborCount) {
            Facade.Cell cell = cellWithPaintedNeighborCount.cell;
            commands.add(new Commands.PaintCommand(cell.row, cell.col, 1));
            if (!originalPaintedCells.contains(cell)) {
                commands.add(new Commands.EraseCommand(cell.row, cell.col));
            }

            paintedCells.remove(cell);
            for (Facade.Cell neighbor : facade.getValidNeighbors(cell)) {
                paintedCells.remove(neighbor);
                if (!originalPaintedCells.contains(neighbor)) {
                    commands.add(new Commands.EraseCommand(neighbor.row, neighbor.col));
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(commands.size() + paintedCells.size()).append(newline);

        for (Commands.Command command : commands) {
            builder.append(command).append(newline);
        }
        for (Facade.Cell cell : paintedCells) {
            builder.append(new Commands.PaintCommand(cell)).append(newline);
        }
        return builder.toString();
    }

    private String execute(Facade facade, Scanner commands) {
        int commandsCount = commands.nextInt();
        commands.nextLine();
        for (int i = 0; i < commandsCount; ++i) {
            String commandString = commands.nextLine();
            Commands.Command command = Commands.fromString(commandString);
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

    @Test
    public void testSolutionUsing3x3() {
        assertEquals(simpleFacadeString, execute(simpleFacade,
                new Scanner(generateSolutionUsing3x3(simpleFacade))));
    }
}

package com.janosgyerik.hc2014;


import org.junit.Test;

import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

public class PaintingTest {

    private String generateNaiveSolution(Facade facade) {
        String newline = String.format("%n");

        StringBuilder builder = new StringBuilder();

        List<Facade.Cell> cells = facade.getPaintedCells();
        builder.append(cells.size()).append(newline);

        for (Facade.Cell cell : cells) {
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
}

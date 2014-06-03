package com.janosgyerik.stackoverflow.fluxgame;


import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FluxSolver {
    //public static final String DEFAULT_TEMPLATE = "RRRRYYYYBBG_";
    public static final String DEFAULT_TEMPLATE = "RGBYRGBYRGB_";

    private final Map<String, Board> allBoards = Maps.newHashMap();
    private final String template;

    private Set<Board> scoredBoards = Sets.newHashSet();

    public FluxSolver() {
        this(DEFAULT_TEMPLATE);
    }

    public FluxSolver(String template) {
        this.template = template;
    }

    public static void main(String[] a) {
        new FluxSolver().run();
    }

    public void run() {
        computeAllBoards(template);
        solveAllBoards();

        Scanner c = new Scanner(System.in);
        while (c.hasNext()) {
            String input = c.nextLine();
            printSolution(input);
        }

        c.close();
    }

    private void printSolution(String boardString) {
        Board currentBoard = allBoards.get(boardString);
        while (!currentBoard.isSolved()) {
            System.out.print(currentBoard.getDirection().getSymbol());
            currentBoard = currentBoard.getParent();
        }
        System.out.println();
        System.out.print(currentBoard);
    }

    private void computeAllBoards(String template) {
        Preconditions.checkArgument(template.length() == Board.X_SIZE * Board.Y_SIZE,
                "Board size is the wrong length: %s", template.length());
        permuteHelper("", template);
    }

    private void solveAllBoards() {
        for (int score = 0; score < 200; score++) {
            Set<Board> nextBoardIteration = Sets.newHashSet(scoredBoards);

            for (Board currentBoard : scoredBoards) {
                if (currentBoard.getScore() == score) {
                    System.out.println("score=" + score);
                    for (Direction direction : Direction.values()) {
                        Board boardAfterMove =
                                direction.moveSquare(this, currentBoard);
                        if (discoveredNewBoard(nextBoardIteration,
                                boardAfterMove)) {
                            boardAfterMove.setParent(currentBoard, direction);
                            nextBoardIteration.add(boardAfterMove);
                        }
                    }
                }
            }
            scoredBoards = nextBoardIteration;
        }
    }

    private void permuteHelper(String prefix, String remaining) {
        int charsLeft = remaining.length();
        if (charsLeft == 0) {
            Board newBoard = new Board(prefix);
            allBoards.put(prefix, newBoard);
            if (newBoard.isSolved())
                scoredBoards.add(newBoard);
        } else {
            Set<Character> seenCharacters = new HashSet<Character>();
            for (int i = 0; i < charsLeft; i++) {
                char c = remaining.charAt(i);
                if (!seenCharacters.contains(c)) {
                    permuteHelper(prefix + c, remaining.substring(0, i)
                            + remaining.substring(i + 1, charsLeft));
                    seenCharacters.add(c);
                }
            }
        }
    }

    private boolean discoveredNewBoard(Set<Board> nextBoardIteration,
                                       Board boardAfterMove) {
        return boardAfterMove != null
                && !nextBoardIteration.contains(boardAfterMove);
    }

    public Board getBoardFromRawString(String key) {
        return allBoards.get(key);
    }
}
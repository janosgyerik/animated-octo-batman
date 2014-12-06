package com.janosgyerik.codereview.fluxgame;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Board {
    public static final int X_SIZE = 4;
    public static final int Y_SIZE = 3;

    private final Board.CellType[][] boardCells = new Board.CellType[Y_SIZE][X_SIZE];
    private final Position emptyPosition;

    private final String rawString;
    private String prettyString = null;

    private Board parent;
    private Direction direction;
    private int score;

    Board(String rawString) {
        int y, x, raw;
        int emptyX = -1, emptyY = -1, emptyRaw = -1;
        for (y = 0; y < Y_SIZE; y++) {
            for (x = 0; x < X_SIZE; x++) {
                raw = calculateRawPosition(y, x);
                boardCells[y][x] =
                        CellType.getTypeFromSymbol(rawString.charAt(raw));
                if (boardCells[y][x] == CellType.EMPTY) {
                    if (emptyX > -1) {
                        throw new IllegalArgumentException(
                                "Multiple empty cells detected at position "
                                        + raw + ": " + rawString);
                    }
                    emptyX = x;
                    emptyY = y;
                    emptyRaw = raw;
                }
            }
        }

        if (emptyX == -1) {
            throw new IllegalArgumentException("No empty cell detected!");
        } else {
            emptyPosition = new Position(emptyX, emptyY, emptyRaw);
        }

        this.rawString = rawString;
    }

    public static int calculateRawPosition(int y, int x) {
        return X_SIZE * y + x;
    }

    public Position getEmptyPosition() {
        return emptyPosition;
    }

    public boolean isSolved() {
        return Arrays.equals(boardCells[0], boardCells[boardCells.length - 1]);
    }

    public void setParent(Board parent, Direction direction) {
        this.parent = parent;
        this.direction = direction;
        this.score = parent.getScore() + 1;
    }

    @Override
    public String toString() {
        if (prettyString == null) {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < Y_SIZE; i++) {
                for (int j = 0; j < X_SIZE; j++) {
                    builder.append(boardCells[i][j].getSymbol());
                }
                builder.append("\n");
            }
            prettyString = builder.toString();
        }
        return prettyString;
    }

    public String getRawString() {
        return rawString;
    }

    public int getScore() {
        return score;
    }

    public Board getParent() {
        return parent;
    }

    public Direction getDirection() {
        return direction;
    }

    public static class Position {
        public final int x;
        public final int y;
        public final int raw;

        private Position(int x, int y, int raw) {
            this.x = x;
            this.y = y;
            this.raw = raw;
        }
    }

    public static enum CellType {
        EMPTY('_'), GREEN('G'), BLUE('B'), RED('R'), YELLOW('Y');

        private static Map<Character, CellType> reverseMap = new HashMap<Character, CellType>();

        static {
            for (CellType type : values()) {
                reverseMap.put(type.symbol, type);
            }
        }

        private char symbol;

        private CellType(char symbol) {
            this.symbol = symbol;
        }

        public char getSymbol() {
            return symbol;
        }

        public static CellType getTypeFromSymbol(char symbol) {
            return reverseMap.get(symbol);
        }
    }
}


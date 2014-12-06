package com.janosgyerik.codereview.fluxgame;

public enum Direction {
    RIGHT("R") {
        public Board moveSquare(FluxSolver solver, Board input) {
            return input.getEmptyPosition().x == Board.X_SIZE - 1 ? null
                    : moveToEmpty(solver, input, 1);
        }
    },
    LEFT("L") {
        public Board moveSquare(FluxSolver solver, Board input) {
            return input.getEmptyPosition().x == 0 ? null
                    : moveToEmpty(solver, input, -1);
        }
    },
    DOWN("D") {
        public Board moveSquare(FluxSolver solver, Board input) {
            return input.getEmptyPosition().y == Board.Y_SIZE - 1 ? null
                    : moveToEmpty(solver, input, Board.X_SIZE);
        }
    },
    UP("U") {
        public Board moveSquare(FluxSolver solver, Board input) {
            return input.getEmptyPosition().y == 0 ? null
                    : moveToEmpty(solver, input, -Board.X_SIZE);
        }
    };

    private String symbol;

    private Direction(String symbol) {
        this.symbol = symbol;
    }

    public abstract Board moveSquare(FluxSolver solver, Board input);

    Board moveToEmpty(FluxSolver solver, Board board, int emptyOffset) {
        int newPosition = emptyOffset + board.getEmptyPosition().raw;
        char[] s = board.getRawString().toCharArray();
        s[board.getEmptyPosition().raw] = s[newPosition];
        s[newPosition] = Board.CellType.EMPTY.getSymbol();

        return solver.getBoardFromRawString(new String(s));
    }

    public String getSymbol() {
        return symbol;
    }
}
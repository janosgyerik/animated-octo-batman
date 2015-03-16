package com.janosgyerik.practice.tictactoe;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Players {

    private Players() {
        // utility class, forbidden constructor
    }

    private static abstract class AbstractPlayer implements Player {

        private final Symbol symbol;

        private AbstractPlayer(Symbol symbol) {
            this.symbol = symbol;
        }

        @Override
        public Symbol getSymbol() {
            return symbol;
        }

        @Override
        public String toString() {
            return symbol.toString();
        }
    }

    public static Player random(Symbol symbol, Game game) {
        return new AbstractPlayer(symbol) {
            @Override
            public Move getNextMove(Move otherPlayerMove) {
                List<Move> moves = new ArrayList<>(game.getAvailableMoves());
                Collections.shuffle(moves);
                return moves.get(0);
            }
        };
    }

    public static Player centerThenRandom(Symbol symbol, Game game) {
        return new AbstractPlayer(symbol) {
            @Override
            public Move getNextMove(Move otherPlayerMove) {
                Set<Move> moves = game.getAvailableMoves();
                if (moves.contains(Move.CENTER)) {
                    return Move.CENTER;
                }
                List<Move> movesList = new ArrayList<>(moves);
                Collections.shuffle(movesList);
                return movesList.get(0);
            }
        };
    }
}

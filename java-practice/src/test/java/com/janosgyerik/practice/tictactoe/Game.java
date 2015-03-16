package com.janosgyerik.practice.tictactoe;

import java.util.Set;

public class Game {

    private final Board board = new Board();
    private Player[] players;

    public void setPlayers(Player first, Player second) {
        assert first != second;
        assert players == null;
        players = new Player[]{first, second};
    }

    public void play() {
        int round = 0;
        Move otherPlayerMove = null;
        while (true) {
            Player player = players[round++ % players.length];
            Move move = player.getNextMove(otherPlayerMove);
            if (!board.isValidMove(move)) {
                throw new IllegalStateException(String.format("Player %s tried an invalid move: %s", player, move));
            }
            board.applyMove(player, player.getNextMove(otherPlayerMove));
            board.print();

            Board.State state = board.getState();
            if (state.over) {
                System.out.printf("The winner is: %s%n", state.winner);
                break;
            }
            otherPlayerMove = move;
        }
    }

    public Set<Move> getAvailableMoves() {
        return board.getAvailableMoves();
    }
}

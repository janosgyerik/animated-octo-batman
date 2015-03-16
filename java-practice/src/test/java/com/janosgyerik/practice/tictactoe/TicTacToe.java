package com.janosgyerik.practice.tictactoe;

public class TicTacToe {
    public static void main(String[] args) {
        Game game = new Game();
        Player first = Players.random(Symbol.X, game);
        Player second = Players.random(Symbol.O, game);
        game.setPlayers(first, second);
        game.play();
    }
}

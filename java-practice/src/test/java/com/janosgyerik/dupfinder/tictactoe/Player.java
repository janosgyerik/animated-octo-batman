package com.janosgyerik.dupfinder.tictactoe;

public interface Player {

    Symbol getSymbol();

    Move getNextMove(Move otherPlayerMove);
}

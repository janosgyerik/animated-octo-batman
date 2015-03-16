package com.janosgyerik.practice.tictactoe;

public interface Player {

    Symbol getSymbol();

    Move getNextMove(Move otherPlayerMove);
}

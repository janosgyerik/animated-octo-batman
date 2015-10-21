package com.janosgyerik.practice.mazerunner;

enum CellType {
    EMPTY(' '),
    WALL('#'),
    START('S'),
    GOAL('G');

    public final char symbol;

    CellType(char symbol) {
        this.symbol = symbol;
    }
}

package com.janosgyerik.codereview.randomuser.tictacto;


public class Game {

    Main.Symbol[][] table = new Main.Symbol[3][3];

    public void initializeGame() {
        for (int i = 0; i < 3; i++) {
            for (int p = 0; p < 3; p++) {
                table[i][p] = Main.Symbol.EMPTY;
            }
        }
    }

    public boolean checkIfLegal(int row, int column) {
        if ((row > 2 || column > 2) || (row < 0 || column < 0)) {
            return true;
        } else if (table[row][column] == Main.Symbol.X || table[row][column] == Main.Symbol.O) {
            return true;
        }

        return false;
    }

    public void changeBoard(Main.Symbol player, int row, int column) {
        table[row][column] = player;
    }

    public void displayBoard() {
        System.out.println("  0  " + table[0][0] + "|" + table[0][1] + "|" + table[0][2]);
        System.out.println("    --+-+--");
        System.out.println("  1  " + table[1][0] + "|" + table[1][1] + "|" + table[1][2]);
        System.out.println("    --+-+--");
        System.out.println("  2  " + table[2][0] + "|" + table[2][1] + "|" + table[2][2]);
        System.out.println("     0 1 2 ");
    }

    public Main.Symbol changePlayer(Main.Symbol player) {
        return player == Main.Symbol.X ? Main.Symbol.O : Main.Symbol.X;
    }

    public boolean checkIfWinner() {
        if (table[0][0] == table[1][0] && table[1][0] == table[2][0] && (table[0][0] == Main.Symbol.X || table[0][0] == Main.Symbol.O)) {
            return true;
        } else if (table[0][1] == table[1][1] && table[1][1] == table[2][1] && (table[0][1] == Main.Symbol.X || table[0][1] == Main.Symbol.O)) {
            return true;
        } else if (table[0][2] == table[1][2] && table[1][2] == table[2][2] && (table[0][2] == Main.Symbol.X || table[0][2] == Main.Symbol.O)) {
            return true;
        } else if (table[0][0] == table[0][1] && table[0][1] == table[0][2] && (table[0][0] == Main.Symbol.X || table[0][0] == Main.Symbol.O)) {
            return true;
        } else if (table[1][0] == table[1][1] && table[1][1] == table[1][2] && (table[1][0] == Main.Symbol.X || table[1][0] == Main.Symbol.O)) {
            return true;
        } else if (table[2][0] == table[2][1] && table[2][1] == table[2][2] && (table[2][0] == Main.Symbol.X || table[2][0] == Main.Symbol.O)) {
            return true;
        } else if (table[0][0] == table[1][1] && table[1][1] == table[2][2] && (table[0][0] == Main.Symbol.X || table[0][0] == Main.Symbol.O)) {
            return true;
        } else if (table[2][0] == table[1][1] && table[1][1] == table[0][2] && (table[2][0] == Main.Symbol.X ||
                table[2][0] == Main.Symbol.O)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean checkIfTie() {
        for (int i = 0; i < 3; i++) {
            for (int p = 0; p < 3; p++) {
                if (table[i][p] == Main.Symbol.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }
}
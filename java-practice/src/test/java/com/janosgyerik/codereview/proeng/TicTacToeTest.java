package com.janosgyerik.codereview.proeng;

enum PlayerType {
    None("*"),
    Cross("X"),
    Circle("O");

    private final String symbol;

    PlayerType(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }
}

class Board {
    private Square  board[][] = new Square [3][3];
    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = new Square();
            }
        }
    }
    public void setSquare (int row, int col, PlayerType type) {
        board[row][col].setMark(type);
    }
    public Square getSquare(int row, int col) {
        return board[row][col];
    }
}

class Square {
    private PlayerType mark = PlayerType.None;

    public PlayerType getMark() {
        return mark;
    }

    public void setMark(PlayerType mark) {
        this.mark = mark;
    }
}

class Player {
    private PlayerType type;
    private Board board;

    public Player(Board board, PlayerType type) {
        this.type = type;
        this.board = board;
    }
    public void makeMove(int row, int col) {
        board.setSquare(row, col, type);
    }
    public PlayerType getType() {
        return this.type;
    }
}

class MainFrame {
    public MainFrame(Game game) {

    }
}

class Game {
    private final Board board = new Board();
    private final Player crossPlayer = new Player(board, PlayerType.Cross);
    private final Player circlePlayer = new Player(board, PlayerType.Circle);
    private Player currentPlayer;
    private MainFrame frame;

    public Game() {
        frame = new MainFrame(this); //init Gui

        initPlayers();
    }

    private void initPlayers() {
        currentPlayer = crossPlayer;
    }

    public void togglePlayer() {
        if (currentPlayer.equals(crossPlayer)) {
            currentPlayer = circlePlayer;
        } else {
            currentPlayer = crossPlayer;
        }
    }
    /*
        Called when the player clicks on the ith square.
    */
    public void makeMove(int i) {
        //find what row and col, the i:th square lies on.
        int row = i/3;
        int col = i%3;

        currentPlayer.makeMove(row, col);
//        frame.markSquare(i, currentPlayer);
//        frame.disableSquare(i);
        togglePlayer();
        display();
    }

    /**
     * Display the board in the console.
     */
    public void display() {
        PlayerType markType;

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {
                markType = board.getSquare(i, j).getMark();

                    switch (markType) {
                        case None:
                            System.out.println("*");
                        case Cross:
                            System.out.println("X");
                        case Circle:
                            System.out.println("O");
                    }

                if (markType  == PlayerType.None) {
                    System.out.print("*");
                }
                else if(markType == PlayerType.Cross) {
                    System.out.print("X");
                }
                else if(markType == PlayerType.Circle) {
                    System.out.print("O");
                }
            }
            System.out.print("\n");
        }
    }

    public PlayerType checkDiag() {

        if (board.getSquare(0, 0).getMark() == board.getSquare(1, 1).getMark() &&
                board.getSquare(0, 0).getMark() == board.getSquare(2, 2).getMark()) {
            return board.getSquare(0, 0).getMark();
        }
        if (board.getSquare(0, 2).getMark() == board.getSquare(1, 1).getMark() &&
                board.getSquare(0, 2).getMark() == board.getSquare(2, 0).getMark()) {
            return board.getSquare(0, 2).getMark();
        }
        return PlayerType.None;
    }

    public PlayerType checkCol(int col) {
        if (board.getSquare(0, col).getMark() == board.getSquare(1, col).getMark() &&
                board.getSquare(0, col).getMark() == board.getSquare(2, col).getMark()){
            return board.getSquare(0, col).getMark();
        }
        return PlayerType.None;
    }

    public PlayerType checkRow(int row) {

        if (board.getSquare(row, 0).getMark() == board.getSquare(row, 1).getMark() &&
                board.getSquare(row, 0).getMark() == board.getSquare(row, 2).getMark()) { //row full
            return board.getSquare(row, 0).getMark();
        }

        return PlayerType.None;
    }

    public PlayerType playerWon() {
        PlayerType winner;
        for (int i = 0; i < 3; i++) {
            winner = checkRow(i);
            if (winner != PlayerType.None) {
                return winner;
            }

            winner = checkCol(i);
            if (winner != PlayerType.None) {
                return winner;
            }
        }
        return checkDiag();
    }

}

public class TicTacToeTest {
}

package com.janosgyerik.codereview.junk.maze;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

class Solution {

    BufferedReader bufferedReader;

    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solve();
    }

    public Solution() {
        bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    }

    private void solve() {
        int[] inputs = convertStringArraytoIntArray(nextLine().split(" "));
        int x = inputs[0];
        int y = inputs[1];
        char[][] maze = new char[y][x];
        for (int ctr = 0; ctr < y; ctr++) {
            maze[ctr] = nextLine().toCharArray();
        }
        Maze inputMaze = new Maze(maze);
        int maxMoves = inputMaze.numOfSpaces();
        Set<Maze> seenMazes = new HashSet<>();
        Stack<Maze> mazeStack = new Stack<>();
        mazeStack.add(inputMaze);
        Maze answer = null;
        while (mazeStack.size() > 0) {
            Maze currMaze = mazeStack.pop();
            if (seenMazes.size() > 300000)
                break;
            if (currMaze.isSolved()) {
                if (answer == null || answer.getMoves() > currMaze.getMoves()) {
                    answer = currMaze;
                }

            } else {
                if (currMaze.getMoves() < maxMoves) {
                    if (currMaze.canMoveUp()) {
                        Maze temp = currMaze.moveUp();
                        if (!seenMazes.contains(temp)) {
                            mazeStack.push(temp);
                            seenMazes.add(temp);
                        }
                    }
                    if (currMaze.canMoveDown()) {
                        Maze temp = currMaze.moveDown();
                        if (!seenMazes.contains(temp)) {
                            mazeStack.push(temp);
                            seenMazes.add(temp);
                        }
                    }
                    if (currMaze.canMoveLeft()) {
                        Maze temp = currMaze.moveLeft();
                        if (!seenMazes.contains(temp)) {
                            mazeStack.push(temp);
                            seenMazes.add(temp);
                        }
                    }
                    if (currMaze.canMoveRight()) {
                        Maze temp = currMaze.moveRight();
                        if (!seenMazes.contains(temp)) {
                            mazeStack.push(temp);
                            seenMazes.add(temp);
                        }
                    }
                }
            }
        }
        answer.print();
    }

    private int[] convertStringArraytoIntArray(String[] strArray) {
        int[] intArr = new int[strArray.length];
        for (int ctr = 0, len = strArray.length; ctr < len; ctr++) {
            intArr[ctr] = Integer.parseInt(strArray[ctr]);
        }
        return intArr;
    }

    private String nextLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}

class Maze {

    public int moves;
    private char[][] maze;
    private Point currLoc;
    private Point endPoint;
    private int width;
    private int height;

    public Maze(char[][] maze) {
        this.maze = maze;
        moves = 0;
        height = maze.length;
        width = maze[0].length;
        currLoc = findStart();
        endPoint = findEnd();
    }

    public int numOfSpaces() {
        int numOfSpaces = 0;
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == ' ' || maze[y][x] == 'E') {
                    numOfSpaces++;
                }
            }
        return numOfSpaces;
    }

    private Point findStart() {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 'S') {
                    return new Point(x, y);
                }
            }
        return null;
    }

    private Point findEnd() {
        for (int y = 0; y < height; y++)
            for (int x = 0; x < width; x++) {
                if (maze[y][x] == 'E') {
                    return new Point(x, y);
                }
            }
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Maze) {
            Maze secondMaze = (Maze) obj;
            for (int y = 0; y < height; y++)
                for (int x = 0; x < width; x++) {
                    if (maze[y][x] != secondMaze.elementAt(y, x)) {
                        return false;
                    }
                }
            return true;
        }
        return false;
    }

    private char elementAt(int y, int x) {
        return maze[y][x];
    }

    public boolean canMoveUp() {
        int x = currLoc.x;
        int y = currLoc.y;
        //if (y > 0 && maze[y - 1][x] != '#' && maze[y - 1][x] != '*') {
        return y > 0 && maze[y - 1][x] != '#' && maze[y - 1][x] != '*';
    }

    public Maze moveUp() {
        int x = currLoc.x;
        int y = currLoc.y;
        Maze temp = createClone();
        temp.setElementAt(y - 1, x, '*');
        temp.setCurrentLocation(y - 1, x);
        temp.setMoves(moves + 1);
        return temp;
    }

    private void setMoves(int moves) {
        this.moves = moves;
    }

    private void setCurrentLocation(int y, int x) {
        this.currLoc = new Point(x, y);
    }

    private void setElementAt(int y, int x, char c) {
        maze[y][x] = c;
    }

    private Maze createClone() {
        char[][] newMaze = new char[height][width];
        for (int y = 0; y < height; y++)
            System.arraycopy(maze[y], 0, newMaze[y], 0, width);
//        for (int y = 0; y < height; y++)
//            for (int x = 0; x < width; x++) {
//                newMaze[y][x] = maze[y][x];
//            }
        return new Maze(newMaze);
    }

    public boolean isSolved() {
        if (currLoc.x == endPoint.x && currLoc.y == endPoint.y) {
            maze[currLoc.y][currLoc.x] = 'E';
            return true;
        }
        return false;
    }

    public int getMoves() {
        return moves;
    }

    public boolean canMoveDown() {
        int x = currLoc.x;
        int y = currLoc.y;
        return y < height - 1 && maze[y + 1][x] != '#' && maze[y + 1][x] != '*';
    }

    public Maze moveDown() {
        int x = currLoc.x;
        int y = currLoc.y;
        Maze temp = createClone();
        temp.setElementAt(y + 1, x, '*');
        temp.setCurrentLocation(y + 1, x);
        temp.setMoves(moves + 1);
        return temp;
    }

    public boolean canMoveLeft() {
        int x = currLoc.x;
        int y = currLoc.y;
        return x > 0 && maze[y][x - 1] != '#' && maze[y][x - 1] != '*';
    }

    public Maze moveLeft() {
        int x = currLoc.x;
        int y = currLoc.y;
        Maze temp = createClone();
        temp.setElementAt(y, x - 1, '*');
        temp.setCurrentLocation(y, x - 1);
        temp.setMoves(moves + 1);
        return temp;
    }

    public boolean canMoveRight() {
        int x = currLoc.x;
        int y = currLoc.y;
        return x < width - 1 && maze[y][x + 1] != '#' && maze[y][x + 1] != '*';
    }

    public Maze moveRight() {
        int x = currLoc.x;
        int y = currLoc.y;
        Maze temp = createClone();
        temp.setElementAt(y, x + 1, '*');
        temp.setCurrentLocation(y, x + 1);
        temp.setMoves(moves + 1);
        return temp;
    }

    public void print() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                System.out.print(maze[y][x]);
            }
            System.out.println();
        }
    }
}


package com.janosgyerik.practice.mazerunner;

public interface Maze {
    /**
     * Initialize the maze
     */
    void initialize();

    /**
     * Try to move in the specified direction
     *
     * @param direction The direction to try to move into
     * @return true if the move was successful (and the position has changed)
     */
    boolean move(Direction direction);

    /**
     * Determine if we are on the target
     *
     * @return true if we are on the target
     */
    boolean isSuccess();
}

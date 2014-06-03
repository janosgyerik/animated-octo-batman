package com.janosgyerik.codingame.tron;

public abstract class AbstractPlayerTest {
    static final Position MIDDLE = new Position(BasePlayer.MID_X, BasePlayer.MID_Y);
    static final Position HORIZONTAL_EDGE_CENTER = new Position(BasePlayer.MID_X, 0);
    static final Position VERTICAL_EDGE_CENTER = new Position(0, BasePlayer.MID_Y);
    static final Position CORNER = new Position(0, 0);
    static final Position NEAR_CORNER = new Position(0, 1);
}

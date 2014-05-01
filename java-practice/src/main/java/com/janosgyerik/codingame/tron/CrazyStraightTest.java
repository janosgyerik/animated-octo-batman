package com.janosgyerik.codingame.tron;

import org.junit.Assert;
import org.junit.Test;

public class CrazyStraightTest {
    private Position middle = new Position(BasePlayer.MAX_X / 2, BasePlayer.MAX_Y / 2);
    private Position middleEdge = new Position(BasePlayer.MAX_X / 2, 0);
    private Position corner = new Position(0, 0);
    private Position nearCorner = new Position(0, 1);

    private BasePlayer createPlayer() {
        return new Crazy();
    }

    @Test
    public void can_play_alone_at_least_one_round() {
        getFirstMove(createPlayer(), middle);
        getFirstMove(createPlayer(), middleEdge);
        getFirstMove(createPlayer(), corner);
        getFirstMove(createPlayer(), nearCorner);
    }

    private Move getFirstMove(BasePlayer player, Position position) {
        Move move = player.getFirstMove(0, new PlayerInfo[]{new PlayerInfo(position)});
        Assert.assertNotNull(move);
        return move;
    }

    private Move getNextMove(BasePlayer player, Move lastMove) {
        int newx = player.getX();
        int newy = player.getY();
        switch (lastMove) {
            case LEFT:
                --newx;
                break;
            case RIGHT:
                ++newx;
                break;
            case UP:
                --newy;
                break;
            case DOWN:
                ++newy;
                break;
        }
        return player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(new Position(newx, newy))});
    }

    private void play(int rounds, Position position) {
        BasePlayer player = createPlayer();
        Move move = getFirstMove(player, position);
        int count = 0;
        do {
            move = getNextMove(player, move);
            ++count;
        } while (move != null);
        Assert.assertTrue(count >= rounds);
    }

    private void play(int repeat, int rounds, Position position) {
        for (int i = 0; i < repeat; ++i) {
            play(rounds, position);
        }
    }

    @Test
    public void test_can_play_at_least_8_rounds_from_middle() {
        play(10, 8, middle);
    }

    @Test
    public void test_can_play_at_least_5_rounds_from_side() {
        play(50, 5, middleEdge);
    }

    @Test
    public void test_can_play_at_least_3_rounds_from_near_corner() {
        play(100, 3, nearCorner);
    }

    @Test(expected = AssertionError.class)
    public void test_cannot_always_play_100_rounds_from_near_corner() {
        play(10, 100, nearCorner);
    }
}

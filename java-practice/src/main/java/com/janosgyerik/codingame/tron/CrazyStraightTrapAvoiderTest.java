package com.janosgyerik.codingame.tron;

import org.junit.Assert;
import org.junit.Test;

public class CrazyStraightTrapAvoiderTest extends BasePlayerTest {
    @Override
    protected BasePlayer createPlayer() {
        return new CrazyStraightTrapAvoider();
    }

    private BasePlayer forceFirstMove(Move firstMove) {
        BasePlayer player = createPlayer();
        Move move;
        do {
            move = player.getFirstMove(0, new PlayerInfo[]{new PlayerInfo(middle)});
        } while (move != firstMove);
        return player;
    }

    @Test
    public void test_keep_left_if_no_obstacles() {
        Move move = Move.LEFT;
        BasePlayer player = forceFirstMove(move);
        Assert.assertEquals(move, player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(middle.x, middle.y, middle.x - 1, middle.y)}));
    }

    @Test
    public void test_keep_right_if_no_obstacles() {
        Move move = Move.RIGHT;
        BasePlayer player = forceFirstMove(move);
        Assert.assertEquals(move, player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(middle.x, middle.y, middle.x + 1, middle.y)}));
    }

    @Test
    public void test_keep_down_if_no_obstacles() {
        Move move = Move.DOWN;
        BasePlayer player = forceFirstMove(move);
        Assert.assertEquals(move, player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(middle.x, middle.y, middle.x, middle.y + 1)}));
    }

    @Test
    public void test_keep_up_if_no_obstacles() {
        Move move = Move.UP;
        BasePlayer player = forceFirstMove(move);
        Assert.assertEquals(move, player.getNextMove(0, new PlayerInfo[]{new PlayerInfo(middle.x, middle.y, middle.x, middle.y - 1)}));
    }
}

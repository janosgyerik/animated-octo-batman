package com.janosgyerik.codingame.tron;

import org.junit.Assert;
import org.junit.Test;

public class CrazyLongStraightTest extends CrazyTest {
    @Override
    protected BasePlayer createPlayer() {
        return new CrazyLongStraight();
    }

    @Test
    public void test_choose_longer_straight_direction() {
        BasePlayer player = createPlayer();
        Assert.assertEquals(Move.LEFT, player.getFirstMove(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(new Position(middle.x + 3, middle.y))
        }));
        Assert.assertEquals(Move.RIGHT, player.getFirstMove(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(new Position(middle.x - 3, middle.y))
        }));
        Assert.assertEquals(Move.UP, player.getFirstMove(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(new Position(middle.x - 3, middle.y)),
                new PlayerInfo(new Position(middle.x + 3, middle.y)),
                new PlayerInfo(new Position(middle.x, middle.y + 3)),
        }));
        Assert.assertEquals(Move.DOWN, player.getFirstMove(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(new Position(middle.x - 3, middle.y)),
                new PlayerInfo(new Position(middle.x + 3, middle.y)),
                new PlayerInfo(new Position(middle.x, middle.y - 3)),
        }));
    }
}

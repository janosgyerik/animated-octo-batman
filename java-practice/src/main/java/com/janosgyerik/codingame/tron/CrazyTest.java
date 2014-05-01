package com.janosgyerik.codingame.tron;

import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

public class CrazyTest {
    protected Position middle = new Position(BasePlayer.MAX_X / 2, BasePlayer.MAX_Y / 2);
    private Position middleEdge = new Position(BasePlayer.MAX_X / 2, 0);
    private Position corner = new Position(0, 0);
    private Position nearCorner = new Position(0, 1);

    protected BasePlayer createPlayer() {
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

    //@Test
    public void test_can_play_at_least_8_rounds_from_middle() {
        play(10, 8, middle);
    }

    //@Test
    public void test_can_play_at_least_5_rounds_from_side() {
        play(50, 5, middleEdge);
    }

    //@Test
    public void test_can_play_at_least_3_rounds_from_near_corner() {
        play(100, 3, nearCorner);
    }

    //@Test(expected = AssertionError.class)
    public void test_cannot_always_play_100_rounds_from_near_corner() {
        play(10, 100, nearCorner);
    }

    @Test
    public void test_can_move_to_4_directions_from_middle() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(middle)});
        Assert.assertEquals(4, player.getPossibleMoves().size());
    }

    @Test
    public void test_can_move_to_3_directions_from_edge() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(middleEdge)});
        Assert.assertEquals(3, player.getPossibleMoves().size());
    }

    @Test
    public void test_can_move_to_2_directions_from_corner() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(corner)});
        Assert.assertEquals(2, player.getPossibleMoves().size());
    }

    @Test
    public void test_blocked_by_another_player() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(middle)});
        Assert.assertEquals(4, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(new Position(middle.x + 1, middle.y))
        });
        Assert.assertEquals(3, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(new Position(middle.x + 1, middle.y)),
                new PlayerInfo(new Position(middle.x - 1, middle.y))
        });
        Assert.assertEquals(2, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(new Position(middle.x + 1, middle.y)),
                new PlayerInfo(new Position(middle.x - 1, middle.y)),
                new PlayerInfo(new Position(middle.x, middle.y + 1))
        });
        Assert.assertEquals(1, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(new Position(middle.x + 1, middle.y)),
                new PlayerInfo(new Position(middle.x - 1, middle.y)),
                new PlayerInfo(new Position(middle.x, middle.y + 1)),
                new PlayerInfo(new Position(middle.x, middle.y - 1))
        });
        Set<Move> moves = player.getPossibleMoves();
        Assert.assertEquals(1, moves.size());
        Assert.assertNull(moves.iterator().next());
    }

    @Test
    public void test_ignore_lost_players() {
        BasePlayer player = createPlayer();
        player.initPositionHistory(0, new PlayerInfo[]{new PlayerInfo(middle)});
        Assert.assertEquals(4, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(0, 0, middle.x + 1, middle.y)
        });
        Assert.assertEquals(3, player.getPossibleMoves().size());

        player.initPositionHistory(0, new PlayerInfo[]{
                new PlayerInfo(middle),
                new PlayerInfo(-1, -1, middle.x + 1, middle.y)
        });
        Assert.assertEquals(4, player.getPossibleMoves().size());
    }
}

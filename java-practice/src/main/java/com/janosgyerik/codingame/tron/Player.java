package com.janosgyerik.codingame.tron;

import java.util.*;

class Player {

    private static IPlayer createPlayer() {
        return new CrazyStraight();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        IPlayer me = null;

        while (true) {
            int n = in.nextInt();
            int p = in.nextInt();

            PlayerInfo[] playerInfos = new PlayerInfo[n];
            for (int i = 0; i < n; ++i) {
                int x0 = in.nextInt();
                int y0 = in.nextInt();
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                playerInfos[i] = new PlayerInfo(x0, y0, x1, y1);
            }

            if (me == null) {
                me = createPlayer();
                System.out.println(me.getFirstMove(p, playerInfos));
            } else {
                System.out.println(me.getNextMove(p, playerInfos));
            }
        }
    }
}

enum Move {
    LEFT,
    RIGHT,
    UP,
    DOWN;
    public static final Move[] MOVES = {LEFT, RIGHT, UP, DOWN};
}

class PlayerInfo {
    protected final int x0;
    protected final int y0;
    protected final int x1;
    protected final int y1;

    public PlayerInfo(int x0, int y0, int x1, int y1) {
        this.x0 = x0;
        this.y0 = y0;
        this.x1 = x1;
        this.y1 = y1;
    }

    public PlayerInfo(Position position) {
        x0 = x1 = position.x;
        y0 = y1 = position.y;
    }
}

interface IPlayer {

    Move getFirstMove(int p, PlayerInfo[] playerInfos);

    Move getNextMove(int p, PlayerInfo[] playerInfos);
}

class Position {
    final int x;
    final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Position) {
            Position other = (Position) obj;
            return x == other.x && y == other.y;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", x, y);
    }
}

abstract class BasePlayer implements IPlayer {
    static final int MIN_X = 0;
    static final int MAX_X = 29;
    static final int MIN_Y = 0;
    static final int MAX_Y = 19;

    private final Map<Integer, OtherPlayer> otherPlayers = new HashMap<Integer, OtherPlayer>();
    protected final Set<Position> visitedPositions = new HashSet<Position>();
    protected final List<Position> positionsHistory = new ArrayList<Position>();

    protected int x;
    protected int y;
    protected Move move;

    private void initOtherPlayers(int p, PlayerInfo[] playerInfos) {
        for (int i = 0; i < playerInfos.length; ++i) {
            if (i != p) {
                OtherPlayer other = new OtherPlayer();
                other.initPositionHistory(0, new PlayerInfo[]{playerInfos[i]});
                otherPlayers.put(i, other);
            }
        }
    }

    void initPositionHistory(int p, PlayerInfo[] playerInfos) {
        PlayerInfo playerInfo = playerInfos[p];
        x = playerInfo.x0;
        y = playerInfo.y0;

        Position pos0 = new Position(playerInfo.x0, playerInfo.y0);
        visitedPositions.add(pos0);
        positionsHistory.add(pos0);

        Position pos = new Position(playerInfo.x1, playerInfo.y1);
        if (!pos.equals(pos0)) {
            visitedPositions.add(pos);
            positionsHistory.add(pos);
        }

        initOtherPlayers(p, playerInfos);
    }

    private void updateOtherPlayers(int p, PlayerInfo[] playerInfos) {
        for (int i = 0; i < playerInfos.length; ++i) {
            if (i != p) {
                otherPlayers.get(i).updatePositionHistory(0, new PlayerInfo[]{playerInfos[i]});
            }
        }
    }

    void updatePositionHistory(int p, PlayerInfo[] playerInfos) {
        PlayerInfo playerInfo = playerInfos[p];
        x = playerInfo.x1;
        y = playerInfo.y1;

        Position pos = new Position(x, y);
        visitedPositions.add(pos);
        positionsHistory.add(pos);

        updateOtherPlayers(p, playerInfos);
    }

    public Set<Move> getPossibleMoves() {
        Set<Move> possibleMoves = new HashSet<Move>();
        for (Move move : Move.MOVES) {
            if (canMove(move)) {
                possibleMoves.add(move);
            }
        }
        if (possibleMoves.isEmpty()) {
            return Collections.singleton(null);
        }
        return possibleMoves;
    }

    public boolean canMove(Move targetMove) {
        switch (targetMove) {
            case LEFT:
                return x > MIN_X && isAvailable(x - 1, y);
            case RIGHT:
                return x < MAX_X && isAvailable(x + 1, y);
            case UP:
                return y > MIN_Y && isAvailable(x, y - 1);
            case DOWN:
                return y < MAX_Y && isAvailable(x, y + 1);
            default:
                return false;
        }
    }

    public Move[] getPossibleMovesArray() {
        Set<Move> possibleMoves = getPossibleMoves();
        return possibleMoves.toArray(new Move[possibleMoves.size()]);
    }

    public Move getRandomMove() {
        Move[] possibleMoves = getPossibleMovesArray();
        return possibleMoves[(int) (Math.random() * possibleMoves.length)];
    }

    private boolean isAvailable(int x, int y) {
        if (isUsing(x, y)) {
            return false;
        }
        for (OtherPlayer player : otherPlayers.values()) {
            if (player.isUsing(x, y)) {
                return false;
            }
        }
        return true;
    }

    protected boolean isUsing(int x, int y) {
        return visitedPositions.contains(new Position(x, y));
    }
}

class OtherPlayer extends BasePlayer {
    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        throw new UnsupportedOperationException();
    }
}

abstract class CrazyStarter extends BasePlayer {
    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        initPositionHistory(p, playerInfos);
        return move = getRandomMove();
    }
}

class Crazy extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        return move = getRandomMove();
    }
}

class CrazyStraight extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        if (canMove(move)) {
            return move;
        }
        return move = getRandomMove();
    }
}

//class Divider extends BasePlayer {
// todo divide the available space
//}
//
//class Chicken extends BasePlayer {
//    // todo head to the nearest wall
//}

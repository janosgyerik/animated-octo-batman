package com.janosgyerik.codingame.tron;

import java.util.*;

class Player {

    private static IPlayer createPlayer() {
        return new Crazy();
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
    DOWN
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

    private int x;
    private int y;
    protected Move move;

    void initPositionHistory(int p, PlayerInfo[] playerInfos) {
        this.x = playerInfos[p].x0;
        this.y = playerInfos[p].y0;
        for (int i = 0; i < playerInfos.length; ++i) {
            if (i != p) {
                otherPlayers.put(i, new OtherPlayer(playerInfos[i]));
            }
        }
        Position pos0 = new Position(playerInfos[p].x0, playerInfos[p].y0);
        Position pos = new Position(playerInfos[p].x1, playerInfos[p].y1);
        visitedPositions.add(pos0);
        visitedPositions.add(pos);
        positionsHistory.add(pos0);
        if (!pos.equals(pos0)) {
            positionsHistory.add(pos);
        }
    }

    void initPositionHistory(PlayerInfo playerInfo) {
        this.x = playerInfo.x0;
        this.y = playerInfo.y0;
        Position pos = new Position(playerInfo.x1, playerInfo.y1);
        visitedPositions.add(pos);
        positionsHistory.add(pos);
    }

    void updatePositionHistory(int p, PlayerInfo[] playerInfos) {
        x = playerInfos[p].x1;
        y = playerInfos[p].y1;
        for (int i = 0; i < playerInfos.length; ++i) {
            if (i != p) {
                otherPlayers.get(i).updatePositionHistory(playerInfos[i]);
            }
        }
        Position pos = new Position(x, y);
        visitedPositions.add(pos);
        positionsHistory.add(pos);
    }

    public void updatePositionHistory(PlayerInfo playerInfo) {
        if (playerInfo.x1 == -1) {
            return;
        }
        Position pos = new Position(playerInfo.x1, playerInfo.y1);
        if (pos.x != x) {
            if (pos.x < x) {
                move = Move.LEFT;
            } else {
                move = Move.RIGHT;
            }
        } else if (pos.y != y) {
            if (pos.y < y) {
                move = Move.DOWN;
            } else {
                move = Move.UP;
            }
        }
        visitedPositions.add(pos);
        positionsHistory.add(pos);
    }

    public Set<Move> getPossibleMoves() {
        Set<Move> possibleMoves = new HashSet<Move>();
        if (x > MIN_X && isAvailable(x - 1, y)) {
            possibleMoves.add(Move.LEFT);
        }
        if (x < MAX_X && isAvailable(x + 1, y)) {
            possibleMoves.add(Move.RIGHT);
        }
        if (y > MIN_Y && isAvailable(x, y - 1)) {
            possibleMoves.add(Move.UP);
        }
        if (y < MAX_Y && isAvailable(x, y + 1)) {
            possibleMoves.add(Move.DOWN);
        }
        return possibleMoves;
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
    public OtherPlayer(PlayerInfo playerInfo) {
        initPositionHistory(playerInfo);
    }

    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        throw new UnsupportedOperationException();
    }
}

class Crazy extends BasePlayer {
    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        initPositionHistory(p, playerInfos);
        return move = getRandomMove();
    }

    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
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

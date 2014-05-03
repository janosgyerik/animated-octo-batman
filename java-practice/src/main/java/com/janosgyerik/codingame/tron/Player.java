package com.janosgyerik.codingame.tron;

import java.util.*;

class Player {

    private static IPlayer createPlayer() {
        return new CrazyAggressiveTrapAvoider();
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
    LEFT, RIGHT, UP, DOWN,
    IMPOSSIBLE("damn");

    public static final Move[] MOVES = {LEFT, RIGHT, UP, DOWN};

    private final String message;

    Move() {
        message = null;
    }

    Move(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message != null ? message : name();
    }
}

class PlayerInfo {
    final int x0;
    final int y0;
    final int x1;
    final int y1;

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
    static final int MID_X = (MAX_X - MIN_X) / 2;
    static final int MID_Y = (MAX_Y - MIN_Y) / 2;

    private static final char USED = '1';
    private static final char FREE = '0';
    private static final char REACHABLE = ' ';

    final Map<Integer, OtherPlayer> otherPlayers = new HashMap<Integer, OtherPlayer>();
    final Set<BasePlayer> players = new HashSet<BasePlayer>();
    private final Set<Position> visitedPositions = new HashSet<Position>();

    private int x;
    private int y;
    protected Move lastMove;

    private void initOtherPlayers(int p, PlayerInfo[] playerInfos) {
        for (int i = 0; i < playerInfos.length; ++i) {
            if (i != p) {
                OtherPlayer other = new OtherPlayer();
                other.initPositionHistory(0, new PlayerInfo[]{playerInfos[i]});
                otherPlayers.put(i, other);
                players.add(other);
            }
        }
    }

    void initPositionHistory(int p, PlayerInfo[] playerInfos) {
        PlayerInfo playerInfo = playerInfos[p];
        x = playerInfo.x0;
        y = playerInfo.y0;

        Position pos0 = new Position(playerInfo.x0, playerInfo.y0);
        visitedPositions.add(pos0);

        Position pos = new Position(playerInfo.x1, playerInfo.y1);
        visitedPositions.add(pos);

        players.add(this);
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
            return Collections.singleton(Move.IMPOSSIBLE);
        }
        return possibleMoves;
    }

    public boolean canMove(Move targetMove) {
        return isValidAndAvailablePosition(getPositionAfterMove(targetMove));
    }

    public Position getPositionAfterMove(Move move) {
        switch (move) {
            case LEFT:
                return new Position(x - 1, y);
            case RIGHT:
                return new Position(x + 1, y);
            case UP:
                return new Position(x, y - 1);
            case DOWN:
                return new Position(x, y + 1);
        }
        throw new IllegalArgumentException();
    }

    public Move[] getMovesArray(Set<Move> moves) {
        return moves.toArray(new Move[moves.size()]);
    }

    public Move getRandomMove(Set<Move> moves) {
        Move[] movesArray = getMovesArray(moves);
        return movesArray[(int) (Math.random() * movesArray.length)];
    }

    public Move getRandomMove() {
        return getRandomMove(getPossibleMoves());
    }

    protected boolean isValidAndAvailablePosition(Position position) {
        return isValidPosition(position) && isAvailablePosition(position);
    }

    protected boolean isValidPosition(Position position) {
        return isValidPosition(position.x, position.y);
    }

    protected boolean isValidPosition(int x, int y) {
        return x >= MIN_X && x <= MAX_X && y >= MIN_Y && y <= MAX_Y;
    }

    protected boolean isAvailablePosition(Position position) {
        for (BasePlayer player : players) {
            if (player.ownsPosition(position)) {
                return false;
            }
        }
        return true;
    }

    protected boolean ownsPosition(Position position) {
        return isAlive() && visitedPositions.contains(position);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isAlive() {
        return x != -1;
    }

    int countHoleSize(char[][] grid, int x, int y) {
        if (!isValidPosition(x, y)) {
            return 0;
        }
        if (grid[x][y] == FREE) {
            grid[x][y] = REACHABLE;
            return 1
                    + countHoleSize(grid, x + 1, y)
                    + countHoleSize(grid, x - 1, y)
                    + countHoleSize(grid, x, y + 1)
                    + countHoleSize(grid, x, y - 1);
        }
        return 0;
    }

    protected char[][] initGrid() {
        char[][] grid = new char[MAX_X + 1][MAX_Y + 1];
        for (int x = 0; x <= MAX_X; ++x) {
            for (int y = 0; y <= MAX_Y; ++y) {
                grid[x][y] = FREE;
                Position position = new Position(x, y);
                for (BasePlayer player : players) {
                    if (player.ownsPosition(position)) {
                        grid[x][y] = USED;
                        break;
                    }
                }
            }
        }
        return grid;
    }

    Set<Move> getSaferMoves() {
        Set<Move> possibleMoves = getPossibleMoves();
        if (possibleMoves.size() < 2) {
            return possibleMoves;
        }
        TreeMap<Integer, Set<Move>> holeSizeToMoves = new TreeMap<Integer, Set<Move>>();
        for (Move move : possibleMoves) {
            int holeSize = countHoleSizeFromMove(move);
            Set<Move> moves = holeSizeToMoves.get(holeSize);
            if (moves == null) {
                moves = new HashSet<Move>();
                holeSizeToMoves.put(holeSize, moves);
            }
            moves.add(move);
        }
        return holeSizeToMoves.lastEntry().getValue();
    }

    private int countHoleSizeFromMove(Move move) {
        return countHoleSize(initGrid(), getPositionAfterMove(move));
    }

    private int countHoleSize(char[][] grid, Position position) {
        return countHoleSize(grid, position.x, position.y);
    }
}

final class OtherPlayer extends BasePlayer {
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
        return lastMove = getRandomMove();
    }
}

abstract class AggressiveStarter extends BasePlayer {
    @Override
    public Move getFirstMove(int p, PlayerInfo[] playerInfos) {
        initPositionHistory(p, playerInfos);
        return lastMove = getRandomMove(getSaferMovesToward(getAnotherPlayer()));
    }

    Set<Move> getSaferMovesToward(OtherPlayer otherPlayer) {
        Set<Move> saferMoves = getSaferMoves();
        if (saferMoves.size() < 2) {
            return saferMoves;
        }
        TreeMap<Integer, Set<Move>> distanceToMove = new TreeMap<Integer, Set<Move>>();
        for (Move move : saferMoves) {
            int distance = distanceFromPlayer(otherPlayer, move);
            Set<Move> moves = distanceToMove.get(distance);
            if (moves == null) {
                moves = new HashSet<Move>();
                distanceToMove.put(distance, moves);
            }
            moves.add(move);
        }
        return distanceToMove.firstEntry().getValue();
    }

    private int distanceFromPlayer(OtherPlayer otherPlayer, Move move) {
        return distanceFromPlayer(otherPlayer, getPositionAfterMove(move));
    }

    private int distanceFromPlayer(OtherPlayer otherPlayer, Position position) {
        int dx = position.x - otherPlayer.getX();
        int dy = position.y - otherPlayer.getY();
        return dx * dx + dy * dy;
    }

    OtherPlayer getAnotherPlayer() {
        return otherPlayers.values().iterator().next();
    }
}

class Crazy extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        return getRandomMove();
    }
}

class CrazyStraight extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        if (canMove(lastMove)) {
            return lastMove;
        }
        return lastMove = getRandomMove();
    }
}

class CrazyTrapAvoider extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        Set<Move> saferMoves = getSaferMoves();
        return lastMove = getRandomMove(saferMoves);
    }
}

class CrazyStraightTrapAvoider extends CrazyStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        Set<Move> saferMoves = getSaferMoves();
        if (saferMoves.contains(lastMove)) {
            return lastMove;
        }
        return lastMove = saferMoves.iterator().next();
    }
}

class CrazyAggressiveStraightTrapAvoider extends AggressiveStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        Set<Move> saferMoves = getSaferMoves();
        if (saferMoves.contains(lastMove)) {
            return lastMove;
        }
        return lastMove = saferMoves.iterator().next();
    }
}

class CrazyAggressiveTrapAvoider extends AggressiveStarter {
    @Override
    public Move getNextMove(int p, PlayerInfo[] playerInfos) {
        updatePositionHistory(p, playerInfos);
        return lastMove = getRandomMove(getSaferMovesToward(getAnotherPlayer()));
    }
}
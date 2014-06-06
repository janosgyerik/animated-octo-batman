package com.janosgyerik.codingame.tron.v2;

import java.util.*;

// TODO stop following other player if unreachable

// TODO consider potential hole if target player keeps going in the same direction

// TODO consider potential hole if all other players keep going in the same direction

// TODO if next move will cut off the other player, do it, example: y: (10,10)(8,6)
// MEMO count reachable positions of other players after a move

// TODO if dominating a space, don't diminish it, fill it well
// MEMO if no reachable players (keep in mind though a dead player might open a wall)
// MEMO fill well by following a wall in a "safe" direction

// TODO which reachable player is best to attack? the weak or the strong?

// TODO consider the ability of cutting off players by repeating same move from current pos,
// or from current pos + move

class Player {

    static final int LOST_PLAYER_X = -1;

    private static IPlayer createPlayer() {
        return new CarefulInterceptor();
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        Grid grid = new RectangleGrid(30, 20);
        IPlayer me = createPlayer();

        while (true) {
            int n = in.nextInt();
            int index = in.nextInt();

            Position[] positions = new Position[n];

            for (int i = 0; i < n; ++i) {
                int x0 = in.nextInt();
                int y0 = in.nextInt();
                int x1 = in.nextInt();
                int y1 = in.nextInt();

                positions[i] = new Position(x1, y1);

                if (x0 == LOST_PLAYER_X) {
                    grid.removePlayer(i);
                } else {
                    grid.addPosition(i, new Position(x0, y0));
                    grid.addPosition(i, new Position(x1, y1));
                }
            }
            System.out.println(me.getNextMove(index, positions, grid));
        }
    }
}

enum Move {
    LEFT, RIGHT, UP, DOWN,
    IMPOSSIBLE("DAAAAAAAAAMN!...");

    public static final Move[] MOVES = {LEFT, RIGHT, UP, DOWN};

    private final String message;

    Move() {
        message = name();
    }

    Move(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}

interface IPlayer {
    Move getNextMove(int index, Position[] positions, Grid grid);
}

class Position {
    final int x;
    final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static Position plusMove(Position position, Move... moves) {
        Position newPosition = position;
        for (Move move : moves) {
            newPosition = plusMove(newPosition.x, newPosition.y, move);
        }
        return newPosition;
    }

    static Position plusMove(int x, int y, Move move) {
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

interface Grid {
    boolean containsPosition(Position position);

    boolean isAvailablePosition(Position position);

    boolean isReachablePosition(Position from, Position to);

    int countReachablePositionsFrom(Position position);

    Set<Move> getPossibleMovesFrom(Position position);

    /**
     * "Safer" moves would result in more reachable positions
     * compared to other less safe moves.
     * @param position to start from
     * @return a non-empty Set of safer moves
     */
    Set<Move> getSaferMovesFrom(Position position);

    void applyMove(IPlayer player, Move move);

    void applyMoveUntilBlocked(IPlayer player, Move move);

    Position getAnyPosition();

    void addPosition(int playerIndex, Position position);

    void removePlayer(int index);

    Grid copy(Grid other);

    Position getFirstPosition(int playerIndex);

    Set<Position> getAvailablePositions();
}

class RectangleGrid implements Grid {

    private final int width;
    private final int height;

    private final Map<Position, Integer> positions;

    public RectangleGrid(int width, int height) {
        this.width = width;
        this.height = height;
        this.positions = new HashMap<Position, Integer>();
    }

    private RectangleGrid(RectangleGrid other) {
        this.width = other.width;
        this.height = other.height;
        this.positions = new HashMap<Position, Integer>();
        positions.putAll(other.positions);
    }

    @Override
    public RectangleGrid copy(Grid other) {
        if (other instanceof RectangleGrid) {
            return new RectangleGrid((RectangleGrid) other);
        }
        return null;
    }

    @Override
    public Position getFirstPosition(int playerIndex) {
        for (Map.Entry<Position, Integer> entry : positions.entrySet()) {
            if (entry.getValue().equals(playerIndex)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public Set<Position> getAvailablePositions() {
        Set<Position> available = new HashSet<Position>();
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                Position position = new Position(i, j);
                if (isAvailablePosition(position)) {
                    available.add(position);
                }
            }
        }
        return available;
    }

    @Override
    public boolean containsPosition(Position position) {
        return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
    }

    @Override
    public boolean isAvailablePosition(Position position) {
        return ! positions.containsKey(position);
    }

    @Override
    public boolean isReachablePosition(Position from, Position to) {
        return false;
    }

    @Override
    public int countReachablePositionsFrom(Position position) {
        return countReachablePositionsFrom(copy(this), position);
    }

    private int countReachablePositionsFrom(Grid grid, Position position) {
        if (!grid.containsPosition(position) || !grid.isAvailablePosition(position)) {
            return 0;
        }
        grid.addPosition(Integer.MAX_VALUE, position);
        int count = 1;
        for (Move move : Move.MOVES) {
            Position next = Position.plusMove(position, move);
            count += countReachablePositionsFrom(grid, next);
        }
        return count;
    }

    @Override
    public Set<Move> getPossibleMovesFrom(Position position) {
        Set<Move> possibleMoves = new HashSet<Move>();
        for (Move move : Move.MOVES) {
            Position newPosition = Position.plusMove(position, move);
            if (containsPosition(newPosition) && isAvailablePosition(newPosition)) {
                possibleMoves.add(move);
            }
        }
        return possibleMoves;
    }

    @Override
    public Set<Move> getSaferMovesFrom(Position position) {
        return null;
    }

    @Override
    public void applyMove(IPlayer player, Move move) {
    }

    @Override
    public void applyMoveUntilBlocked(IPlayer player, Move move) {
    }

    @Override
    public Position getAnyPosition() {
        return new Position(width / 2, height / 2);
    }

    @Override
    public void addPosition(int playerIndex, Position position) {
        positions.put(position, playerIndex);
    }

    @Override
    public void removePlayer(int index) {
        Set<Position> toRemove = new HashSet<Position>();
        for (Map.Entry<Position, Integer> entry : positions.entrySet()) {
            if (entry.getValue().equals(index)) {
                toRemove.add(entry.getKey());
            }
        }
        for (Position position : toRemove) {
            positions.remove(position);
        }
    }
}

abstract class BasePlayer implements IPlayer {
    Set<OtherPlayer> getOtherPlayers(int index, Position[] positions) {
        Set<OtherPlayer> otherPlayers = new HashSet<OtherPlayer>();
        for (int i = 0; i < positions.length; ++i) {
            Position position = positions[i];
            if (i != index && position.x != Player.LOST_PLAYER_X) {
                otherPlayers.add(new OtherPlayer(position));
            }
        }
        return otherPlayers;
    }

    OtherPlayer getClosestPlayer(int index, Position[] positions) {
        Set<OtherPlayer> otherPlayers = getOtherPlayers(index, positions);
        return getClosestPlayer(otherPlayers);
    }

    private OtherPlayer getClosestPlayer(Set<OtherPlayer> otherPlayers) {
        if (otherPlayers.size() == 1) {
            return otherPlayers.iterator().next();
        }
        // TODO
        return otherPlayers.iterator().next();
    }

    OtherPlayer getClosestReachablePlayer(int index, Position[] positions) {
        Set<OtherPlayer> otherPlayers = getOtherPlayers(index, positions);
        return getClosestReachablePlayer(otherPlayers);
    }

    private OtherPlayer getClosestReachablePlayer(Set<OtherPlayer> otherPlayers) {
        SortedMap<Integer, OtherPlayer> distanceToPlayers = new TreeMap<Integer, OtherPlayer>();
        for (OtherPlayer otherPlayer : otherPlayers) {
            int distance = getDistance(otherPlayer);
            if (distance > 0) {
                distanceToPlayers.put(distance, otherPlayer);
            }
        }
        if (distanceToPlayers.isEmpty()) {
            return null;
        }
        return distanceToPlayers.get(distanceToPlayers.firstKey());
    }

    private int getDistance(OtherPlayer otherPlayer) {
        return 0;
    }

}

class OtherPlayer extends BasePlayer {
    final Position position;

    public OtherPlayer(Position position) {
        this.position = position;
    }

    @Override
    public Move getNextMove(int index, Position[] positions, Grid grid) {
        return null;
    }
}

class CarefulInterceptor extends BasePlayer {
    @Override
    public Move getNextMove(int index, Position[] positions, Grid grid) {
        Position me = positions[index];
        Set<OtherPlayer> otherPlayers = getOtherPlayers(index, positions);
        // TODO get possible moves in the direction of the closest reachable player
        OtherPlayer enemy = getClosestReachablePlayer(index, positions);
        // TODO
        return null;
    }

}
package com.janosgyerik.codereview.user11684;

enum Direction {
	NORTHWEST(-1, -1),
	NORTH(0, -1),
	NORTHEAST(1, -1),
	EAST(1, 0),
	SOUTHEAST(1, 1),
	SOUTH(0, -1),
	SOUTHWEST(-1, 1),
	WEST(-1, 0),
	;

	final int dx;
	final int dy;

	Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
}

class Position {
	final int x;
	final int y;

	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	Position getNeighbourAt(Direction direction) {
		return new Position(x + direction.dx, y + direction.dy);
	}
}

class Board {
	private int width;
	private int height;
	private boolean[][] tiles;

	public boolean isAlive(Position position) {
		return isAlive(position.x, position.y);
	}

	public boolean isAlive(int x, int y) {
		return !(x < 0 || y < 0 || x > getWidth() - 1 || y > getHeight() - 1) && tiles[y][x];
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}

class Game {
	static int countNeighbours(Board b, int x, int y) {
		int count = 0;
		for (Direction direction : Direction.values()) {
			if (b.isAlive(x + direction.dx, y + direction.dy)) {
				++count;
			}
		}
		return count;
	}

	static int countNeighbours(Board b, Position position) {
		int count = 0;
		for (Direction direction : Direction.values()) {
			if (b.isAlive(position.getNeighbourAt(direction))) {
				++count;
			}
		}
		return count;
	}
}

public class GameOfLifeTest {
}

package com.janosgyerik.stackoverflow.user16547;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

class ScoreCounter {
	public int score = 1;
	private int level = 0;
	private boolean increased100500;
	private boolean increased5001500;
	private boolean increased15003000;

	private final int[] thresholds = new int[]{100, 500, 1500};
	private final Set<Integer> passedThresholds = new HashSet<>();

	public void increaseScore() {
		for (int threshold : thresholds) {
			if (score >= threshold && !passedThresholds.contains(threshold)) {
				passedThresholds.add(threshold);
				increase();
				break;
			}
		}
	}

	public void increaseScore2() {
		if (score >= 100 && !increased100500) {
			increase();
			increased100500 = true;
		} else if (score >= 500 && !increased5001500) {
			increase();
			increased5001500 = true;
		} else if (score >= 1500 && !increased15003000) {
			increase();
			increased15003000 = true;
		}
	}

	private void increase() {
		++level;
	}

	public int getLevel() {
		return level;
	}
}

public class ScoreRangeTest {

	@Test
	public void testNoIncrease() {
		ScoreCounter counter = new ScoreCounter();
		counter.increaseScore();
		assertEquals(0, counter.getLevel());
	}

	@Test
	public void testIncreaseOnce() {
		ScoreCounter counter = new ScoreCounter();
		counter.increaseScore();
		assertEquals(0, counter.getLevel());

		counter.score = 200;
		counter.increaseScore();
		assertEquals(1, counter.getLevel());
		counter.increaseScore();
		assertEquals(1, counter.getLevel());
	}

	@Test
	public void testIncreaseTwice() {
		ScoreCounter counter = new ScoreCounter();
		counter.increaseScore();
		assertEquals(0, counter.getLevel());

		counter.score = 200;
		counter.increaseScore();
		assertEquals(1, counter.getLevel());
		counter.increaseScore();
		assertEquals(1, counter.getLevel());

		counter.score = 600;
		counter.increaseScore();
		assertEquals(2, counter.getLevel());
		counter.increaseScore();
		assertEquals(2, counter.getLevel());
	}

	@Test
	public void testIncreaseThrice() {
		ScoreCounter counter = new ScoreCounter();
		counter.increaseScore();
		assertEquals(0, counter.getLevel());

		counter.score = 5200;
		counter.increaseScore();
		assertEquals(1, counter.getLevel());
		counter.increaseScore();
		assertEquals(2, counter.getLevel());
		counter.increaseScore();
		assertEquals(3, counter.getLevel());
		counter.increaseScore();
		assertEquals(3, counter.getLevel());
	}
}

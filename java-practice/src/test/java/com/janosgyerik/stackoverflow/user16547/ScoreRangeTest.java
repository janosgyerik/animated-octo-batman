package com.janosgyerik.stackoverflow.user16547;

import org.junit.Before;

public class ScoreRangeTest {
	private int Score;
	private boolean Increased100500;
	private boolean Increased5001500;
	private boolean Increased15003000;

	@Before
	public void setUp() {
		Score = 1;
	}
	private void getDifficulty() {
		if (Score >= 100 && !Increased100500)
		{
			Increase();
			Increased100500 = true;
		}
		else if (Score >= 500 && !Increased5001500)
		{
			Increase();
			Increased5001500 = true;
		}
		else if (Score >= 1500 && !Increased15003000)
		{
			Increase();
			Increased15003000 = true;
		}
	}

	private void Increase() {

	}
}

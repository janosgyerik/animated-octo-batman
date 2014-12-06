package com.janosgyerik.codereview.mannymeng;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

enum Card {
	ACE_AS_ONE("A", 1),
	TWO("2", 2),
	THREE("3", 3),
	// and so on
	KING("K", 10);

	private final String name;
	private final int value;

	Card(String name, int value) {
		this.name = name;
		this.value = value;
	}

	public static Card decode(String name) {
		for (Card card : Card.values()) {
			if (card.name.equalsIgnoreCase(name)) {
				return card;
			}
		}
		return null;
	}
}

class CardNumber {
	private final String name;
	private final int value;

	public static final CardNumber ACE_AS_ONE = new CardNumber("A", 1);
	public static final CardNumber TWO = new CardNumber("2", 2);
	public static final CardNumber THREE = new CardNumber("3", 3);
	public static final CardNumber FOUR = new CardNumber("4", 4);
	public static final CardNumber FIVE = new CardNumber("5", 5);
	public static final CardNumber SIX = new CardNumber("6", 6);
	public static final CardNumber SEVEN = new CardNumber("7", 7);
	public static final CardNumber EIGHT = new CardNumber("8", 8);
	public static final CardNumber NINE = new CardNumber("9", 9);
	public static final CardNumber TEN = new CardNumber("10", 10);
	public static final CardNumber JACK = new CardNumber("J", 11);
	public static final CardNumber QUEEN = new CardNumber("Q", 12);
	public static final CardNumber KING = new CardNumber("K", 13);
	public static final CardNumber ACE = new CardNumber("A", 14);

	private CardNumber(String name, int value) {
		this.name = name;
		this.value = value;
	}

	@Override
	public String toString() {
		return name;
	}

	public static CardNumber decode(String s) {
		if (s == null) {
			return null;
		} else if (s.equalsIgnoreCase(ACE.toString())) {
			return ACE;
		} else if (s.equalsIgnoreCase(TWO.toString())) {
			return TWO;
		} else if (s.equalsIgnoreCase(THREE.toString())) {
			return THREE;
		} else if (s.equalsIgnoreCase(FOUR.toString())) {
			return FOUR;
		} else if (s.equalsIgnoreCase(FIVE.toString())) {
			return FIVE;
		} else if (s.equalsIgnoreCase(SIX.toString())) {
			return SIX;
		} else if (s.equalsIgnoreCase(SEVEN.toString())) {
			return SEVEN;
		} else if (s.equalsIgnoreCase(EIGHT.toString())) {
			return EIGHT;
		} else if (s.equalsIgnoreCase(NINE.toString())) {
			return NINE;
		} else if (s.equalsIgnoreCase(TEN.toString())) {
			return TEN;
		} else if (s.equalsIgnoreCase(JACK.toString())) {
			return JACK;
		} else if (s.equalsIgnoreCase(QUEEN.toString())) {
			return QUEEN;
		} else if (s.equalsIgnoreCase(KING.toString())) {
			return KING;
		} else {
			return null;
		}
	}

	public int getValue() {
		return value;
	}
}

public class CardTest {
	@Test
	public void testDecodeNumeric() {
		assertEquals(Card.TWO, Card.decode("2"));
		assertEquals(Card.THREE, Card.decode("3"));
	}

	@Test
	public void testDecodeLabeled() {
		assertEquals(Card.KING, Card.decode("K"));
		assertEquals(Card.KING, Card.valueOf("KING"));
	}

	@Test
	public void testNonexistent() {
		assertNull(Card.decode("nonexistent"));
	}

	@Test
	public void testDecodeNull() {
		assertNull(Card.decode(null));
	}
}

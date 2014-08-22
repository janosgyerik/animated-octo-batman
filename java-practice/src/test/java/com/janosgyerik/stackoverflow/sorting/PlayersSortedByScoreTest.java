package com.janosgyerik.stackoverflow.sorting;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class Player {
	final String name;
	final int score;

	Player(String name, int score) {
		this.name = name;
		this.score = score;
	}

}
class PlayerComparatorByScore implements Comparator<Player> {
	@Override
	public int compare(Player o1, Player o2) {
		return -Integer.compare(o1.score, o2.score);
	}
}

class PlayerUtil {
	static Collection<Player> getHighestScoringPlayers(Collection<Player> players) {
		Iterator<Player> iterator = players.iterator();
		Player sample = iterator.next();

		List<Player> highestScoringPlayers = new ArrayList<>();
		highestScoringPlayers.add(sample);
		int maxScore = sample.score;

		while (iterator.hasNext()) {
			Player player = iterator.next();
			if (player.score > maxScore) {
				highestScoringPlayers.clear();
				highestScoringPlayers.add(player);
			} else if (player.score == maxScore) {
				highestScoringPlayers.add(player);
			}
		}

		return highestScoringPlayers;
	}

	static Collection<Player> getHighestScoringPlayers3(Collection<Player> players) {
		List<Player> sortedPlayers = new ArrayList<>(players);
		Collections.sort(sortedPlayers, new PlayerComparatorByScore());

		Set<Player> highestScoringPlayers = new HashSet<>();
		Iterator<Player> iterator = sortedPlayers.iterator();
		Player highestScoringPlayer = iterator.next();
		highestScoringPlayers.add(highestScoringPlayer);

		while (iterator.hasNext()) {
			Player player = iterator.next();
			if (player.score == highestScoringPlayer.score) {
				highestScoringPlayers.add(player);
			} else {
				break;
			}
		}

		return highestScoringPlayers;
	}

	static Collection<Player> getHighestScoringPlayers2(Collection<Player> players) {
		TreeMap<Integer, Set<Player>> scoresToPlayers = new TreeMap<>();

		for (Player player : players) {
			Set<Player> playersWithScore = scoresToPlayers.get(player.score);
			if (playersWithScore == null) {
				playersWithScore = new HashSet<>();
				scoresToPlayers.put(player.score, playersWithScore);
			}
			playersWithScore.add(player);
		}

		return scoresToPlayers.lastEntry().getValue();
	}
}

public class PlayersSortedByScoreTest {
	@Test
	public void testSortingByScore() {
		Collection<Player> players = new HashSet<>();
		players.add(new Player("Alice", 3));
		players.add(new Player("Bob", 1));
		players.add(new Player("Mike", 3));

		Collection<Player> highestScoringPlayers = PlayerUtil.getHighestScoringPlayers(players);

		assertEquals(2, highestScoringPlayers.size());
		assertEquals(3, highestScoringPlayers.iterator().next().score);
	}

	@Test(expected = NoSuchElementException.class)
	public void testEmptyCollection() {
		PlayerUtil.getHighestScoringPlayers(new HashSet<Player>());
	}
}

package com.janosgyerik.codereview.shree.philosophers3;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

class DiningAlgorithm {
	public static void main(String[] args) {
		int num = 5;

		ForkFactory forkFactory = new ForkFactory();
		PhilosopherFactory philosopherFactory = new PhilosopherFactory();

		Fork[] forks = new Fork[num];
		for (int i = 0; i < num; ++i) {
			forks[i] = forkFactory.create();
		}

		for (int i = 0; i < num - 1; ++i) {
			Fork fork1 = forks[i];
			Fork fork2 = forks[i + 1];
			new Thread(philosopherFactory.create(fork1, fork2)).start();
		}
		new Thread(philosopherFactory.create(forks[num - 1], forks[0])).start();
	}
}

public class DiningAlgorithmTest {
	@Test
	public void test() {

	}
}

class ForkFactory {
	private static AtomicInteger num = new AtomicInteger(0);

	Fork create() {
		return new Fork(num.getAndIncrement());
	}
}

class PhilosopherFactory {
	private static AtomicInteger num = new AtomicInteger(0);

	Philosopher create(Fork fork1, Fork fork2) {
		return new Philosopher(num.getAndIncrement(), fork1, fork2);
	}
}

class Philosopher implements Runnable {

	private static final int TIME_WHEN_PHILOSOPHER_FEELS_HUNGRY_AGAIN = 10;
	private static final int TIME_REQUIRED_TO_EAT = 5;
	private final int id;
	private Waiter waiter = new Waiter();
	private Fork leftFork;
	private Fork rightFork;

	public Philosopher(int id, Fork fork1, Fork fork2) {
		this.id = id;
		this.leftFork = fork1;
		this.rightFork = fork2;
	}

	private void pickUpLeftFork() {
		if (null == leftFork) {
			leftFork = waiter.getForkOnLeft(id);
		}
	}

	private void pickUpRightFork() {
		if (null == rightFork) {
			rightFork = waiter.getForkOnRight(id);
		}
	}

	private void tryToEat() throws InterruptedException {

		pickUpLeftFork();
		pickUpRightFork();
		if (bothForksAvailable()) {
			System.out.println("both forks " + leftFork.getID() + " & " + rightFork.getID() + " are available for use for Philosopher " + id);
			eat(TIME_REQUIRED_TO_EAT);
		}

		putDownBothForks();
	}

	private void putDownBothForks() {
		if (null != rightFork) {
			rightFork.putDownFork();
		}
		if (null != leftFork) {
			leftFork.putDownFork();
		}
		leftFork = null;
		rightFork = null;
	}

	private void eat(long miliseconds) throws InterruptedException {
		System.out.println(id + " has started eating");
		eatFor(miliseconds);
		System.out.println(id + " has finished eating");
	}

	private void eatFor(long miliseconds) throws InterruptedException {
		waitFor(miliseconds);
	}

	private void waitUntilHungry() throws InterruptedException {
		waitFor(TIME_WHEN_PHILOSOPHER_FEELS_HUNGRY_AGAIN);
	}

	private void waitFor(long milliseconds) throws InterruptedException {
		Thread.currentThread().sleep(milliseconds);
	}

	public boolean bothForksAvailable() {
		return null != leftFork && null != rightFork;
	}

	@Override
	public void run() {
		System.out.println("Thread for philosopher " + id + " started");
		while (true) {
			try {
				tryToEat();
				waitUntilHungry();
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception Occured, Reason " + e.getMessage());
			}
		}

	}
}

class Waiter {
	private static Map<Integer, Fork> map = new HashMap<Integer, Fork>() {{
		put(1, new Fork(1));
		put(2, new Fork(2));
		put(3, new Fork(3));
		put(4, new Fork(4));
		put(5, new Fork(5));
	}};

	private boolean canUseFork(int forkId) {
		return !map.get(forkId).isBeingUsed();
	}

	public Fork getForkOnLeft(int philosopherId) {
		return getFork(philosopherId + 1);
	}

	private Fork getFork(int forkId) {
		if (canUseFork(forkId)) {
			map.get(forkId).useFork();
			return map.get(forkId);
		}
		return null;
	}

	public Fork getForkOnRight(int philosopherId) {
		if (philosopherId == 0) {
			return getFork(map.size());
		} else {
			return getFork(philosopherId);
		}
	}
}

class Fork {
	int id;
	boolean isBeingUsed;

	public Fork(int id) {
		this.id = id;
	}

	public boolean isBeingUsed() {
		return isBeingUsed;
	}

	public void useFork() {
		isBeingUsed = true;
	}

	public void putDownFork() {
		isBeingUsed = false;
	}

	public int getID() {
		return id;
	}

}
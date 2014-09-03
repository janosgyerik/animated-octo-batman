package com.janosgyerik.stackoverflow.shree.philosophers;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

class DiningAlgorithm {
	public static void main(String[] args) {
		Thread thread1 = new Thread(new Philosopher());
		Thread thread2 = new Thread(new Philosopher());
		Thread thread3 = new Thread(new Philosopher());
		Thread thread4 = new Thread(new Philosopher());
		Thread thread5 = new Thread(new Philosopher());
		thread1.start();
		thread2.start();
		thread3.start();
		thread4.start();
		thread5.start();
	}
}

public class DiningAlgorithmTest {
	@Test
	public void test() {

	}
}

class Philosopher implements Runnable {
	private static final int TIME_WHEN_PHILOSOPHER_FEELS_HUNGRY_AGAIN = 10;
	private static final int TIME_REQUIRED_TO_EAT = 5;
	private static int noOfPhilosophers =0;
	private int id;
	private Waiter waiter = new  Waiter();
	private Fork leftFork;
	private Fork rightFork;
	public Philosopher() {
		this.id=noOfPhilosophers;
		noOfPhilosophers++;
	}
	private void pickUpLeftFork(){
		if(null==leftFork){
			leftFork = waiter.getForkOnLeft(id);
		}
	}
	private void pickUpRightFork(){
		if(null==rightFork){
			rightFork = waiter.getForkOnRight(id);
		}
	}

	private void eat() throws InterruptedException{

		pickUpLeftFork();
		pickUpRightFork();
		if(bothForksAvailable()){
			System.out.println("both forks "+leftFork.getID() +" & "+rightFork.getID()+" are available for use for Philosopher "+id);
			eat(TIME_REQUIRED_TO_EAT);
		}

		putDownBothForks();
	}
	private void putDownBothForks() {
		if(null!=rightFork) {
			rightFork.putDownFork();
		}
		if(null!=leftFork){
			leftFork.putDownFork();
		}
		leftFork = null;
		rightFork =null;
	}
	private void eat(long miliseconds) throws InterruptedException {
		System.out.println(id+" has started eating");
		eatFor(miliseconds);
		System.out.println(id+" has finished eating");
	}
	private void eatFor(long miliseconds) throws InterruptedException {
		waitFor(miliseconds);
	}

	private void waitTillHeFeelsHungry() throws InterruptedException {
		waitFor(TIME_WHEN_PHILOSOPHER_FEELS_HUNGRY_AGAIN);
	}

	private void waitFor(long milliseconds) throws InterruptedException {
		Thread.currentThread().sleep(milliseconds);
	}

	public boolean bothForksAvailable(){
		return null!=leftFork && null!= rightFork;
	}
	@Override
	public void run() {
		System.out.println("Thread for philosopher "+id +" started");
		while(true){
			try {
				eat();
				waitTillHeFeelsHungry();
			} catch (InterruptedException e) {
				System.out.println("Interrupted Exception Occured, Reason "+e.getMessage());
			}
		}

	}
}

class Waiter {
	private static Map<Integer,Fork> map = new HashMap<Integer,Fork>(){{
		put(1,new Fork(1));
		put(2,new Fork(2));
		put(3,new Fork(3));
		put(4,new Fork(4));
		put(5,new Fork(5));
	}};

	private boolean canUseFork(int forkId){
		return !map.get(forkId).isBeingUsed();
	}

	public Fork getForkOnLeft(int philosopherId){
		return getFork(philosopherId+1);
	}

	private Fork getFork(int forkId) {
		if(canUseFork(forkId)){
			map.get(forkId).useFork();
			return map.get(forkId);
		}
		return null;
	}

	public Fork getForkOnRight(int philosopherId){
		if(philosopherId==0){
			return getFork(map.size());
		}
		else{
			return getFork(philosopherId);
		}
	}
}

class Fork {
	int id;
	boolean isBeingUsed ;
	public Fork(int id){
		this.id = id;
	}

	public boolean isBeingUsed(){
		return isBeingUsed;
	}

	public void useFork(){
		isBeingUsed = true;
	}

	public void putDownFork(){
		isBeingUsed = false;
	}

	public int getID() {
		return id;
	}

}
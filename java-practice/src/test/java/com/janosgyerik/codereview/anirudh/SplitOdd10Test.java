package com.janosgyerik.codereview.anirudh;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SplitOdd10Test {
	private boolean splitOdd10(int[] nums) {
		return splitOdd10(nums, 0, 0, sum(nums));
	}

	private boolean splitOdd10(int[] nums, int i, int left, int right) {
		return left % 10 == 0 && right % 2 == 1
				|| i < nums.length && splitOdd10(nums, i + 1, left + nums[i], right - nums[i]);
	}

	private int sum(int[] nums) {
		return findSum(nums, 0, 0);
	}

	private int findSum(int[] nums, int i, int accum) {
		if (i == nums.length) {
			return accum;
		}
		return findSum(nums, i + 1, nums[i] + accum);
	}

	@Test
	public void testEmpty() {
		assertFalse(splitOdd10(new int[0]));
	}

	@Test
	public void testSingleNum() {
		assertTrue(splitOdd10(new int[]{1}));
		assertFalse(splitOdd10(new int[]{2}));
		assertFalse(splitOdd10(new int[]{10}));
	}

	@Test
	public void test555() {
		assertTrue(splitOdd10(new int[]{5, 5, 5}));
	}

	@Test
	public void test556() {
		assertFalse(splitOdd10(new int[]{5, 5, 6}));
	}

	@Test
	public void test5561() {
		assertTrue(splitOdd10(new int[]{5, 5, 6, 1}));
	}

	@Test
	public void test5561_17() {
		assertFalse(splitOdd10(new int[]{5, 5, 6, 1, 17}));
	}

	@Test
	public void test5561_17_1() {
		assertTrue(splitOdd10(new int[]{5, 5, 6, 1, 17, 1}));
	}

}
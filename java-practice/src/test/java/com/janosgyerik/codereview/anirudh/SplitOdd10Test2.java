package com.janosgyerik.codereview.anirudh;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SplitOdd10Test2 {

	public boolean splitOdd10(int[] nums) {
		int total = findSum(0, nums, 0);
		if ((total % 10) % 2 == 0 || total == 0) {
			return false;
		}
		return ifGroupExistsThatSumsToValue(0, nums, total % 10);
	}

	public int findSum(int[] nums) {
		return findSum(0, nums, 0);
	}

	public int findSum(int n, int[] nums, int sum) {
		if (nums.length == 0)
			return 0;
		if (n == nums.length - 1) {
			return sum + nums[n];
		}
		return findSum(n + 1, nums, sum + nums[n]);
	}

	public int findSum2(int[] nums) {
		return findSum2(nums, 0, 0);
	}

	public int findSum2(int[] nums, int i, int accum) {
		if (i == nums.length) {
			return accum;
		}
		return findSum2(nums, i + 1, nums[i] + accum);
	}

	public int findSum3(int[] nums, int i) {
		if (i < 0) {
			return 0;
		}
		return nums[i] + findSum3(nums, i - 1);
	}

	public int findSum4(int[] nums, int i, int accum) {
		if (i == nums.length) {
			return accum;
		}
		return findSum4(nums, i + 1, nums[i] + accum);
	}

	public boolean ifGroupExistsThatSumsToValue(int start, int[] nums, int target) {
		if (start == nums.length - 1) {
			return target - nums[start] == 0;
		}
		if (target == 0) {
			return true;
		}
		return ifGroupExistsThatSumsToValue(start + 1, nums, target - nums[start])
				|| ifGroupExistsThatSumsToValue(start + 1, nums, target + nums[start]);
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

//	@Test
	public void testTailRecursiveFindsum() {
		int[] arr = new int[20000];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = i % 3;
		}
		assertEquals(3, findSum(arr));
	}

//	@Test
	public void testTailRecursiveFindsum2() {
		int[] arr = new int[20000];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = i % 3;
		}
		assertEquals(3, findSum2(arr));
	}

//	@Test
	public void testTailRecursiveFindsum4() {
		int[] arr = new int[20000];
		for (int i = 0; i < arr.length; ++i) {
			arr[i] = 0;
		}
		assertEquals(0, findSum4(arr, 0, 0));
	}

	private int sumN(int n, int accum) {
		if (n == 0) {
			return accum;
		}
		return sumN(n - 1, n + accum);
	}

//	@Test
	public void testTailRecursiveSum() {
		assertEquals(2001000, sumN(16000, 0));
	}

}
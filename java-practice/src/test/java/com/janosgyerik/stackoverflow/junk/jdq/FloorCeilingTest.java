package com.janosgyerik.stackoverflow.junk.jdq;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

class FloorCeiling {

	private TreeNode root;

	public TreeNode getRoot() {
		return root;
	}

	public FloorCeiling(List<Integer> items) {
		create(items);
	}


	private void create(List<Integer> items) {
		if (items.isEmpty()) {
			throw new NullPointerException("The items array is empty.");
		}

		root = new TreeNode(items.get(0));

		final Queue<TreeNode> queue = new LinkedList<>();
		queue.add(root);

		final int half = items.size() / 2;

		for (int i = 0; i < half; i++) {
			if (items.get(i) != null) {
				final TreeNode current = queue.poll();
				final int left = 2 * i + 1;
				final int right = 2 * i + 2;

				if (items.get(left) != null) {
					current.left = new TreeNode(items.get(left));
					queue.add(current.left);
				}
				if (right < items.size() && items.get(right) != null) {
					current.right = new TreeNode(items.get(right));
					queue.add(current.right);
				}
			}
		}
	}

	class TreeNode {
		public TreeNode left;
		public int item;
		public TreeNode right;

		TreeNode(int item) {
			this.item = item;
		}
	}

	private static class IntegerObj {
		Integer obj = null;
	}

	public Integer ceiling2(int val) {
		IntegerObj iobj = new IntegerObj();
		recurseCeiling2(root, iobj, val);
		return iobj.obj;
	}

	public int ceiling(int val) {
		Integer ceil = recurseCeiling(root, null, val);
		if (ceil != null) {
			return ceil;
		}
		throw new NoSuchElementException("No ceiling for " + val);
	}

	private Integer recurseCeiling(TreeNode node, Integer ceil, int value) {
		if (node == null) {
			return ceil;
		}
		if (value <= node.item) {
			return recurseCeiling(node.left, node.item, value);
		}
		return recurseCeiling(node.right, ceil, value);
	}


	public Integer floor(int val) {
		IntegerObj iobj = new IntegerObj();
		recurseFloor(root, iobj, val);
		return iobj.obj;
	}

	private void recurseCeiling2(TreeNode node, IntegerObj iobj, int value) {
		if (node == null) {
			return;
		}
		if (value <= node.item) {
			iobj.obj = node.item;
			recurseCeiling2(node.left, iobj, value);
		} else {
			recurseCeiling2(node.right, iobj, value);
		}
	}


	private void recurseFloor(TreeNode node, IntegerObj iobj, int value) {
		if (node == null) {
			return;
		}
		if (value < node.item) {
			recurseFloor(node.left, iobj, value);
		} else {
			iobj.obj = node.item;
			recurseFloor(node.right, iobj, value);
		}
	}
}


public class FloorCeilingTest {

	@Test
	public void testCeiling() {
		List<Integer> list = Arrays.asList(100, 50, 150, 25, 75, 125, 175);
		FloorCeiling fc1 = new FloorCeiling(list);
		for (int item : list) {
			assertEquals(item, fc1.ceiling(item));
			assertEquals(item, fc1.ceiling(item - 1));
		}
	}

	@Test(expected = NoSuchElementException.class)
	public void testNoCeiling() {
		List<Integer> list = Arrays.asList(100, 50, 150, 25, 75, 125, 175);
		FloorCeiling fc1 = new FloorCeiling(list);
		fc1.ceiling(Collections.max(list) + 1);
	}

	@Test
	public void testFloor() {
		List<Integer> list = Arrays.asList(100, 50, 150, 25, 75, 125, 175);
		FloorCeiling fc1 = new FloorCeiling(list);
		for (Integer item : list) {
			assertEquals(item, fc1.floor(item));
			assertEquals(item, fc1.floor(item + 1));
		}
		assertNull(fc1.floor(Collections.min(list) - 1));
	}

	@Test
	public void testBST() {
		List<Integer> list = Arrays.asList(100, 50, 150, 25, 75, 125, 175);
		FloorCeiling fc1 = new FloorCeiling(list);
		assertTrue(isBST(fc1.getRoot(), Integer.MAX_VALUE, Integer.MIN_VALUE));
	}

	public static boolean isBST(FloorCeiling.TreeNode node, int maxData, int minData) {
		if (node == null) {
			return true;
		}

		if (node.item >= maxData || node.item <= minData) {
			return false;
		}

		return isBST(node.left, node.item, minData) && isBST(node.right, maxData, node.item);
	}

}
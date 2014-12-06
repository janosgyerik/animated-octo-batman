package com.janosgyerik.codereview.javadeveloper.completebinarytree;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class CompleteBinaryTreeDetection<T> {

	private TreeNode<T> root;

	/**
	 * Constructs a binary tree in order of elements in an array.
	 * After the number of nodes in the level have maxed, the next
	 * element in the array would be a child of leftmost node.
	 */
	public CompleteBinaryTreeDetection(List<T> items) {
		create(items);
	}

	private void create(List<T> items) {
		root = new TreeNode<>(null, items.get(0), null);

		final Queue<TreeNode<T>> queue = new LinkedList<>();
		queue.add(root);

		final int half = items.size() / 2;

		int i = 0;
		for (T item : items) {
			if (i >= half) {
				break;
			}
			if (item != null) {
				final TreeNode<T> current = queue.poll();
				int left = 2 * i + 1;
				int right = 2 * i + 2;

				if (items.get(left) != null) {
					current.left = new TreeNode<>(items.get(left));
					queue.add(current.left);
				}
				if (right < items.size() && items.get(right) != null) {
					current.right = new TreeNode<>(items.get(right));
					queue.add(current.right);
				}
			}
			++i;
		}
	}

	private static class TreeNode<T> {
		TreeNode<T> left;
		final T item;
		TreeNode<T> right;

		public TreeNode(TreeNode<T> left, T item, TreeNode<T> right) {
			this.left = left;
			this.item = item;
			this.right = right;
		}

		public TreeNode(T item) {
			this(null, item, null);
		}
	}

	public boolean isComplete() {
		if (root == null) {
			throw new NoSuchElementException();
		}
		ConcurrentHashMap x;
		final Queue<TreeNode<T>> queue = new LinkedList<>();
		queue.add(root);

		boolean incompleteDetected = false;

		while (!queue.isEmpty()) {
			TreeNode<T> node = queue.poll();

			if (node.left != null) {
				if (incompleteDetected) return false;
				queue.add(node.left);
			} else {
				incompleteDetected = true;
			}

			if (node.right != null) {
				if (incompleteDetected) return false;
				queue.add(node.right);
			} else {
				incompleteDetected = true;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return toString(root);
	}

	private String toString(TreeNode<T> node) {
		if (node == null) {
			return null;
		}
		return "[" + toString(node.left)
				+ "," + node.item
				+ "," + toString(node.right) + "]";
	}
}

public class CompleteBinaryTreeTest {

	@Test
	public void test1() {
		/**
		 *         1
		 *       2   3
		 *     4  n  n n
		 */
		CompleteBinaryTreeDetection<Integer> createTree1 = new CompleteBinaryTreeDetection<>(Arrays.asList(1, 2, 3, 4, null, null, null));
		assertTrue(createTree1.isComplete());
	}


	@Test
	public void test2() {
		/**
		 *         1
		 *       2   3
		 *     4  5  n n
		 */
		CompleteBinaryTreeDetection<Integer> createTree2 = new CompleteBinaryTreeDetection<>(Arrays.asList(1, 2, 3, 4, 5, null, null));
		assertTrue(createTree2.isComplete());
	}

	@Test
	public void test3() {
		/**
		 *         1
		 *       2   3
		 *     4  5  6 n
		 */
		CompleteBinaryTreeDetection<Integer> createTree3 = new CompleteBinaryTreeDetection<>(Arrays.asList(1, 2, 3, 4, 5, 6, null));
		assertTrue(createTree3.isComplete());
	}

	@Test
	public void test4() {
		/**
		 *         1
		 *       2   3
		 *     4  5  6  7
		 */
		CompleteBinaryTreeDetection<Integer> createTree4 = new CompleteBinaryTreeDetection<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		assertTrue(createTree4.isComplete());
	}

	@Test
	public void test5() {
		/**
		 *         1
		 *       2   3
		 *     4  5  6  7
		 *       8
		 */
		CompleteBinaryTreeDetection<Integer> createTree5 = new CompleteBinaryTreeDetection<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, null, 8));
		assertFalse(createTree5.isComplete());
	}

	@Test
	public void test6() {
		/**
		 *         1
		 *       2   3
		 *     4  5   6  7
		 *       8 9
		 */
		CompleteBinaryTreeDetection<Integer> createTree6 = new CompleteBinaryTreeDetection<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, null, 8, 9));
		assertFalse(createTree6.isComplete());
	}

	@Test
	public void test7() {
		/**
		 *         1
		 *       2   3
		 *     4  5   6  7
		 *           8
		 */
		CompleteBinaryTreeDetection<Integer> createTree7 = new CompleteBinaryTreeDetection<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, null, null, null, null, 8));
		assertFalse(createTree7.isComplete());
	}

	@Test
	public void test8() {
		/**
		 *         1
		 *
		 */
		CompleteBinaryTreeDetection<Integer> createTree8 = new CompleteBinaryTreeDetection<>(Arrays.asList(1));
		assertTrue(createTree8.isComplete());
	}

	@Test
	public void test9() {
		/**
		 *         1
		 *       2   3
		 *     4  5   6  7
		 *   8       9
		 */
		CompleteBinaryTreeDetection<Integer> createTree9 = new CompleteBinaryTreeDetection<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, null, null, null, 9));
		assertFalse(createTree9.isComplete());
	}

	@Test
	public void testEmpty() {
//		new CompleteBinaryTreeDetection<>(Arrays.asList());
	}

}

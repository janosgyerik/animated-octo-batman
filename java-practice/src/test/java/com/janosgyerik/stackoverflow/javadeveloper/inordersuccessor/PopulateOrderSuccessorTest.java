package com.janosgyerik.stackoverflow.javadeveloper.inordersuccessor;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class PopulateInorderSuccessor<T> {

	private TreeNode<T> root;

	public PopulateInorderSuccessor(List<T> items) {
		create (items);
	}

	public PopulateInorderSuccessor(TreeNode<T> root) {
		this.root = root;
	}

	private void create(List<T> items) {
		if (items.size() == 0) {
			throw new IllegalArgumentException("There should atlease be single item in the tree");
		}

		root = new TreeNode<>(items.get(0));

		final Queue<TreeNode<T>> queue = new LinkedList<TreeNode<T>>();
		queue.add(root);

		final int half = items.size() / 2;

		for (int i = 0; i < half; i++) {
			if (items.get(i) != null) {
				final TreeNode<T> current = queue.poll();
				final int left = 2 * i + 1;
				final int right = 2 * i + 2;

				if (items.get(left) != null) {
					current.left = new TreeNode<T>(items.get(left));
					queue.add(current.left);
				}
				if (right < items.size() && items.get(right) != null) {
					current.right = new TreeNode<T>(items.get(right));
					queue.add(current.right);
				}
			}
		}
	}

	static class TreeNode<T> {
		private TreeNode<T> left;
		private T item;
		private TreeNode<T> right;
		private TreeNode<T> next;

		TreeNode(T item) {
			this.item = item;
		}

		public TreeNode(T item, TreeNode<T> left, TreeNode<T> right) {
			this.item = item;
			this.left = left;
			this.right = right;
		}
	}

	private static class TreeNodeHolder<T> {
		private TreeNode<T> treeNode;
	}

	public static <T> TreeNode<T> newNode(T item, TreeNode<T> left, TreeNode<T> right) {
		return new TreeNode<>(item, left, right);
	}

	public static <T> TreeNode<T> newNode(T item) {
		return new TreeNode<>(item, null, null);
	}

	public void populateInorderSuccessor() {
		populate(root, new TreeNodeHolder<T>());
	}

	private void populate(TreeNode<T> node, TreeNodeHolder<T> treeNodeHolder) {
		if (node != null) {
			populate(node.right, treeNodeHolder);
			node.next = treeNodeHolder.treeNode;
			treeNodeHolder.treeNode = node;
			populate(node.left, treeNodeHolder);
		}
	}

	public List<T> toInOrderList() {
		TreeNode<T> node = root;

		while (node.left != null) {
			node = node.left;
		}

		final List<T> list = new ArrayList<>();
		while (node != null) {
			list.add(node.item);
			node = node.next;
		}
		return list;
	}

	@Override
	public String toString() {
		return toString(root);
	}

	private String toString(TreeNode<T> node) {
		if (node == null) {
			return "null";
		}
		StringBuilder builder = new StringBuilder();
		return builder.append("[")
				.append(toString(node.left))
				.append(", ")
				.append(node.item)
				.append(", ")
				.append(toString(node.right))
				.append("]")
				.toString();
	}
}


public class PopulateOrderSuccessorTest {

	@Test
	public void testCompleteTree() {
//		PopulateInorderSuccessor<Integer> pis1 = new PopulateInorderSuccessor<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));
		PopulateInorderSuccessor<Integer> pis1 = new PopulateInorderSuccessor<Integer>(
				PopulateInorderSuccessor.newNode(1,
						PopulateInorderSuccessor.newNode(2, PopulateInorderSuccessor.newNode(4), PopulateInorderSuccessor.newNode(5)),
						PopulateInorderSuccessor.newNode(3, PopulateInorderSuccessor.newNode(6), PopulateInorderSuccessor.newNode(7)))
		);
		pis1.populateInorderSuccessor();
		assertEquals(Arrays.asList(4, 2, 5, 1, 6, 3, 7), pis1.toInOrderList());
	}

	@Test
	public void testLeftSkewedTree() {
		// left skewed
//		PopulateInorderSuccessor<Integer> pis2 = new PopulateInorderSuccessor<Integer>(Arrays.asList(1, 2, null, 4));
		PopulateInorderSuccessor<Integer> pis2 = new PopulateInorderSuccessor<Integer>(
				PopulateInorderSuccessor.newNode(1,
						PopulateInorderSuccessor.newNode(2, PopulateInorderSuccessor.newNode(4), null),
						null)
		);
		pis2.populateInorderSuccessor();
		assertEquals(Arrays.asList(4, 2, 1), pis2.toInOrderList());
	}

	@Test
	public void testRightSkewedTree() {
		// right skewed
//		PopulateInorderSuccessor<Integer> pis3 = new PopulateInorderSuccessor<Integer>(Arrays.asList(1, null, 3, null, null, null, 7));
		PopulateInorderSuccessor<Integer> pis3 = new PopulateInorderSuccessor<Integer>(
				PopulateInorderSuccessor.newNode(1,
						null,
						PopulateInorderSuccessor.newNode(3, null, PopulateInorderSuccessor.newNode(7)))
		);
		pis3.populateInorderSuccessor();
		assertEquals(Arrays.asList(1, 3, 7), pis3.toInOrderList());
	}

	@Test
	public void testLevel4() {
		// right skewed
//		PopulateInorderSuccessor<Integer> pis3 = new PopulateInorderSuccessor<Integer>(Arrays.asList(1, null, 3, null, null, null, 7));
//		PopulateInorderSuccessor<Integer> pis3 = new PopulateInorderSuccessor<Integer>(Arrays.asList(4, 2, 6, null, null, null, 7));
		PopulateInorderSuccessor<Integer> pis3 = new PopulateInorderSuccessor<Integer>(
				PopulateInorderSuccessor.newNode(4,
						PopulateInorderSuccessor.newNode(2),
						PopulateInorderSuccessor.newNode(6,
								null,
								PopulateInorderSuccessor.newNode(7, null, PopulateInorderSuccessor.newNode(15))))
		);
		pis3 = new PopulateInorderSuccessor<Integer>(Arrays.asList(4, 2, 6, null, null, null, 7, null, null, null, null, null, null, null, 15));
		pis3.populateInorderSuccessor();
		assertEquals(Arrays.asList(2, 4, 6, 7, 15), pis3.toInOrderList());
	}
}
package com.janosgyerik.stackoverflow.javadeveloper.createbst;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

// http://codereview.stackexchange.com/questions/31994/creating-a-binary-search-tree/60885#60885
class CreateBinarySearchTree {

	private TreeNode root;

	private static class TreeNode {
		TreeNode left;
		int item;
		TreeNode right;

		TreeNode(TreeNode left, int item, TreeNode right) {
			this.left = left;
			this.right = right;
			this.item = item;
		}
	}

	public void add(int... items) {
		for (int item : items) {
			add(item);
		}
	}

	public void add(int item) {
		if (root == null) {
			root = new TreeNode(null, item, null);
		} else {
			add(root, item);
		}
	}

	public void add(TreeNode node, int item) {
		if (item < node.item) {
			if (node.left == null) {
				node.left = new TreeNode(null, item, null);
			} else {
				add(node.left, item);
			}
		} else if (item > node.item) {
			if (node.right == null) {
				node.right = new TreeNode(null, item, null);
			} else {
				add(node.right, item);
			}
		}
	}

	@Override
	public String toString() {
		return toString(root);
	}

	private String toString(TreeNode node) {
		if (node == null) {
			return null;
		}
		return "[" + toString(node.left) + "," + node.item + "," + toString(node.right) + "]";
	}
}

public class CreateBinarySearchTreeTest {
	@Test
	public void test345() {
		CreateBinarySearchTree tree = new CreateBinarySearchTree();
		tree.add(3, 4, 5);
		assertEquals("[null,3,[null,4,[null,5,null]]]", tree.toString());
	}

	@Test
	public void test453() {
		CreateBinarySearchTree tree = new CreateBinarySearchTree();
		tree.add(4, 5, 3);
		assertEquals("[[null,3,null],4,[null,5,null]]", tree.toString());
	}

	@Test
	public void testNoDups() {
		CreateBinarySearchTree tree = new CreateBinarySearchTree();
		tree.add(4, 4);
		tree.add(4);
		assertEquals("[null,4,null]", tree.toString());
	}

	@Test
	public void testAddingIsTailRecursive() {
		CreateBinarySearchTree tree = new CreateBinarySearchTree();
		for (int i = 0; i < 10000; ++i) {
			tree.add(i);
		}
		// No StackOverflowError, thanks to tail recursive implementation
	}

	@Test(expected = StackOverflowError.class)
	public void testTreeTooDeep() {
		// if you set this high enough, there should be a StackOverflowError,
		// because tree.toString is not tail recursive
		int N = 10000;
		CreateBinarySearchTree tree = new CreateBinarySearchTree();
		for (int i = 0; i < N; ++i) {
			tree.add(i);
		}
		assertEquals(3, tree.toString().length());
	}
}

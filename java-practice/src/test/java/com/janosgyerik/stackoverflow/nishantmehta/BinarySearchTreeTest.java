package com.janosgyerik.stackoverflow.nishantmehta;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

class BST {
	public TreeNode root = null;

	public TreeNode get(int element) {
		TreeNode runner = root;
		while (runner != null) {
			if (runner.data > element) {
				runner = runner.leftNode;
			} else if (runner.data < element) {
				runner = runner.rightNode;
			} else {
				return runner;
			}
		}
		return null;
	}

	public void insert(int element) {
		if (root == null) {
			root = new TreeNode(element);
			return;
		}
		TreeNode runner = root;
		while (runner.data != element) {
			if (runner.data > element) {
				if (runner.leftNode == null) {
					runner.leftNode = new TreeNode(element);
					return;
				}
				runner = runner.leftNode;
			} else {
				if (runner.rightNode == null) {
					runner.rightNode = new TreeNode(element);
					return;
				}
				runner = runner.rightNode;
			}
		}
	}

	public void breadthFirstSearch(TreeNode root) {
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		TreeNode runner;
		while (!queue.isEmpty()) {
			runner = queue.remove();
			if (runner.leftNode != null) {
				queue.add(runner.leftNode);
			}
			if (runner.rightNode != null) {
				queue.add(runner.rightNode);
			}
			System.out.println("visited node " + runner.data);
		}
	}

	public static void depthFirstSearch(TreeNode root) {
		if (root == null) {
			return;
		}
		Stack<TreeNode> stack = new Stack<TreeNode>();
		stack.add(root);
		TreeNode runner;
		while (!stack.empty()) {
			runner = stack.peek();
			if (runner.leftNode != null && !runner.leftNode.visited) {
				stack.add(runner.leftNode);
				continue;
			}
			if (runner.rightNode != null && !runner.rightNode.visited) {
				stack.add(runner.rightNode);
				continue;
			}
			stack.pop();
			runner.visited = true;
			System.out.println("visited node " + runner.data);
		}
	}

	public static void preOrderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		preOrderTraversal(root.leftNode);
		preOrderTraversal(root.rightNode);
	}

	public static void inOrderTraversal(TreeNode root) {
		if (root == null) {
			return;
		}
		inOrderTraversal(root.leftNode);
		System.out.print(root.data + " ");
		inOrderTraversal(root.rightNode);
	}

	public int size() {
		int count = 0;
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.add(root);
		TreeNode runner;
		while (!queue.isEmpty()) {
			runner = queue.remove();
			if (runner != null) {
				++count;
				queue.add(runner.leftNode);
				queue.add(runner.rightNode);
			}
		}
		return count;
	}
}

class TreeNode {
	public final int data;
	public TreeNode leftNode;
	public TreeNode rightNode;
	public boolean visited;

	TreeNode(int data) {
		this.data = data;
	}
}

public class BinarySearchTreeTest {
	private BST createBST(int...items) {
		BST tree = new BST();
		for (int item : items) {
			tree.insert(item);
		}
		return tree;
	}

	@Test
	public void basicTest() {
		BST tree = new BST();
		int[] data = {9, 5, 3, 1, 4, 8, 15, 11, 21, 20, 29};
		for (int i : data) {
			tree.insert(i);
		}
		for (int i : data) {
			assertEquals(i, tree.get(i).data);
		}
	}

	@Test
	public void testSize() {
		assertEquals(0, new BST().size());
		BST tree = new BST();
		int[] data = {9, 5, 3, 1, 4, 8, 15, 11, 21, 20, 29};
		for (int i : data) {
			tree.insert(i);
		}
		assertEquals(data.length, tree.size());
	}

	@Test
	public void testNoSuchElement() {
		BST tree = new BST();
		assertNull(tree.get(3));
	}

	@Test
	public void testNoDuplicateVaues() {
		BST tree = new BST();
		int val = 3;
		tree.insert(val);
		tree.insert(val);
		tree.insert(val);
		assertEquals(1, tree.size());
	}

	@Test
	public void testNonBST() {
		BST tree = new BST();
		int[] data = {9, 5, 5};
		for (int i : data) {
			tree.insert(i);
		}
		assertNull(tree.get(3));
	}
}

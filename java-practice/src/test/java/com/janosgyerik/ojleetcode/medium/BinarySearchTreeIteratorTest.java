package com.janosgyerik.ojleetcode.medium;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Stack;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return String.valueOf(val);
    }
}

class BSTIterator {

    private final LinkedList<TreeNode> nodes = new LinkedList<>();

    public BSTIterator(TreeNode root) {
        populate(root);
    }

    private final void populate(TreeNode node) {
        if (node != null) {
            populate(node.left);
            nodes.add(node);
            populate(node.right);
        }
    }

    public boolean hasNext() {
        return !nodes.isEmpty();
    }

    public int next() {
        return nodes.pollFirst().val;
    }
}

public class BinarySearchTreeIteratorTest {
    private void assertValuesInSequence(BSTIterator iterator) {
        assertTrue(iterator.hasNext());
        int i = 1;
        while (iterator.hasNext()) {
            assertEquals(i++, iterator.next());
        }
        assertFalse(iterator.hasNext());
    }

    @Test
    public void test_1_2_3() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);

        assertValuesInSequence(new BSTIterator(root));
    }

    @Test
    public void test_1_2_3_4_5() {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(5);

        assertValuesInSequence(new BSTIterator(root));
    }
}

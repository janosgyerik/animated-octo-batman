package com.janosgyerik.codereview.javadeveloper.balancedbst;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

class CheckBalancedTree<E> {

    private TreeNode<E> root;

    public CheckBalancedTree(List<E> items) {
        create(items);
    }

    private void create (List<E> items) {
        if (items.size() == 0) { throw new IllegalArgumentException("The list is empty."); }

        root = new TreeNode<>(items.get(0));

        final Queue<TreeNode<E>> queue = new LinkedList<>();
        queue.add(root);

        final int half = items.size() / 2;

        for (int i = 0; i < half; i++) {
            if (items.get(i) != null) {
                final TreeNode<E> current = queue.poll();
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
        }
    }

    // create a binary tree.
    private static class TreeNode<E> {
        private TreeNode<E> left;
        private E item;
        private TreeNode<E> right;

        TreeNode(E item) {
            this.item = item;
        }
    }

    private static final int UNBALANCED = -1;

    public boolean checkIfBalanced() {
        if (root == null) {
            throw new IllegalStateException();
        }
        return checkBalanced(root) != UNBALANCED;
    }

    public int checkBalanced(TreeNode<E> node) {
        if (node == null) return 0;

        int tdLeft = checkBalanced(node.left);

        if (tdLeft == UNBALANCED) return UNBALANCED;

        int tdRight = checkBalanced(node.right);

        if (tdRight != UNBALANCED && Math.abs(tdLeft - tdRight) <= 1) {
            return Math.max(tdLeft, tdRight) + 1;
        }

        return UNBALANCED;
    }


//    public boolean checkIfBalanced() {
//        return getHeight(root) != UNBALANCED;
//    }

    public int getHeight(TreeNode<E> node) {
        if (node == null) {
            return 0;
        }

        int leftHeight = getHeight(node.left);
        if (leftHeight != UNBALANCED) {
            int rightHeight = getHeight(node.right);
            if (rightHeight != UNBALANCED) {
                if (Math.abs(leftHeight - rightHeight) < 2) {
                    return 1 + Math.max(leftHeight, rightHeight);
                }
            }
        }

        return UNBALANCED;
    }
}

public class CheckBalancedTreeTest {

    @Test
    public void testLeftSkewed() {
        /*     1
        *    /
        *   2
        *  /
        * 3
        */
        Integer[] leftSkewed =  {1, 2, null, 3, null, null, null};
        CheckBalancedTree<Integer> cbt = new CheckBalancedTree<>(Arrays.asList(leftSkewed));
        assertFalse(cbt.checkIfBalanced());
    }


    @Test
    public void testRightSkewed() {
        /* 1
        *   \
        *    2
        *     \
        *      3
        */
        Integer[] rightSkewed = {1, null, 2, null, null, null, 3 };
        CheckBalancedTree<Integer> cbt = new CheckBalancedTree<>(Arrays.asList(rightSkewed));
        assertFalse(cbt.checkIfBalanced());
    }

    @Test
    public void testSuccessCase() {
        /*
         *          1
         *         / \
         *        2   3
         *       /\
         *      4  5
         */
        Integer[] successCase = {1, 2, 3, 4, 5, null, null};
        CheckBalancedTree<Integer> cbt = new CheckBalancedTree<>(Arrays.asList(successCase));
        assertTrue(cbt.checkIfBalanced());
    }

    @Test
    public void testFailureCase() {
        /*
         *         1
         *        / \
         *       2   3
         *      / \
         *     4   5
         *        / \
         *       6   7
         */
        Integer[] failure = {1, 2, 3, 4, 5, null, null, null, null, 6, 7, null, null};
        CheckBalancedTree<Integer> cbt = new CheckBalancedTree<>(Arrays.asList(failure));
        assertFalse(cbt.checkIfBalanced());
    }
}

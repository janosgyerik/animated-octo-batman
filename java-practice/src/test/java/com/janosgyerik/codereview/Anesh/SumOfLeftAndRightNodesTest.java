package com.janosgyerik.codereview.Anesh;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

public class SumOfLeftAndRightNodesTest {
    private static class Node {
        private Node left;
        private Node right;
        private final int value;

        public Node(int value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node value=" + value + "";
        }
    }

    public static void insert(Node node, int value) {
        if (value < node.value) {
            if (node.left != null) {
                insert(node.left, value);
            } else {
                node.left = new Node(value);
            }
        } else if (value > node.value) {
            if (node.right != null) {
                insert(node.right, value);
            } else {
                node.right = new Node(value);
            }
        }
    }

    @Test
    public void main() {
        Node root = new Node(5);
        insert(root, 1);
        insert(root, 8);
        insert(root, -2);
        insert(root, 6);
        insert(root, 3);
        insert(root, 9);
        insert(root, -3);
        insert(root, -1);
        /*
         *           5
         *       1     8
         *   -2    3  6 9
         * -3 -1
         *
         * -3 -2 +1 +6 = 2
         * -1 +3 +8 +9 = 19
         */

        Result result = sumOfLeftandRightNodes(root);
        assertEquals(2, result.leftSum);
        assertEquals(19, result.rightSum);
    }

    private static class Result {
        private long leftSum;
        private long rightSum;

        private Result() {

        }
        private Result(long leftSum, long rightSum) {
            this.leftSum = leftSum;
            this.rightSum = rightSum;
        }
    }

    public static Result sumOfLeftandRightNodes(Node root) {
        Result result = new Result();
        sumOfLeftandRightNodes(root, result);
        return result;
    }

    public static void sumOfLeftandRightNodes(Node node, Result result) {
        if (node.left != null) {
            result.leftSum += node.left.value;
            sumOfLeftandRightNodes(node.left, result);
        }
        if (node.right != null) {
            result.rightSum += node.right.value;
            sumOfLeftandRightNodes(node.right, result);
        }
    }

    public static Result sumOfLeftandRightNodes2(Node root) {
        long leftSum = 0, rightSum = 0;

        Queue<Node> nodes = new LinkedList<Node>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node temp = nodes.poll();

            if (temp.left != null) {
                leftSum += temp.left.value;
                nodes.add(temp.left);
            }
            if (temp.right != null) {
                rightSum += temp.right.value;
                nodes.add(temp.right);
            }
        }
        return new Result(leftSum, rightSum);
    }

}

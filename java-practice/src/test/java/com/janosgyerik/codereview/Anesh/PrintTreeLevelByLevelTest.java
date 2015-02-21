package com.janosgyerik.codereview.Anesh;

import org.junit.Test;

import java.util.*;

public class PrintTreeLevelByLevelTest {
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
        insert(root, -4);
        /*
         *           5
         *       1     8
         *   -2    3  6 9
         * -3 -1
         *
         * -3 -2 +1 +6 = 2
         * -1 +3 +8 +9 = 19
         */

        System.out.println("*************\nPrinting the tree levelWise");
        printLevelWise(root);
    }

    private List<List<Node>> traverseLevels(Node root) {
        if (root == null) {
            return Collections.emptyList();
        }
        List<List<Node>> levels = new LinkedList<>();

        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            List<Node> level = new ArrayList<>(nodes.size());
            levels.add(level);

            for (Node node : new ArrayList<>(nodes)) {
                level.add(node);
                if (node.left != null) {
                    nodes.add(node.left);
                }
                if (node.right != null) {
                    nodes.add(node.right);
                }
                nodes.poll();
            }
        }
        return levels;
    }

    public void printLevelWise(Node root) {
        List<List<Node>> levels = traverseLevels(root);

        for (List<Node> level : levels) {
            for (Node node : level) {
                System.out.print(node.value + " ");
            }
            System.out.println();
        }
    }
}

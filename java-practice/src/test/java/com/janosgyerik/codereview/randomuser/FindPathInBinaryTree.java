package com.janosgyerik.codereview.randomuser;

import org.junit.Test;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Stack;

public class FindPathInBinaryTree {
    private static class Node {
        Node left;
        Node right;
        int value;

        public Node(int value) {
            this.value = value;
        }
    }

    public void findPathFromRoot(Node rootNode, int key) {
        Stack<Node> nodeStack = new Stack<>();
        nodeStack.push(rootNode);
        boolean present = true;

        Node topNodeRight;

        while (true) {
            Node topNode = nodeStack.peek();
            topNodeRight = topNode.right;

            if (topNode.left != null) {
                nodeStack.push(topNode.left);
                if (topNode.left.value == key) {
                    break;
                }
            } else if (topNodeRight != null) {
                nodeStack.push(topNodeRight);
                if (topNodeRight.value == key) {
                    break;
                }
            } else {
                Node lastPoppedNode;

                do {
                    lastPoppedNode = nodeStack.pop();
                    topNode = nodeStack.peek();
                } while (topNode.right == null);

                topNodeRight = topNode.right;

                nodeStack.push(topNodeRight);

                if (topNodeRight.value == key) {
                    break;
                }

                if (lastPoppedNode == topNodeRight) {
                    present = false;
                    break;
                }
            }
        }

        if (present) {
            for (Node tempNode : nodeStack) {
                System.out.println(tempNode.value);
            }
        } else {
            System.out.println("Element is not present in the tree");
        }
    }

    @Test
    public void test_3_x_7() {
        findPathFromRoot(new Node(3), 7);
    }

    @Test
    public void test_3_5_x_7() {
        Node node = new Node(3);
        node.left = new Node(5);
//        node.right = new Node(7);
        findPathFromRoot(node, 9);
    }

}

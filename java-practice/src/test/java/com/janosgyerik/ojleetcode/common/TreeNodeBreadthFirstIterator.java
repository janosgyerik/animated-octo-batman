package com.janosgyerik.ojleetcode.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class TreeNodeBreadthFirstIterator implements Iterator<Integer> {

    private final Queue<TreeNode> nodeQueue = new LinkedList<>();

    public TreeNodeBreadthFirstIterator(TreeNode node) {
        if (node != null) {
            nodeQueue.add(node);
        }
    }

    @Override
    public boolean hasNext() {
        return !nodeQueue.isEmpty();
    }

    @Override
    public Integer next() {
        TreeNode node = nodeQueue.poll();
        if (node == null) {
            return null;
        }
        if (node.left != null || node.right != null) {
            nodeQueue.add(node.left);
            nodeQueue.add(node.right);
        }
        return node.val;
    }
}

package com.janosgyerik.ojleetcode.easy;

import com.janosgyerik.ojleetcode.common.TreeNode;
import org.junit.Test;

import static org.junit.Assert.assertFalse;

public class BalancedBinaryTreeTest {
    private static final class SearchInfo {
        private final int height;
        private final boolean balanced;

        private SearchInfo(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }

    public boolean isBalanced(TreeNode root) {
        return isBalancedHelper(root).balanced;
    }

    private SearchInfo isBalancedHelper(TreeNode node) {
        if (node == null) {
            return new SearchInfo(0, true);
        }
        SearchInfo left = isBalancedHelper(node.left);
        if (left.balanced) {
            SearchInfo right = isBalancedHelper(node.right);
            if (right.balanced && Math.abs(left.height - right.height) < 2) {
                return new SearchInfo(1 + Math.max(left.height, right.height), true);
            }
        }
        return new SearchInfo(-1, false);
    }

    @Test
    public void testFromString() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.right = new TreeNode(3);
        assertFalse(isBalanced(root));
    }
}

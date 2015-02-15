package com.janosgyerik.ojleetcode.common;

import java.util.Stack;

public class TreeNodeUtils {

    private static final String NULL_MARKER = "#";

    private TreeNodeUtils() {
        // utility class, forbidden constructor
    }

    public static TreeNode deserialize(String string) {
        String[] values = string.substring(1, string.length() - 1).split(",");
        if (values[0].isEmpty()) {
            return null;
        }

        TreeNode root = parseValueAndCreateNode(values[0]);
        if (root != null) {
            Stack<TreeNode> parents = new Stack<>();
            parents.push(root);

            for (int i = 1; i < values.length; ++i) {
                TreeNode parent = parents.pop();

                TreeNode left = parseValueAndCreateNode(values[i]);
                if (i + 1 < values.length) {
                    TreeNode right = parseValueAndCreateNode(values[++i]);
                    if (right != null) {
                        parent.right = right;
                        parents.push(right);
                    }
                }
                if (left != null) {
                    parent.left = left;
                    parents.push(left);
                }
            }
        }

        return root;
    }

    private static TreeNode parseValueAndCreateNode(String value) {
        return value.equals(NULL_MARKER) ? null : new TreeNode(Integer.parseInt(value));
    }

}


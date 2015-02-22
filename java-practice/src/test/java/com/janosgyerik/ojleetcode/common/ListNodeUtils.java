package com.janosgyerik.ojleetcode.common;

public class ListNodeUtils {

    private ListNodeUtils() {
        // utility class, forbidden constructor
    }

    public static ListNode deserialize(String string) {
        String[] values = string.substring(1, string.length() - 1).split(",");
        if (values[0].isEmpty()) {
            return null;
        }

        ListNode head = parseValueAndCreateNode(values[0]);
        ListNode node = head;

        for (int i = 1; i < values.length; ++i) {
            node.next = parseValueAndCreateNode(values[i]);
            node = node.next;
        }

        return head;
    }

    private static ListNode parseValueAndCreateNode(String value) {
        return new ListNode(Integer.parseInt(value));
    }

    public static String serialize(ListNode node) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        if (node != null) {
            builder.append(node.val);
            node = node.next;
            while (node != null) {
                builder.append(",").append(node.val);
                node = node.next;
            }
        }
        return builder.append("]").toString();
    }
}

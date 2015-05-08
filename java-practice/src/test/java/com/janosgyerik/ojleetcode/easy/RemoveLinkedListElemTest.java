package com.janosgyerik.ojleetcode.easy;

import com.janosgyerik.ojleetcode.common.ListNode;

public class RemoveLinkedListElemTest {
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }

        ListNode node = head;
        while (node.next != null) {
            if (node.next.val == val) {
                node.next = node.next.next;
            } else {
                node = node.next;
            }
        }

        if (head.val == val) {
            return head.next;
        }
        return head;
    }
}

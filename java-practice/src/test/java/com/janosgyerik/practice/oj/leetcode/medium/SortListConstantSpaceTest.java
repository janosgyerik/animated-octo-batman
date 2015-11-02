package com.janosgyerik.practice.oj.leetcode.medium;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SortListConstantSpaceTest {
    public ListNode sortList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode prev = head;
        ListNode node = head.next;
        while (node != null) {
            if (node.val < prev.val) {
                ListNode nextBackup = node.next;
                head = insert(head, node);
                prev.next = node = nextBackup;
            } else {
                prev = node;
                node = node.next;
            }
        }
        return head;
    }

    private ListNode insert(ListNode head, ListNode target) {
        if (target.val < head.val) {
            target.next = head;
            return target;
        }

        ListNode node = head;
        while (node.next.val <= target.val) {
            node = node.next;
        }
        target.next = node.next;
        node.next = target;

        return head;
    }

    private String sortList(String serialized) {
        return ListNodeUtils.serialize(sortList(ListNodeUtils.deserialize(serialized)));
    }

    @Test
    public void test_empty() {
        assertEquals("[]", sortList("[]"));
    }

    @Test
    public void test_singleton() {
        assertEquals("[3]", sortList("[3]"));
    }

    @Test
    public void test_3_2_1() {
        assertEquals("[1,2,3]", sortList("[3,2,1]"));
    }

    @Test
    public void test_1_2_3() {
        assertEquals("[1,2,3]", sortList("[1,2,3]"));
    }

    @Test
    public void test_1_3_2() {
        assertEquals("[1,2,3]", sortList("[1,3,2]"));
    }

    @Test
    public void test_3_1_2() {
        assertEquals("[1,2,3]", sortList("[3,1,2]"));
    }

    @Test
    public void test_1_1_1() {
        assertEquals("[1,1,1]", sortList("[1,1,1]"));
    }

    @Test
    public void test_4_4_4_1() {
        assertEquals("[1,4,4,4]", sortList("[4,4,4,1]"));
    }

    @Test
    public void test_1_2() {
        assertEquals("[1,2]", sortList("[1,2]"));
    }

    @Test
    public void test_2_1() {
        assertEquals("[1,2]", sortList("[2,1]"));
    }

    @Test
    public void test_2_3_1_4() {
        assertEquals("[1,2,3,4]", sortList("[2,3,1,4]"));
    }

    @Test
    public void test_long() {
        assertEquals("[1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3]",
                sortList("{1,3,3,1,3,1,3,3,2,3,2,2,1,1,1,3,2,2,1,1,2,2,2,3,3,1,1,2,2,2,1,2,1,1,2,3,3,2,2,3,2}"));
    }
}

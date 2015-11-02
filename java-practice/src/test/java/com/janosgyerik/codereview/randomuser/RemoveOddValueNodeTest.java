package com.janosgyerik.codereview.randomuser;

import com.janosgyerik.practice.oj.leetcode.common.ListNode;
import com.janosgyerik.practice.oj.leetcode.common.ListNodeUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RemoveOddValueNodeTest {
    @Test
    public void test1() {
        ListNode head = ListNodeUtils.deserialize("[1,2,3,4,5,6,4,4,4,7,8]");
        assertEquals("[2,4,6,4,4,4,8]", ListNodeUtils.serialize(removeOdd(head)));
    }

    @Test
    public void test2() {
        ListNode head = ListNodeUtils.deserialize("[1,1,1,1,2,3,4,5,6,4,4]");
        assertEquals("[2,4,6,4,4]", ListNodeUtils.serialize(removeOdd(head)));
    }

    @Test
    public void test3() {
        ListNode head = ListNodeUtils.deserialize("[1,1,1,1,3,5,9]");
        assertEquals("[]", ListNodeUtils.serialize(removeOdd(head)));
    }

    @Test
    public void test4() {
        ListNode head = ListNodeUtils.deserialize("[2,4,6]");
        assertEquals("[2,4,6]", ListNodeUtils.serialize(removeOdd(head)));
    }

    @Test
    public void test5() {
        ListNode head = ListNodeUtils.deserialize("[2]");
        assertEquals("[2]", ListNodeUtils.serialize(removeOdd(head)));
    }

    @Test
    public void test6() {
        ListNode head = ListNodeUtils.deserialize("[2,3,3,5]");
        assertEquals("[2]", ListNodeUtils.serialize(removeOdd(head)));
    }

    @Test
    public void test7() {
        ListNode head = ListNodeUtils.deserialize("[2,4,6,8,9,1]");
        assertEquals("[2,4,6,8]", ListNodeUtils.serialize(removeOdd(head)));
    }

    private ListNode removeOdd(ListNode head) {
        while (head != null && head.val % 2 != 0) {
            head = head.next;
        }
        if (head == null) {
            return null;
        }
        ListNode lastEven = head;
        ListNode current = head.next;
        while (current != null) {
            if (current.val % 2 == 0) {
                lastEven.next = current;
                lastEven = current;
            }
            current = current.next;
        }
        lastEven.next = null;
        return head;
    }
}

package com.janosgyerik.ojleetcode.common;

import org.junit.Test;

import static org.junit.Assert.*;

public class ListNodeUtilsTest {

    private ListNode deserialize(String string) {
        return ListNodeUtils.deserialize(string);
    }

    @Test
    public void testDeserialize_empty() {
        assertNull(deserialize("{}"));
    }

    @Test
    public void testSerialize_1_2_3() {
        ListNode head = deserialize("{1,2,3}");
        assertEquals(1, head.val);
        assertEquals(2, head.next.val);
        assertEquals(3, head.next.next.val);
        assertNull(head.next.next.next);
    }

    @Test
    public void testSerialize_1() {
        ListNode head = deserialize("{1}");
        assertEquals(1, head.val);
        assertNull(head.next);
    }

    @Test
    public void testSerialize_1_2() {
        ListNode head = deserialize("{1,2}");
        assertEquals(1, head.val);
        assertEquals(2, head.next.val);
        assertNull(head.next.next);
    }
}
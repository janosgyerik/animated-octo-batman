package com.janosgyerik.codereview.foo;

/* DListNode.java */


import org.junit.Test;

import static org.junit.Assert.*;

/**
 * A DListNode is a node in a DList (doubly-linked list).
 */

class DListNode {

    /**
     * item references the item stored in the current node.
     * prev references the previous node in the DList.
     * next references the next node in the DList.
     * <p>
     * DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */

    public Object item;
    protected DListNode prev;
    protected DListNode next;

    /**
     * DListNode() constructor.
     *
     * @param i the item to store in the node.
     * @param p the node previous to this node.
     * @param n the node following this node.
     */
    DListNode(Object i, DListNode p, DListNode n) {
        item = i;
        prev = p;
        next = n;
    }

//    @Override
//    public boolean equals(Object obj) {
//        return item.equals(obj);
//    }

    @Override
    public String toString() {
        return item.toString();
    }
}


/**
 * A DList is a mutable doubly-linked list ADT.  Its implementation is
 * circularly-linked and employs a sentinel (dummy) node at the head
 * of the list.
 * <p>
 * DO NOT CHANGE ANY METHOD PROTOTYPES IN THIS FILE.
 */

class DList {

    /**
     * head references the sentinel node.
     * size is the number of items in the list.  (The sentinel node does not
     * store an item.)
     * <p>
     * DO NOT CHANGE THE FOLLOWING FIELD DECLARATIONS.
     */

    protected DListNode head;
    protected int size;
    private DListNode sentinel;

  /* DList invariants:
   *  1)  head != null.
   *  2)  For any DListNode x in a DList, x.next != null.
   *  3)  For any DListNode x in a DList, x.prev != null.
   *  4)  For any DListNode x in a DList, if x.next == y, then y.prev == x.
   *  5)  For any DListNode x in a DList, if x.prev == y, then y.next == x.
   *  6)  size is the number of DListNodes, NOT COUNTING the sentinel,
   *      that can be accessed from the sentinel (head) by a sequence of
   *      "next" references.
   */

    /**
     * newNode() calls the DListNode constructor.  Use this class to allocate
     * new DListNodes rather than calling the DListNode constructor directly.
     * That way, only this method needs to be overridden if a subclass of DList
     * wants to use a different kind of node.
     *
     * @param item the item to store in the node.
     * @param prev the node previous to this node.
     * @param next the node following this node.
     */
    protected DListNode newNode(Object item, DListNode prev, DListNode next) {
        return new DListNode(item, prev, next);
    }

    /**
     * DList() constructor for an empty DList.
     */
    public DList() {
        //  Your solution here.
        sentinel = newNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        head = sentinel;

    }

    /**
     * isEmpty() returns true if this DList is empty, false otherwise.
     *
     * @return true if this DList is empty, false otherwise.
     * Performance:  runs in O(1) time.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * length() returns the length of this DList.
     *
     * @return the length of this DList.
     * Performance:  runs in O(1) time.
     */
    public int length() {
        return size;
    }

    /**
     * insertFront() inserts an item at the front of this DList.
     *
     * @param item is the item to be inserted.
     *             Performance:  runs in O(1) time.
     */
    public void insertFront(Object item) {
        // Your solution here.
        sentinel.next = newNode(item, sentinel, sentinel.next);
        if (sentinel.prev == sentinel) {
            sentinel.prev = sentinel.next;
        }
        size++;

    }

    /**
     * insertBack() inserts an item at the back of this DList.
     *
     * @param item is the item to be inserted.
     *             Performance:  runs in O(1) time.
     */
    public void insertBack(Object item) {
        // Your solution here.
        DListNode node = newNode(item, sentinel.prev, sentinel);
        sentinel.prev = node;
        if (sentinel.next == sentinel) {
            sentinel.next = sentinel.prev;
        }
        size++;
    }

    /**
     * front() returns the node at the front of this DList.  If the DList is
     * empty, return null.
     * <p>
     * Do NOT return the sentinel under any circumstances!
     *
     * @return the node at the front of this DList.
     * Performance:  runs in O(1) time.
     */
    public DListNode front() {
        return size > 0 ? sentinel.next : null;
    }

    /**
     * back() returns the node at the back of this DList.  If the DList is
     * empty, return null.
     * <p>
     * Do NOT return the sentinel under any circumstances!
     *
     * @return the node at the back of this DList.
     * Performance:  runs in O(1) time.
     */
    public DListNode back() {
        // Your solution here.
        if (size > 0) {
            return sentinel.prev;
        } else {
            return null;
        }
    }

    /**
     * next() returns the node following "node" in this DList.  If "node" is
     * null, or "node" is the last node in this DList, return null.
     * <p>
     * Do NOT return the sentinel under any circumstances!
     *
     * @param node the node whose successor is sought.
     * @return the node following "node".
     * Performance:  runs in O(1) time.
     */
    public DListNode next(DListNode node) {
        if (node == null || node.next == sentinel) {
            return null;
        } else {
            return node.next;
        }
    }

    /**
     * prev() returns the node prior to "node" in this DList.  If "node" is
     * null, or "node" is the first node in this DList, return null.
     * <p>
     * Do NOT return the sentinel under any circumstances!
     *
     * @param node the node whose predecessor is sought.
     * @return the node prior to "node".
     * Performance:  runs in O(1) time.
     */
    public DListNode prev(DListNode node) {
        if (node == null || node.prev == sentinel) {
            return null;
        } else {
            return node.prev;
        }
    }

    /**
     * insertAfter() inserts an item in this DList immediately following "node".
     * If "node" is null, do nothing.
     *
     * @param item the item to be inserted.
     * @param node the node to insert the item after.
     *             Performance:  runs in O(1) time.
     */
    public void insertAfter(Object item, DListNode node) {
        // Your solution here.
        if (node != null)
            ;
        {
            node.next = newNode(item, node, node.next);
            size++;
        }
    }

    /**
     * insertBefore() inserts an item in this DList immediately before "node".
     * If "node" is null, do nothing.
     *
     * @param item the item to be inserted.
     * @param node the node to insert the item before.
     *             Performance:  runs in O(1) time.
     */
    public void insertBefore(Object item, DListNode node) {
        // Your solution here.
        if (node != null)
            ;
        {
            node.prev = newNode(item, node.prev, node);
            size++;
        }
    }

    /**
     * remove() removes "node" from this DList.  If "node" is null, do nothing.
     * Performance:  runs in O(1) time.
     */
    public void remove(DListNode node) {
        // Your solution here.
        if (node != null)
            ;
        {

            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
    }

    /**
     * toString() returns a String representation of this DList.
     * <p>
     * DO NOT CHANGE THIS METHOD.
     *
     * @return a String representation of this DList.
     * Performance:  runs in O(n) time, where n is the length of the list.
     */
    public String toString() {
        String result = "[  ";
        DListNode current = head.next;
        while (current != head) {
            result = result + current.item + "  ";
            current = current.next;
        }
        return result + "]";
    }
}

public class DLinkTest {
    @Test
    public void testEmpty() {
        DList list = new DList();
        assertEquals(null, list.back());
        assertEquals(null, list.front());
        assertNotEquals(null, list.head);
        assertTrue(list.isEmpty());
        assertEquals(0, list.length());
        assertEquals("[  ]", list.toString());
    }

    @Test
    public void testInsertFront() {
        DList list = new DList();
        String item = "hello";
        list.insertFront(item);
        assertEquals(item, list.back().item);
        assertEquals(item, list.front().item);
        assertNotEquals(item, list.head.item);
        assertFalse(list.isEmpty());
        assertEquals(1, list.length());
        assertEquals("[  " + item + "  ]", list.toString());
    }

    @Test
    public void testInsertBack() {
        DList list = new DList();
        String item = "hello";
        list.insertBack(item);
        assertEquals(item, list.back().item);
        assertEquals(item, list.front().item);
        assertNotEquals(item, list.head.item);
        assertFalse(list.isEmpty());
        assertEquals(1, list.length());
        assertEquals("[  " + item + "  ]", list.toString());
    }

    @Test
    public void testInsertBackFront() {
        DList list = new DList();
        String item1 = "hello1";
        String item2 = "hello2";
        list.insertBack(item1);
        list.insertFront(item2);
        assertEquals(item1, list.back().item);
        assertEquals(item2, list.front().item);
        assertEquals(item1, list.front().next.item);
//        assertEquals(item2, list.front().next.next.item);
        assertNotEquals(item1, list.head.item);
        assertFalse(list.isEmpty());
        assertEquals(2, list.length());
        //assertEquals("[  " + item1 + "  ]", list.toString());
    }

    @Test
    public void testInsertBackBack() {
        DList list = new DList();
        String item1 = "hello1";
        String item2 = "hello2";
        list.insertBack(item1);
        list.insertBack(item2);
        assertEquals(item2, list.back().item);
        assertEquals(item1, list.front().item);
        assertNotEquals(item1, list.head.item);
        assertFalse(list.isEmpty());
        assertEquals(2, list.length());
        //assertEquals("[  " + item1 + "  ]", list.toString());
    }

    @Test
    public void test3Items() {
        DList list = new DList();
        String item1 = "hello1";
        String item2 = "hello2";
        String item3 = "hello3";
        list.insertBack(item1);
        list.insertBack(item2);
        list.insertBack(item3);
        list.insertBack(item3);
        list.insertBack(item3);
        DListNode node = list.head;
        DListNode sentinel = list.head.next;
        for (int i = 0; i < list.length(); ++i) {
            assertNotNull(node.next);
            assertNotNull(node.prev);
            assertNull(node.next.prev.item);
            assertNull(node.prev.next.item);
            assertEquals(sentinel, node.next);
            //assertEquals(sentinel, node.prev);
            //assertEquals(node.next.item, node.next.prev.item);
            //assertEquals(node.prev.item, node.prev.next.item);
        }
        assertFalse(list.isEmpty());
        //assertEquals(3, list.length());
    }

    @Test
    public void test3Items2() {
        DList list = new DList();
        String item1 = "hello1";
        String item2 = "hello2";
        String item3 = "hello3";
        list.insertFront(item1);
        list.insertFront(item2);
        list.insertFront(item3);
        DListNode node = list.head;
        DListNode sentinel = list.head.prev;
        for (int i = 0; i < list.length(); ++i) {
            assertNotNull(node.next);
            assertNotNull(node.prev);
            assertNull(node.next.prev.item);
            assertNull(node.prev.next.item);
            assertEquals(sentinel, node.prev);
            //assertEquals(sentinel, node.prev);
            //assertEquals(node.next.item, node.next.prev.item);
            //assertEquals(node.prev.item, node.prev.next.item);
        }
        assertFalse(list.isEmpty());
        assertEquals(3, list.length());

    }
}

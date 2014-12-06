package com.janosgyerik.codereview.overexchange;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

class SList {
    private SListNode head;
    private int size;

    /**
     * SList() constructs an empty list.
     */

    public SList() {
        this.head = null;
        this.size = 0;
    }

    /**
     * isEmpty() indicates whether the list is empty.
     *
     * @return true is the list is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * length() returns the length of this list.
     *
     * @return the length of the list.
     */
    public int length() {
        return size;
    }

    /**
     * insertFront() inserts item "obj" at the beginning of this list.
     *
     * @param obj the item to be inserted.
     */

    public void insertFront(Object obj) {
        this.head = new SListNode(obj, this.head);
        this.size++;
    }

    /**
     * insertEnd() inserts item "obj" at the end of this list.
     *
     * @param obj the item to be inserted.
     */
    public void insertEnd(Object obj) {
        if (head == null) {
            this.head = new SListNode(obj);
        } else {
            SListNode node = this.head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new SListNode(obj);
        }
        size++;
    }

    /**
     * nth() returns the item at the specified position. If position < 1 or
     * position > this.length(), null is returned. Otherwise, item at
     * position "position" is returned. The list does not change.
     *
     * @param position the desired position, from 1 to length(), in the list.
     * @return the item at the given position in the list.
     */

    public Object nth(int position) {
        if ((position < 1) || (position > this.length()) || this.head == null) {
            return null;
        } else {
            SListNode currentNode = this.head;
            while (position > 1) {
                currentNode = currentNode.next;
                if (currentNode == null)
                    return null;
                position--;
            }
            return currentNode.item;
        }
    }


    /**
     * toString() converts the list to String.
     *
     * @return a string representation of the list.
     */
    @Override
    public String toString() {
        Object obj;
        String result = "[ ";

        SListNode currentNode = head;

        while (currentNode != null) {
            obj = currentNode.item;
            result = result + obj.toString() + " ";
            currentNode = currentNode.next;
        }
        result = result + " ]";
        return result;
    }

    private static class SListNode {
        public SListNode next;
        public final Object item;

        public SListNode(Object item, SListNode next) {
            this.item = item;
            this.next = next;
        }

        public SListNode(Object item) {
            this(item, null);
        }
    }

}

public class SListTest {
    @Test
    public void testEmptyList() {
        SList lst1 = new SList();
        assertEquals("toString on newly constructed list failed", "[  ]", lst1.toString());
        assertTrue("isEmpty() on newly constructed list failed", lst1.isEmpty());
        assertEquals("length on newly constructed list failed", 0, lst1.length());
        lst1.insertFront(3);
        assertEquals("InsertFront on empty list failed", "[ 3  ]", lst1.toString());
        SList lst2 = new SList();
        lst2.insertEnd(5);
        assertEquals("insertEnd on empty list failed", "[ 5  ]", lst2.toString());
    }


    /**
     * testAfterInsertFront() tests toString(), isEmpty(), length(),
     * insertFront(), and insertEnd() after insertFront(). Prints summary
     * information of the tests and halts the program if errors are detected.
     */
    private static void testAfterInsertFront() {
        SList lst1 = new SList();
        lst1.insertFront(new Integer(3));
        lst1.insertFront(new Integer(2));
        lst1.insertFront(new Integer(1));
        System.out.println();
        System.out.println("Here is a list after insertFront 3, 2, 1: " + lst1.toString());
        //        TestHelper.verify(lst1.toString().equals("[  1  2  3  ]"), "InsertFronts on non-empty list failed");
        //        System.out.println("isEmpty() should be false. It is: " +
        //                lst1.isEmpty());
        //        TestHelper.verify(lst1.isEmpty() == false,
        //                "isEmpty() after insertFront failed");
        //        System.out.println("length() should be 3. It is: " +
        //                lst1.length());
        //        TestHelper.verify(lst1.length() == 3,
        //                "length() after insertFront failed");
        //        lst1.insertEnd(new Integer(4));
        //        System.out.println("Here is the same list after insertEnd(4): "
        //                + lst1.toString());
        //        TestHelper.verify(lst1.toString().equals("[  1  2  3  4  ]"),
        //                "insertEnd on non-empty list failed");
    }

    /**
     * testAfterInsertEnd() tests toString(), isEmpty(), length(),
     * insertFront(), and insertEnd() after insertEnd().  Prints summary
     * information of the tests and halts the program if errors are detected.
     */

    private static void testAfterInsertEnd() {
        SList lst1 = new SList();
        lst1.insertEnd(new Integer(6));
        lst1.insertEnd(new Integer(7));
        System.out.println();
        System.out.println("Here is a list after insertEnd 6, 7: " + lst1.toString());
        System.out.println("isEmpty() should be false. It is: " + lst1.isEmpty());
        //        TestHelper.verify(lst1.isEmpty() == false,
        //                "isEmpty() after insertEnd failed");
        //        System.out.println("length() should be 2. It is: " +
        //                lst1.length());
        //        TestHelper.verify(lst1.length() == 2,
        //                "length() after insertEndfailed");
        //        lst1.insertFront(new Integer(5));
        //        System.out.println("Here is the same list after insertFront(5): "
        //                + lst1.toString());
        //        TestHelper.verify(lst1.toString().equals("[  5  6  7  ]"),
        //                "insertFront after insertEnd failed");
    }

}

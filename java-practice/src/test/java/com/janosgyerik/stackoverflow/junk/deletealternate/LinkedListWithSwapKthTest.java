package com.janosgyerik.stackoverflow.junk.deletealternate;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class SwapKth<T> extends LinkedList<T> {

    private Node<T> first;
    private int size;

    public SwapKth(List<T> items) {
        addAll(items);
    }

    void addAll(List<T> items) {
        this.size = items.size();
        Node<T> prev = null;
        for (T item : items) {
            Node<T> node = new Node<T>(item);
            if (prev == null) {
                first = prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
        }
    }


    private static class Node<T> {
        private Node<T> next;
        private T item;

        Node(T item) {
            this.item = item;
        }

        @Override
        public String toString() {
            return item.toString();
        }
    }

    private Node<T> findNth(Node<T> from, int n) {
        Node<T> node = from;
        for (int i = 0; i < n; ++i) {
            node = node.next;
        }
        return node;
    }

    public void swap (int n)  {
        if (n == 0) {
            throw new IllegalArgumentException("The value of n should be greater than 0.");
        }
        if (n > size) {
            throw new IllegalArgumentException("The value of n: " + n + " is greater than: " + size);
        }
        if (n > size / 2) {
            n = size - n + 1;
        }

//        System.out.println(this.toList());
//        Node<T> beforeNth = findNth(first, n - 2);
//        Node<T> beforeNthFromEnd = findNth(beforeNth, size - 2 * n + 1);
//        System.out.println(String.format("n=%d b1=%s b2=%s", n, beforeNth, beforeNthFromEnd));
//        beforeNth = null;//findNth(first, n - 2);
//        beforeNthFromEnd = findNth(first, size - 2);
//        System.out.println(String.format("n=%d b2=%s", 1, beforeNthFromEnd));
//
//        if (1 == 1) {
////            System.exit(1);
//        }

        // code to reach the nth node from front.
        Node<T> x = first;
        Node<T> prevX = null;
        for (int i = 0; i < n - 1; x = x.next, ++i) {
            prevX = x;
        }

        // code to reach the nth node from the end.
        Node<T> y = x;
        Node<T> prevY = null;
        for (int i = 0; i < size - 2 * n + 1; ++i, y = y.next) {
            prevY = y;
        }

        // if 'x' and 'y' happen to be the same node.
        // eg: 1->2->3->4->5, swap 3rd from start with 3rd from the end.
        if (x == y) {
            return;
        }

        if (x.next == y) {
            adjacentSwap(prevX, x, y);
        } else {
            distantSwap(prevX, x, prevY, y);
        }

    }


    /**
     * Swap 3rd from start with 3rd form the end, in a linkedlist like.
     * 1->2->3->4->5->6
     * Here node 3, and 4 are adjacent
     *
     * Edge case:
     * 1->2          (swap 1 with 2)
     */
    public void adjacentSwap(Node<T> firstPrev, Node<T> first, Node<T> second) {
        first.next = second.next;
        second.next = first;
        if (firstPrev != null) {
            firstPrev.next = second;
        } else {
            this.first = second;
        }
    }



    /**
     * Swap 2nd not from the front and 2nd node from the end.
     * 1->2->3->4->5->6
     * Here node 2nd node and 5th node are not adjacent
     *
     * Edge case:
     * 1->2->3->4->5->6 (swap 1 with 6)
     *
     */
    public void distantSwap(Node<T> firstPrev, Node<T> first, Node<T> secondPrev, Node<T> second) {
        if (firstPrev != null) {
            firstPrev.next = first.next;
            secondPrev.next = second.next;

            second.next = firstPrev.next;
            first.next = secondPrev.next;

            firstPrev.next = second;
            secondPrev.next = first;
        } else {
            secondPrev.next = second.next;

            second.next = first.next;
            first.next = secondPrev.next;

            this.first = second;
            secondPrev.next = first;
        }
    }

    public List<T> toList() {
        final List<T> list = new ArrayList<>();
        if (first == null) return list;

        for (Node<T> x = first; x != null; x = x.next) {
            list.add(x.item);
        }

        return list;
    }
}

public class LinkedListWithSwapKthTest {

    @Test
    public void testEvenLength() {
        SwapKth<Integer> sk1 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        sk1.swap(1);
        assertEquals(Arrays.asList(6, 2, 3, 4, 5, 1), sk1.toList());
        assertNotEquals(Arrays.asList(2, 2, 3, 4, 5, 1), sk1.toList());

        SwapKth<Integer> sk2 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        sk2.swap(2);
        assertEquals(Arrays.asList(1, 5, 3, 4, 2, 6), sk2.toList());
        assertNotEquals(Arrays.asList(2, 2, 3, 4, 5, 1), sk2.toList());

        SwapKth<Integer> sk3 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        sk3.swap(3);
        assertEquals(Arrays.asList(1, 2, 4, 3, 5, 6), sk3.toList());

        SwapKth<Integer> sk4 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        sk4.swap(4);
        assertEquals(Arrays.asList(1, 2, 4, 3, 5, 6), sk4.toList());

        SwapKth<Integer> sk5 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        sk5.swap(5);
        assertEquals(Arrays.asList(1, 5, 3, 4, 2, 6), sk2.toList());

        SwapKth<Integer> sk6 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5, 6));
        sk6.swap(6);
        assertEquals(Arrays.asList(6, 2, 3, 4, 5, 1), sk6.toList());
    }

    @Test
    public void testOddLength() {
        SwapKth<Integer> sk7 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        sk7.swap(1);
        assertEquals(Arrays.asList(5, 2, 3, 4, 1), sk7.toList());

        SwapKth<Integer> sk8 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        sk8.swap(2);
        assertEquals(Arrays.asList(1, 4, 3, 2, 5), sk8.toList());

        SwapKth<Integer> sk9 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        sk9.swap(3);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), sk9.toList());

        SwapKth<Integer> sk10 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        sk10.swap(4);
        assertEquals(Arrays.asList(1, 4, 3, 2, 5), sk10.toList());

        SwapKth<Integer> sk11 = new SwapKth<Integer>(Arrays.asList(1, 2, 3, 4, 5));
        sk11.swap(5);
        assertEquals(Arrays.asList(5, 2, 3, 4, 1), sk11.toList());
    }

    @Test
    public void testTwoElement() {
        SwapKth<Integer> sk12 = new SwapKth<Integer>(Arrays.asList(1, 2));
        sk12.swap(1);
        assertEquals(Arrays.asList(2, 1), sk12.toList());

        SwapKth<Integer> sk13 = new SwapKth<Integer>(Arrays.asList(1, 2));
        sk13.swap(2);
        assertEquals(Arrays.asList(2, 1), sk13.toList());
    }

    @Test
    public void testSingleElement() {
        SwapKth<Integer> sk14 = new SwapKth<Integer>(Arrays.asList(1));
        sk14.swap(1);
        assertEquals(Arrays.asList(1), sk14.toList());
    }

}

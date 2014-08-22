package com.janosgyerik.stackoverflow.javadeveloper.deletealternate;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class LinkedList<T> {

    Node<T> first;
    Node<T> last;

    public LinkedList() {

    }

    public LinkedList(List<T> items) {
        for (T item : items) {
            create(item);
        }
    }

    private void create(T item) {
        Node<T> node = new Node<>(item);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public void deleteAlternate() {
        for (Node<T> node = first; node != null && node.next != null; ) {
            node = node.next = node.next.next;
        }
    }

    public void eliminateDuplicates() {
        final Set<T> set = new HashSet<T>();
        Node<T> prev = null;
        for (Node<T> x = first; x != null; x = x.next) {
            if (set.contains(x.item)) {
                prev.next = x.next;
            } else {
                set.add(x.item);
                prev = x;
            }
        }
        last = prev;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Node<T> node = first; node != null; node = node.next) {
            builder.append(node.item).append(" -> ");
        }
        return builder.toString();
    }

    @Override
    public int hashCode() {
        int hashCode = 1;
        for (Node<T> node = first; node != null; node = node.next) {
            hashCode = 31 * hashCode + node.hashCode();
        }
        return hashCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof LinkedList) {
            LinkedList<T> other = (LinkedList<T>) obj;
            Node<T> currentListNode = first;
            Node<T> otherListNode = other.first;

            while (currentListNode != null && otherListNode != null) {
                if (!currentListNode.item.equals(otherListNode.item)) {
                    return false;
                }
                currentListNode = currentListNode.next;
                otherListNode = otherListNode.next;
            }
            return currentListNode == null && otherListNode == null;
        }
        return false;
    }

    static class Node<T> {
        Node<T> next;
        T item;

        Node(T item) {
            this.item = item;
        }
    }
}


public class LinkedListTest {

    private void assertEqualsAfterDeleteAlternate(List<Integer> expected, List<Integer> orig) {
        LinkedList<Integer> linkedList = new LinkedList<>(orig);
        linkedList.deleteAlternate();
        assertEquals(new LinkedList<>(expected), linkedList);
    }

    private void assertNotEqualsAfterDeleteAlternate(List<Integer> expected, List<Integer> orig) {
        LinkedList<Integer> linkedList = new LinkedList<>(orig);
        linkedList.deleteAlternate();
        assertNotEquals(new LinkedList<>(expected), linkedList);
    }

    @Test
    public void testWithSingleItem() {
        assertEqualsAfterDeleteAlternate(Arrays.asList(1), Arrays.asList(1));
        assertNotEqualsAfterDeleteAlternate(Arrays.asList(1, 2), Arrays.asList(1));
    }

    @Test
    public void testWith2Items() {
        assertEqualsAfterDeleteAlternate(Arrays.asList(1), Arrays.asList(1, 2));
        assertNotEqualsAfterDeleteAlternate(Arrays.asList(1, 2), Arrays.asList(1, 2));
        assertNotEqualsAfterDeleteAlternate(Arrays.asList(1, 2, 3), Arrays.asList(1, 2));
    }

    @Test
    public void testWith8Items() {
        assertEqualsAfterDeleteAlternate(Arrays.asList(1, 3, 5, 7), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    }


    @Test
    public void test4() {
        LinkedList<Integer> dAlternate4 = new LinkedList<>(Arrays.asList(1, 2, 3, 4));
        dAlternate4.deleteAlternate();
        assertEquals(new LinkedList<>(Arrays.asList(1, 3)), dAlternate4);
    }


    @Test
    public void test5() {
        LinkedList<Integer> dAlternate5 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        dAlternate5.deleteAlternate();
        assertEquals(new LinkedList<>(Arrays.asList(1, 3, 5)), dAlternate5);
        assertNotEquals(new LinkedList<>(Arrays.asList(1, 4, 5)), dAlternate5);
        assertNotEquals(new LinkedList<>(Arrays.asList(1, 4)), dAlternate5);
    }

}
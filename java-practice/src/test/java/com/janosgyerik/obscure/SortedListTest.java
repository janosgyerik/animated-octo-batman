package com.janosgyerik.examples.lists;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

class SortedLinkedList<E> {

    private final Comparator<? super E> _comparator;

    private Node<E> first = null;
    private Node<E> last = null;

    private static class Node<E> {
        private final E value;
        private Node<E> next;
        private Node<E> prev;

        private Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    SortedLinkedList(Comparator<? super E> comparator) {
        _comparator = comparator;
    }

    public void add(E item) {
        Node<E> node = first;
        // seek until null, or until node > item
        while (node != null && _comparator.compare(node.value, item) < 1) {
            node = node.next;
        }
        if (node == null) {
            addLast(item);
        } else {
            addBefore(node, item);
        }
    }

    private void addBefore(Node<E> node, E item) {
        Node<E> newNode = new Node<>(item);
        Node<E> prev = node.prev;
        newNode.prev = node.prev;
        newNode.next = node;
        if (prev != null) {
            prev.next = newNode;
        } else {
            first = newNode;
        }
        node.prev = newNode;
    }

    private void addFirst(E item) {
        if (first == null) {
            first = last = new Node<>(item);
        } else {
            addBefore(first, item);
        }
    }

    private void addAfter(Node<E> node, E item) {
        Node<E> newNode = new Node<>(item);
        Node<E> next = node.next;
        newNode.prev = node;
        newNode.next = node.next;
        if (next != null) {
            next.prev = newNode;
        } else {
            last = newNode;
        }
        node.next = newNode;
    }

    private void addLast(E item) {
        if (last == null) {
            first = last = new Node<>(item);
        } else {
            addAfter(last, item);
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        Iterator<E> iter = iterator();
        if (iter.hasNext()) {
            builder.append(iter.next());
        }
        while (iter.hasNext()) {
            builder.append(", ").append(iter.next());
        }
        builder.append("]");
        return builder.toString();
    }

    private Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> node = first;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}

public class SortedListTest {
    @Test
    public void testSorted() {
        SortedLinkedList<Integer> list = new SortedLinkedList<>(Integer::compare);
        assertEquals(Arrays.asList().toString(), list.toString());
        list.add(3);
        assertEquals(Arrays.asList(3).toString(), list.toString());
        list.add(2);
        assertEquals(Arrays.asList(2, 3).toString(), list.toString());
        list.add(1);
        assertEquals(Arrays.asList(1, 2, 3).toString(), list.toString());
        list.add(5);
        assertEquals(Arrays.asList(1, 2, 3, 5).toString(), list.toString());
        list.add(4);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5).toString(), list.toString());
        list.add(-4);
        assertEquals(Arrays.asList(-4, 1, 2, 3, 4, 5).toString(), list.toString());
        list.add(-2);
        assertEquals(Arrays.asList(-4, -2, 1, 2, 3, 4, 5).toString(), list.toString());
        list.add(12);
        assertEquals(Arrays.asList(-4, -2, 1, 2, 3, 4, 5, 12).toString(), list.toString());
    }
}

package com.janosgyerik.stackoverflow.attilaherbert;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

class MyLinkedList<T> {
    private static class Node<T> {
        public Node<T> nextNode = null;
        public Node<T> previousNode = null;
        public int index;
        public T data;

        public Node(T data) {
            this.data = data;
        }
    }

    private Node<T> firstNode;
    private Node<T> lastNode;
    private int nodeCount;

    public MyLinkedList() {
        this.firstNode = null;
        this.lastNode = null;
        this.nodeCount = 0;
    }

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return this.nodeCount == 0;
    }

    public boolean add(T data) {
        if (data == null) {
            return false;
        }

        Node<T> currentNode = new Node<T>(data);

        if (this.isEmpty()) {
            currentNode.previousNode = null;
            currentNode.index = 0;
            this.firstNode = currentNode;
        } else {
            currentNode.previousNode = lastNode;
            lastNode.nextNode = currentNode;
            currentNode.index = lastNode.index + 1;
        }
        this.lastNode = currentNode;
        this.nodeCount++;
        return true;
    }

    public T get(int index) {
        //error handling
        if (this.isEmpty() || index < 0 || index > this.nodeCount) {
            return null;
        }
        //
        Node<T> currentNode;
        int count = lastNode.index - index;
        currentNode = lastNode;

        while (count > 0) {
            currentNode = currentNode.previousNode;
            count--;
        }

        return currentNode.data;
    }

    public Node<T> getNode(int index) {
        //error handling
        if (this.isEmpty() || index < 0 || index > this.nodeCount) {
            return null;
        }
        //
        int count = lastNode.index - index;
        Node<T> currentNode = lastNode;

        while (count > 0) {
            currentNode = currentNode.previousNode;
            count--;
        }

        return currentNode;
    }

    public boolean insert(T data, int index) {
        Node<T> currentNode;

        if (this.getNode(index) != null) {
            Node<T> newNode = new Node<T>(data);
            currentNode = this.getNode(index);
            newNode.index = index;

            if (currentNode.previousNode != null) {
                currentNode.previousNode.nextNode = newNode;
                newNode.previousNode = currentNode.previousNode;
                currentNode.previousNode = newNode;
                newNode.nextNode = currentNode;
            } else {
                currentNode.previousNode = newNode;
                newNode.nextNode = currentNode;
            }
            currentNode = newNode;

            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
                currentNode.index++;
            }

            this.nodeCount++;
            return true;
        } else {
            return false; // error handling
        }
    }

    public boolean remove(int index) {
        Node<T> currentNode;

        if (this.getNode(index) != null) {
            currentNode = this.getNode(index);

            if (currentNode.previousNode != null) {
                currentNode.nextNode.previousNode = currentNode.previousNode;
                currentNode.previousNode.nextNode = currentNode.nextNode;
            } else if (currentNode.nextNode != null) {
                currentNode.nextNode.previousNode = null;
            } else if (this.isEmpty()) {
                this.lastNode = null;
            }

            while (currentNode.nextNode != null) {
                currentNode = currentNode.nextNode;
                currentNode.index--;
            }

            this.nodeCount--;
            return true;
        } else {
            return false;
        }
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> nextNode = firstNode;

            @Override
            public boolean hasNext() {
                return nextNode != null;
            }

            @Override
            public T next() {
                if (nextNode == null) {
                    throw new NoSuchElementException();
                }
                T data = nextNode.data;
                nextNode = nextNode.nextNode;
                return data;
            }
        };
    }
}

public class LinkedListTest {
    @Test
    public void testSimple() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(3);
        list.add(12);
        list.add(1);

        Iterator<Integer> iter = list.iterator();
        assertTrue(iter.hasNext());
        assertEquals(new Integer(3), iter.next());
        assertEquals(new Integer(12), iter.next());
        assertEquals(new Integer(1), iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testEmpty() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        assertFalse(list.iterator().hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void throwIfNextOnEmpty() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.iterator().next();
    }

    @Test(expected = NoSuchElementException.class)
    public void throwIfIterateBeyond() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(1);
        list.add(2);
        Iterator<Integer> iter = list.iterator();
        iter.next();
        iter.next();
        iter.next();
    }

    @Test
    public void testStandardIteration() {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        Integer[] items = { 12, 3, 4 };
        for (int i : items) {
            list.add(i);
        }
        Iterator<Integer> iter = list.iterator();
        for (Integer item : items) {
            assertTrue(iter.hasNext());
            assertEquals(item, iter.next());
        }
        assertFalse(iter.hasNext());
    }

    @Test(expected = NoSuchElementException.class)
    public void testIteratingBeyondTheEnd() {
        Iterator<Integer> iter = Arrays.asList(1, 2).iterator();
        iter.next();
        iter.next();
        iter.next();
    }
}

package com.janosgyerik.stackoverflow.arunprakash;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


class LinkList<T> {

    private static class Node<T> {

        private final T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

    private Node<T> first = null;

    public void addFirst(T data) {
        Node<T> newFirst = new Node<>(data);
        newFirst.next = first;
        first = newFirst;
    }

    public T removeFirst() {
        Node<T> oldFirst = first;
        first = first.next;
        return oldFirst.data;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        Node current = first;
        if (current != null) {
            builder.append(current);
            current = current.next;
        }
        while (current != null) {
            builder.append(" ").append(current);
            current = current.next;
        }
        return builder.toString();
    }

    public boolean isEmpty() {
        return first == null;
    }
}

class LinkListStack<T> {

    private final LinkList<T> linkedList = new LinkList<>();

    public void push(T data) {
        linkedList.addFirst(data);
    }

    public T pop() {
        return linkedList.removeFirst();
    }

    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}

public class LinkListTest {
    @Test
    public void testPushAndPop() {
        LinkListStack<Integer> st = new LinkListStack<>();
        st.push(50);
        st.push(70);
        st.push(190);
        assertEquals("190 70 50", st.toString());
        assertEquals(190, (int) st.pop());
        assertEquals("70 50", st.toString());
    }

    @Test
    public void testPopUntilEmpty() {
        List<Integer> values = Arrays.asList(50, 70, 190, 20);
        LinkListStack<Integer> st = new LinkListStack<>();
        assertTrue(st.isEmpty());
        for (Integer value : values) {
            st.push(value);
        }
        assertFalse(st.isEmpty());
        for (int i = values.size(); i > 0; --i) {
            assertEquals(values.get(i - 1), st.pop());
        }
        assertTrue(st.isEmpty());
    }
}

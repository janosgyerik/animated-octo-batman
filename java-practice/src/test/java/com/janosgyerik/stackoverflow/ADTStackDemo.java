package com.janosgyerik.stackoverflow;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ADTStackDemo {
    @Test
    public void test_push() {
        ADTStack<Integer> stack = new Stack<>();
        assertEquals(0, stack.size());
        assertEquals(true, stack.empty());
        stack.push(3);
        assertEquals(1, stack.size());
        assertEquals(false, stack.empty());
    }

    @Test
    public void test_push_pop() {
        ADTStack<Integer> stack = new Stack<>();
        assertEquals(0, stack.size());
        assertEquals(true, stack.empty());

        stack.push(3);
        assertEquals(1, stack.size());
        assertEquals(false, stack.empty());

        stack.pop();
        assertEquals(0, stack.size());
        assertEquals(true, stack.empty());
    }
}

interface ADTStack<T> {

    void push(T element);

    void pop();

    T top();

    boolean empty();

    int size();
}

class Stack<T> implements ADTStack<T> {
    private ListElement<T> firstElement;
    int size = 0;

    public Stack() {
        firstElement = null;
    }

    @Override
    public void push(T element) {
        if (!empty()) {
            firstElement.nextElement = firstElement;
        }
        firstElement = new ListElement<>(element);
        size++;
    }

    @Override
    public void pop() {
        if (empty()) {
            throw new RuntimeException("stack is empty");
        }
        firstElement = firstElement.nextElement;
        size--;
    }

    @Override
    public T top() {
        if (empty()) {
            return null;
        } else {
            return firstElement.element;
        }
    }

    @Override
    public boolean empty() {
        return (firstElement == null);
    }

    @Override
    public int size() {
        return size;
    }

    public static class ListElement<T> {
        private T element = null;
        private ListElement<T> nextElement = null;

        public ListElement(T element) {
            this.element = element;
        }

        public T getElement() {
            return element;
        }

        public ListElement<T> getNextElement() {
            return nextElement;

        }

        public void setNextElement(ListElement<T> element) {
            this.nextElement = nextElement;
        }
    }


}

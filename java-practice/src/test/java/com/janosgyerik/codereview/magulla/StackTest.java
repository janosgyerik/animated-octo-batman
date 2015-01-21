package com.janosgyerik.codereview.magulla;

import org.junit.Test;

import java.util.Arrays;
import java.util.EmptyStackException;

interface Stack<T> {
    void push(T object);

    T pop();

    int size();
}

class StackImpl implements Stack<Object> {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    public StackImpl() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    public Object pop() {
        if (size == 0)
            throw new EmptyStackException();
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Ensure space for at least one more element, roughly
     * doubling the capacity each time the array needs to grow.
     */
    private void ensureCapacity() {
        if (elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }
}

public class StackTest {
    long getAvailableMemory() {
        Runtime runtime = Runtime.getRuntime();
        runtime.gc();
        return runtime.maxMemory() - runtime.totalMemory() + runtime.freeMemory();
    }

    @Test
    public void testMemoryUse() {
        System.out.println(getAvailableMemory());

        Stack stack = new StackImpl();
        int size = 1000000;
        for (int i = 0; i < size; ++i) {
            Object obj = new Integer(i);
            stack.push(obj);
        }
        System.out.println(getAvailableMemory());
        while (stack.size() > 0) {
            stack.pop();
        }

        System.out.println(getAvailableMemory());

        stack.push(3);
        //        Stack<Integer> stack = new Stack<>();
        //        stack.push(4);
        //        stack.pop();
        //        stack.pop();
    }
}

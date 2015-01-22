package com.janosgyerik.codereview.magulla;

import org.junit.Test;

import java.lang.ref.WeakReference;
import java.util.Objects;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

interface Stack<T> {
    void push(T object);

    T pop();

    int size();
}

class StackImpl implements Stack<Object> {
    private int size;
    private Object[] data = new Object[10];

    @Override
    public void push(Object object) {
        data[size] = object;
        size++;
    }

    @Override
    public Object pop() {
        --size;
        Object result = data[size];
        data[size] = null;
        return result;
    }

    @Override
    public int size() {
        return size;
    }
}

public class StackTest {
    @Test
    public void testPopCleansUpReference() {
        Stack<Object> stack = new StackImpl();
        Object value = new Object();
        stack.push(value);

        WeakReference<Object> reference = new WeakReference<>(value);
        value = null;

        stack.pop();
        Runtime.getRuntime().gc();
        assertNull(reference.get());
    }
}

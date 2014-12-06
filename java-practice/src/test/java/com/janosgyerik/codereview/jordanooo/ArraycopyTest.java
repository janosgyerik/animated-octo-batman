package com.janosgyerik.codereview.jordanooo;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.Assert.assertEquals;

class CustomCollection<T> {

    private final Class<T> clazz;
    private T[] arr;
    private int size = 0;

    CustomCollection(Class<T> clazz) {
        this.clazz = clazz;
        arr = newArray(0);
    }

    public boolean addAll(int index, Collection<? extends T> items) {
        if (items.isEmpty()) {
            return false;
        }
        if (size + items.size() > arr.length) {
            resize(2 * (size + items.size()));
        }
        System.arraycopy(arr, index, arr, index + items.size(), size - index);
        for (T item : items) {
            arr[index++] = item;
        }
        size += items.size();
        return true;
    }

    public void add(int index, T item) {
        if (size + 1 > arr.length) {
            resize();
        }
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = item;
        size++;
    }

    private void resize() {
        resize(Math.max(2 * size, 1));
    }

    private void resize(int targetSize) {
        T[] copy = newArray(targetSize);
        System.arraycopy(arr, 0, copy, 0, size);
        arr = copy;
    }

    T[] newArray(int targetSize) {
        @SuppressWarnings("unchecked")
        final T[] a = (T[]) Array.newInstance(clazz, targetSize);
        return a;
    }

    Collection<T> getItems() {
        List<T> items = new ArrayList<>(size);
        items.addAll(Arrays.asList(arr).subList(0, size));
        return items;
    }
}

public class ArraycopyTest {
    @Test
    public void testAdd() {
        CustomCollection<Integer> collection = new CustomCollection<>(Integer.class);
        collection.add(0, 3);
        assertEquals(Arrays.asList(3), collection.getItems());
        collection.add(0, 4);
        assertEquals(Arrays.asList(4, 3), collection.getItems());
    }

    @Test
    public void testAddAtEnd() {
        CustomCollection<Integer> collection = new CustomCollection<>(Integer.class);
        collection.add(0, 3);
        assertEquals(Arrays.asList(3), collection.getItems());
        collection.add(1, 4);
        assertEquals(Arrays.asList(3, 4), collection.getItems());
    }

    @Test
    public void testAddAll_InMiddle() {
        CustomCollection<Integer> collection = new CustomCollection<>(Integer.class);
        collection.add(0, 3);
        assertEquals(Arrays.asList(3), collection.getItems());
        collection.add(0, 4);
        assertEquals(Arrays.asList(4, 3), collection.getItems());
        collection.addAll(1, Arrays.asList(9, 1, 8));
        assertEquals(Arrays.asList(4, 9, 1, 8, 3), collection.getItems());
    }
    @Test
    public void testAddAll_Front() {
        CustomCollection<Integer> collection = new CustomCollection<>(Integer.class);
        collection.add(0, 3);
        assertEquals(Arrays.asList(3), collection.getItems());
        collection.add(0, 4);
        assertEquals(Arrays.asList(4, 3), collection.getItems());
        collection.addAll(0, Arrays.asList(9, 1, 8));
        assertEquals(Arrays.asList(9, 1, 8, 4, 3), collection.getItems());
    }
    @Test
    public void testAddAll_Back() {
        CustomCollection<Integer> collection = new CustomCollection<>(Integer.class);
        collection.add(0, 3);
        assertEquals(Arrays.asList(3), collection.getItems());
        collection.add(0, 4);
        assertEquals(Arrays.asList(4, 3), collection.getItems());
        collection.addAll(2, Arrays.asList(9, 1, 8));
        assertEquals(Arrays.asList(4, 3, 9, 1, 8), collection.getItems());
    }
}

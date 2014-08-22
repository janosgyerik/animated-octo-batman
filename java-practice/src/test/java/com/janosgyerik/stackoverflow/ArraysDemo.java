package com.janosgyerik.stackoverflow;

public class ArraysDemo<T> {

    private final T[] values;
    private int size;

    public ArraysDemo(T... values) {
        this.values = values.clone();
        this.size = values.length;
    }

    public static void main(String[] args) {
        ArraysDemo<Integer> arr = new ArraysDemo<>(5, 10, 12, 22, 4);
        arr.print();

        int index = arr.indexOf(11);
        if (index < 0) {
            System.out.println("Element not found");
        } else {
            System.out.println("Element found in the position " + index);
        }

        if (!arr.remove(5)) {
            System.out.println("Element not found");
        }
        arr.print();

        if (!arr.remove(5)) {
            System.out.println("Element not found");
        }
        arr.print();

        arr.remove(4);
        arr.print();
        arr.remove(22);
        arr.print();
    }

    int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                return i;
            }
        }
        return -1;
    }

    boolean remove(int value) {
        for (int i = 0; i < size; i++) {
            if (values[i].equals(value)) {
                --size;
                for (; i < size; ++i) {
                    values[i] = values[i + 1];
                }
                return true;
            }
        }
        return false;
    }

    void print() {
        System.out.print("Elements in the array: ");
        for (int i = 0; i < size; ++i) {
            System.out.print(values[i] + " ");
        }
        System.out.println();
    }
}
package com.janosgyerik.stackoverflow.junk.sort2;

import java.util.Arrays;

public class InsertionSort<T extends Comparable<T>> {

    public void sort(T[] arr) {
        for (int i = 1; i < arr.length; i++) {
            T temp = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1].compareTo(temp) > 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }
}

class InsertionSortDemo {
    public static void main(String[] args) {
        Integer[] ints = {-10, -5, 100, 51, 6, 50};
        new InsertionSort<Integer>().sort(ints);
        System.out.println(Arrays.toString(ints));

        String[] strings = {"hello", "there", "my", "friend"};
        new InsertionSort<String>().sort(strings);
        System.out.println(Arrays.toString(strings));
    }
}
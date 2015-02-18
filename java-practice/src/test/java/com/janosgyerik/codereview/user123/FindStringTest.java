package com.janosgyerik.codereview.user123;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FindStringTest {
    public static int findString(String[] array, String target) {
        return findString(array, target, 0, array.length - 1);
    }

    public static int findString(String[] array, String target, int low, int high){
        int middle = (low+high)/2;

        if (array[middle].equals(target)) return middle;
        if (high == 0) {
            return -1;
        }

        if (array[middle].equals("")){
            int left = middle;
            while (left >= low && array[left].equals("")) left--;
            if (left < low) return -1;
            if (array[left].compareTo(target) >= 0) return findString(array, target, low, left);

            int right = middle;
            while (right <= high && array[right].equals("")) right++;
            if (right > high) return -1;
            if (array[right].compareTo(target) <= 0) return findString(array, target, right, high);
        }
        if (array[middle].compareTo(target) > 0) return findString(array, target, low, middle);
        return findString(array, target, middle, high);
    }

    public static int findString2(String[] array, String target, int low, int high) {
        int middle = (low + high) / 2;

        if (array[middle].equals(target)) {
            return middle;
        }
        if (high == 0) {
            return -1;
        }

        if (array[middle].isEmpty()) {
            int left = middle;
            while (left >= low && array[left].isEmpty()) {
                left--;
            }
            if (left < low) {
                return -1;
            }
            if (array[left].compareTo(target) >= 0) {
                return findString(array, target, low, left);
            }

            int right = middle;
            while (right <= high && array[right].isEmpty()) {
                right++;
            }
            if (right > high) {
                return -1;
            }
            if (array[right].compareTo(target) <= 0) {
                return findString(array, target, right, high);
            }
        }
        if (array[middle].compareTo(target) > 0) {
            return findString(array, target, low, middle);
        }
        return findString(array, target, middle, high);
    }

    @Test
    public void test_0x() {
        assertEquals(-1, findString(new String[]{"", "", "a"}, "0"));
    }

    @Test
    public void test_0() {
        assertEquals(-1, findString(new String[]{"", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "a"));
    }

    @Test
    public void test_z() {
        assertEquals(-1, findString(new String[]{"", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "z"));
    }

    @Test
    public void test_a() {
        assertEquals(-1, findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "a"));
    }

    @Test
    public void test_ball() {
        assertEquals(4, findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "ball"));
    }

    @Test
    public void test_at() {
        assertEquals(0, findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "at"));
    }

    @Test
    public void test_car() {
        assertEquals(7, findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "car"));
    }

    @Test
    public void test_dad() {
        assertEquals(10, findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "dad"));
    }

    @Test
    public void test_core() {
        assertEquals(-1, findString(new String[]{"at", "", "", "", "ball", "", "", "car", "", "", "dad", "", ""}, "core"));
    }
}

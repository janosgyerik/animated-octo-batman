package com.janosgyerik.codereview.Legato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SetIntersectionsTest {

    private String findIntersection(String input) {
        String[] parts = input.split(";");
        String[] arr1 = parts[0].split(",");
        String[] arr2 = parts[1].split(",");
        return findIntersection(arr1, arr2);
    }

    private String findIntersection(String[] arr1, String[] arr2) {
        StringBuilder builder = new StringBuilder();
        for (int pos1 = 0, pos2 = 0; pos1 < arr1.length && pos2 < arr2.length; ) {
            int value1 = Integer.parseInt(arr1[pos1]);
            int value2 = Integer.parseInt(arr2[pos2]);

            if (value1 == value2) {
                builder.append(value1).append(",");
                ++pos1;
                ++pos2;
            } else if (value1 < value2) {
                ++pos1;
            } else {
                ++pos2;
            }
        }
        if (builder.length() == 0) {
            return "";
        }
        return builder.substring(0, builder.length() - 1);
    }

    @Test
    public void test_9_10_11_x_33_34_35() {
        assertEquals("", findIntersection("9,10,11;33,34,35"));
    }

    @Test
    public void test_3_7_8_22_x_11_22() {
        assertEquals("22", findIntersection("3,7,8,22;11,22"));
    }

    @Test
    public void test_11_12_13_14_x_14_15_16() {
        assertEquals("14", findIntersection("11,12,13,14;14,15,16"));
    }

    @Test
    public void test_20_21_22_x_45_46_47() {
        assertEquals("", findIntersection("20,21,22;45,46,47"));
    }

    @Test
    public void test_77_78_79_x_78_79_80_81_82() {
        assertEquals("78,79", findIntersection("77,78,79;78,79,80,81,82"));
    }

    @Test
    public void test_33_35_x_3_18_26_35() {
        assertEquals("35", findIntersection("33,35;3,18,26,35"));
    }

    @Test
    public void test_33_x_3_4() {
        assertEquals("", findIntersection("33;3,4"));
    }
}

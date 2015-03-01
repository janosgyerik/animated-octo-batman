package com.janosgyerik.stackoverflow;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class Answers20150228 {
    @Test
    public void test_array_out_of_bounds() {
        char[] str = {'A', 'B', 'C', 'D', 'E', 'F'};
        char[] strLG = {'L', 'G', 'L', 'G', 'L', 'G'};

        int i, j, k = 1;

        for (j = 0, i = str.length - 1; i > j; j++, i--) {
            char tmp;
            tmp = str[j];

            if (j % 2 == 0)
                str[j] = strLG[j];
            else
                str[j] = str[--i];

            str[i++] = tmp;
            k++;
        }
        for (i = 0; i < str.length; i++) {
            System.out.printf("%c\n", str[i]);
        }
        for (char c : str) {
            System.out.printf("%c\n", c);
        }
    }

    @Test
    public void test_growingArray2() {
        int[] arr = {1, 2, 3};
//        arr = Arrays.copyOf(arr, 4);
        int[] copy = new int[4];
        System.arraycopy(arr, 0, copy, 0, arr.length);
        assertEquals("x", Arrays.toString(copy));
    }
}

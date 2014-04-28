package com.janosgyerik.codility.so;

import org.junit.Assert;
import org.junit.Test;

public class InsertSortTest {
    public Integer[] sort2(Integer[] orig) {
        Integer[] values = orig.clone();
        for (int i = 1; i < values.length; i++) {
            if (values[i] < values[i - 1]) {
                for (int j = i; j >= 1; j--) {
                    if (values[j] < values[j - 1]) {
                        int temp = values[j - 1];
                        values[j - 1] = values[j];
                        values[j] = temp;
                    } else {
                        break;
                    }
                }
            }
        }
        return values;
    }

    public Integer[] sort4(Integer[] orig) {
        Integer[] values = orig.clone();
        for (int i = 1; i < values.length; i++) {
            for (int j = i; j >= 1; j--) {
                if (values[j] < values[j - 1]) {
                    int temp = values[j - 1];
                    values[j - 1] = values[j];
                    values[j] = temp;
                } else {
                    break;
                }
            }
        }
        return values;
    }

    public Integer[] sort(Integer[] orig) {
        Integer[] values = orig.clone();
        for (int i = 1; i < values.length; i++) {
            for (int j = i; j > 0 && values[j] < values[j - 1]; j--) {
                int temp = values[j - 1];
                values[j - 1] = values[j];
                values[j] = temp;
            }
        }
        return values;
    }

    @Test
    public void testExamples() {
        Assert.assertArrayEquals(new Integer[]{3, 4, 5}, sort(new Integer[]{5, 4, 3}));
        Assert.assertArrayEquals(new Integer[]{3, 4, 5}, sort(new Integer[]{5, 3, 4}));
        Assert.assertArrayEquals(new Integer[]{3, 4, 5}, sort(new Integer[]{3, 5, 4}));
    }
}

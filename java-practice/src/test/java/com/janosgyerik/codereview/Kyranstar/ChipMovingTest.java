package com.janosgyerik.codereview.Kyranstar;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ChipMovingTest {
    int chipMoving(int[] a) {
        if (a.length == 1)
            return 0; //base case
        int b = a[0], c = a[1], d = b;
        while (c != 0) {
            int f = c;
            c = b % c;
            b = f;
        }//b is now gcd of a[0] and a[1]

        if (a.length == 2)
            return b <= 1 ? 0 : 1; //if there are only two terms, return whether we can use the next number one or not
        int e = a[2];
        while (e != 0) {
            int f = e;
            e = d % e;
            d = f;
        }//e is now the gcd of a[0] and a[2]

        int leftMax = chipMoving(Arrays.copyOfRange(a, 1, a.length));
        int rightMax = chipMoving(Arrays.copyOfRange(a, 2, a.length));

        //if only one is available, use that one
        if (b > 1 && d <= 1)
            return 1 + leftMax;
        if (d > 1 && b <= 1)
            return 2 + rightMax;

        //check which is better and return that
        if (leftMax > rightMax)
            return 1 + leftMax;
        return 2 + rightMax;
    }

    @Test
    public void test_1_2_4_8() {
        assertEquals(3, chipMoving(new int[]{1, 2, 4, 8}));
    }

    @Test
    public void test_1_2_4_9() {
        assertEquals(2, chipMoving(new int[]{1, 2, 4, 9}));
    }

    @Test
    public void test_1_3_4_8() {
        assertEquals(1, chipMoving(new int[]{1, 3, 4, 8}));
    }

    @Test
    public void test_3_9() {
        assertEquals(1, chipMoving(new int[]{3, 9}));
    }

    @Test
    public void test_3_4() {
        assertEquals(0, chipMoving(new int[]{3, 4}));
    }
}

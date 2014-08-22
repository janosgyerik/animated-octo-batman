package com.janosgyerik.stackoverflow.junk;

import org.junit.Assert;
import org.junit.Test;

class AbleHelper {
    static int ans = 0;

    public static void ableHelperOrig(int a, int b, int c, int d) {
        if (a != c && (b + a) > c) {
            return;
        }
        if (b != d && (b + a) > d) {
            return;
        }
        if (a == c && b == d) {
            ans = 1;
            return;
        }
        ableHelperOrig(a + b, b, c, d);
        ableHelperOrig(a, b + a, c, d);

    }

    public boolean ablehelper_orig2(int a, int b, int c, int d) {
        if (a != c && (b + a) > c) {
            return false;
        }
        if (b != d && (b + a) > d) {
            return false;
        }
        if (a == c && b == d) {
            return true;
        }
        return ablehelper(a + b, b, c, d) || ablehelper(a, a + b, c, d);
    }

    static int steps = 0;

    public boolean ablehelper(int a, int b, int c, int d) {
        System.out.println("prev steps = " + steps);
        steps = 0;
        return ablehelper(null, a, b, c, d);
    }

    public boolean ablehelperx1(int a, int b, int c, int d) {
        System.out.println("prev x1    = " + steps);
        steps = 0;
        return ablehelperx1(null, a, b, c, d);
    }

    public boolean ablehelperx2(int a, int b, int c, int d) {
        System.out.println("prev x2    = " + steps);
        steps = 0;
        return ablehelperx2(null, a, b, c, d);
    }

    public boolean ablehelper(Object tmp, int a, int b, int c, int d) {
        ++steps;
        if (a > c || b > d) {
            return false;
        }
        if (a == c && b == d) {
            return true;
        }
        if (c > d) {
//            return ablehelper(null, a, b, c - d, d);
//            return ablehelper(null, a, b, c - d * (c - a) / d, d);
            return ablehelper(null, a, b, c - d * Math.max(1, c / d - 1), d);
        }
//        return ablehelper(null, a, b, c, d - c);
//        return ablehelper(null, a, b, c, d - c * (d - b) / c);
        return ablehelper(null, a, b, c, d - c * Math.max(1, d / c - 1));
    }

    public boolean ablehelperx1(Object tmp, int a, int b, int c, int d) {
        ++steps;
        if (a > c || b > d) {
            return false;
        }
        if (a == c && b == d) {
            return true;
        }
        return ablehelperx1(null, a, b, c, d - c) || ablehelperx1(null, a, b, c - d, d);
    }

    public boolean ablehelperx2(Object tmp, int a, int b, int c, int d) {
        ++steps;
        if (a > c || b > d) {
            return false;
        }
        if (a == c && b == d) {
            return true;
        }
        return ablehelperx2(null, a, b, c - d, d) || ablehelperx2(null, a, b, c, d - c);
    }

    public boolean ablehelper22(Object tmp, int a, int b, int c, int d) {
        ++steps;
        if (a > c || b > d) {
            return false;
        }
        if (a == c && b == d) {
            return true;
        }
        return ablehelper(null, a, a + b, c, d) || ablehelper(null, a + b, b, c, d);
//        return ablehelper(null, a + b, b, c, d) || ablehelper(null, a, a + b, c, d);
//        if (a > b) {
//            return ablehelper(null, a, a + b, c, d) || ablehelper(null, a + b, b, c, d);
//        }
//        return ablehelper(null, a + b, b, c, d) || ablehelper(null, a, a + b, c, d);
    }

    public boolean ablehelper2(int a, int b, int c, int d) {
        if (a == c && b == d) {
            return true;
        }
        if (a > c || b > d) {
            return false;
        }
        if (a == b) {
            return ablehelper(a, a + b, c, d) || ablehelper(a + b, b, c, d);
        }
        if (a < b) {
            return ablehelper(b, b, c, d) || ablehelper(a + b, b, c, d) || ablehelper(a, a + b, c, d);
        }
        return ablehelper(a, a, c, d) || ablehelper(a + b, b, c, d) || ablehelper(a, a + b, c, d);
    }
}

public class AbleHelperTest {
    @Test
    public void testExamples() {
        AbleHelper helper = new AbleHelper();
        Assert.assertTrue(helper.ablehelper(1, 2, 3, 8));
        Assert.assertFalse(helper.ablehelper(2, 2, 3, 8));
        Assert.assertTrue(helper.ablehelper(1, 1, 3, 8));
        Assert.assertFalse(helper.ablehelper(750, 146, 862, 825));
        Assert.assertFalse(helper.ablehelper(1, 1, 11, 11));
        Assert.assertTrue(helper.ablehelper(1, 1, 191, 1));
        Assert.assertTrue(helper.ablehelper(1, 1, 999, 1));
        Assert.assertTrue(helper.ablehelper(1, 1, 999, 2));
        Assert.assertFalse(helper.ablehelper(1, 2, 999, 1));
        Assert.assertTrue(helper.ablehelper(1, 2, 999, 2));
        Assert.assertFalse(helper.ablehelper(2, 2, 999, 1));
        Assert.assertFalse(helper.ablehelper(2, 2, 999, 2));
        Assert.assertFalse(helper.ablehelper(2, 6, 999, 6));
        Assert.assertFalse(helper.ablehelper(2, 6, 2, 999));
        Assert.assertTrue(helper.ablehelper(3, 2, 21, 34));
        Assert.assertTrue(helper.ablehelper(1, 1, 1, 1));
        Assert.assertTrue(helper.ablehelper(1, 1, 1, 1));
    }

    @Test
    public void testExamples2() {
        AbleHelper helper = new AbleHelper();
//        Assert.assertTrue(helper.ablehelper(1, 2, 3, 8));
//        Assert.assertFalse(helper.ablehelper(2, 2, 3, 8));
        Assert.assertTrue(helper.ablehelper(1, 1, 999, 1));
        Assert.assertTrue(helper.ablehelper(1, 1, 1, 999));
        Assert.assertTrue(helper.ablehelper(1, 1, 1, 999));
        Assert.assertFalse(helper.ablehelper(2, 2, 1, 999));
        Assert.assertTrue(helper.ablehelper(1, 1, 1, 1));
        Assert.assertTrue(helper.ablehelper(1, 1, 1, 1));

//        Assert.assertTrue(helper.ablehelperx1(1, 2, 3, 8));
//        Assert.assertFalse(helper.ablehelperx1(2, 2, 3, 8));
        Assert.assertTrue(helper.ablehelperx1(1, 1, 999, 1));
        Assert.assertTrue(helper.ablehelperx1(1, 1, 1, 999));
        Assert.assertTrue(helper.ablehelperx1(1, 1, 1, 1));
        Assert.assertTrue(helper.ablehelperx1(1, 1, 1, 1));

        Assert.assertTrue(helper.ablehelperx2(1, 2, 3, 8));
        Assert.assertFalse(helper.ablehelperx2(2, 2, 3, 8));
        Assert.assertTrue(helper.ablehelperx2(1, 1, 999, 1));
        Assert.assertTrue(helper.ablehelperx2(1, 1, 1, 999));
        Assert.assertTrue(helper.ablehelperx2(1, 1, 1, 1));
        Assert.assertTrue(helper.ablehelperx2(1, 1, 1, 1));
    }

    @Test
    public void testOrig() {
        AbleHelper.ans = 0;
        AbleHelper.ableHelperOrig(1, 2, 3, 8);
        Assert.assertTrue(AbleHelper.ans == 1);

        AbleHelper.ans = 0;
        AbleHelper.ableHelperOrig(2, 2, 3, 8);
        Assert.assertTrue(AbleHelper.ans == 0);

        AbleHelper.ans = 0;
        AbleHelper.ableHelperOrig(1, 1, 3, 8);
        Assert.assertTrue(AbleHelper.ans == 1);
    }
}
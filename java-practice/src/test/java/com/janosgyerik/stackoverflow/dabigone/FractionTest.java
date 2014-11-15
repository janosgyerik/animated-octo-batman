package com.janosgyerik.stackoverflow.dabigone;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FractionTest {

    public int[] reducedFraction(int numerator, int denominator) {
        int GCD = numerator;
        int tempN = numerator;
        int tempD = denominator;
        int[] fractionParts = {0, numerator, denominator};

        if (numerator == 0) //if the numerator is 0
        {
            return fractionParts;
        }

        //Making sure the program works with negative values by temporarily converting GCD and fractionParts[2] to positive values
        GCD = (numerator < 0) ? -numerator : numerator;

        tempD = (denominator < 0) ? -denominator : denominator;

        //Finding the GCD of the numerator and denominator
        while (GCD != tempD) {
            if (GCD > tempD)
                GCD -= tempD;
            else
                tempD -= GCD;
        }

        //Simplfying numerator and denominator
        fractionParts[1] /= GCD;
        fractionParts[2] /= GCD;

        //Temporarily making the numerator and denominator positive
        tempN = (fractionParts[1] < 0) ? -fractionParts[1] : fractionParts[1];

        tempD = (fractionParts[2] < 0) ? -fractionParts[2] : fractionParts[2];

        //if the numerator is not greater than the denominator return the simplified fraction.
        if (tempN < tempD) {
            return fractionParts;
        }

        //Finding value of whole number, numerator and possibly converting denominator to original sign
        fractionParts[0] = fractionParts[1] / fractionParts[2];
        fractionParts[1] = fractionParts[1] - (fractionParts[1] / fractionParts[2]) * fractionParts[2];

        if (fractionParts[1] < 0 && fractionParts[2] < 0) //if the numerator and denominator are both negative
        {
            fractionParts[1] = (fractionParts[1] < 0) ? -fractionParts[1] : fractionParts[1];

            fractionParts[2] = (fractionParts[2] < 0) ? -fractionParts[2] : fractionParts[2];
        }

        return fractionParts;
    }

    private static void printFraction(int[] parts) {
        int integral = parts[0];
        int numerator = parts[1];
        int denominator = parts[2];
        if (numerator == 0) {
            System.out.println(integral);
        } else if (integral == 0) {
            System.out.println(String.format("%d/%d", numerator, denominator));
        } else {
            System.out.println(String.format("%d %d/%d", integral, numerator, denominator));
        }
    }

    @Test
    public void testOrig() {
        int[] frac = reducedFraction(0, -2);
        printFraction(frac);
        assertEquals(0, frac[0]);
        assertEquals(0, frac[1]);
        assertEquals(-2, frac[2]);

        frac = reducedFraction(3, -2);
        printFraction(frac);
        assertEquals(-1, reducedFraction(3, -2)[0]);
        assertEquals(1, reducedFraction(3, -2)[1]);
        assertEquals(-2, reducedFraction(3, -2)[2]);

        frac = reducedFraction(3, 2);
        printFraction(frac);
        assertEquals(1, reducedFraction(3, 2)[0]);
        assertEquals(1, reducedFraction(3, 2)[1]);
        assertEquals(2, reducedFraction(3, 2)[2]);

        frac = reducedFraction(20, 10);
        printFraction(frac);
        assertEquals(2, reducedFraction(20, 10)[0]);
        assertEquals(0, reducedFraction(20, 10)[1]);
        assertEquals(1, reducedFraction(20, 10)[2]);
    }

    @Test
    public void testNew() {
//        System.out.println(MixedNumber.fromFraction(0, -2));
//        System.out.println(MixedNumber.fromFraction(3, -2));
//        System.out.println(MixedNumber.fromFraction(3, 2));
//        System.out.println(MixedNumber.fromFraction(20, 10));
    }

    @Test
    public void testZeroNumerator() {
        assertEquals("0", MixedNumber.fromFraction(0, -2).toString());
    }

    //@Test
    public void test_minus3_over_2() {
        assertEquals("-1 1/2", MixedNumber.fromFraction(-3, 2).toString());
    }

    //@Test
    public void test_3_over_minus2() {
        assertEquals("-1 1/2", MixedNumber.fromFraction(3, -2).toString());
    }

    @Test
    public void test_minus3_over_minus2() {
        assertEquals("1 1/2", MixedNumber.fromFraction(-3, -2).toString());
    }

    @Test
    public void test_123_over_82() {
        assertEquals("1 1/2", MixedNumber.fromFraction(123, 82).toString());
    }

    @Test
    public void test_82_over_123() {
        assertEquals("2/3", MixedNumber.fromFraction(82, 123).toString());
    }

}

class MixedNumber {
    private final int integral;
    private final int numerator;
    private final int denominator;

    private MixedNumber(int integral, int numerator, int denominator) {
        this.integral = integral;
        this.numerator = numerator;
        this.denominator = denominator;
    }

    public MixedNumber(int[] fractionParts) {
        this(fractionParts[0], fractionParts[1], fractionParts[2]);
    }

    private static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    static MixedNumber fromFraction(int numerator, int denominator) {
        int tempN = numerator;
        int tempD = denominator;
        int[] fractionParts = {0, numerator, denominator};

        if (numerator == 0) //if the numerator is 0
        {
            return new MixedNumber(0, 0, denominator);
        }

        //Making sure the program works with negative values by temporarily converting GCD and fractionParts[2] to positive values
        int GCD = gcd(numerator < 0 ? -numerator : numerator, denominator < 0 ? -denominator : denominator);

        //Simplfying numerator and denominator
        fractionParts[1] /= GCD;
        fractionParts[2] /= GCD;

        //Temporarily making the numerator and denominator positive
        tempN = (fractionParts[1] < 0) ? -fractionParts[1] : fractionParts[1];

        tempD = (fractionParts[2] < 0) ? -fractionParts[2] : fractionParts[2];

        //if the numerator is not greater than the denominator return the simplified fraction.
        if (tempN < tempD) {
            return new MixedNumber(fractionParts);
        }

        //Finding value of whole number, numerator and possibly converting denominator to original sign
        fractionParts[0] = fractionParts[1] / fractionParts[2];
        fractionParts[1] = fractionParts[1] - (fractionParts[1] / fractionParts[2]) * fractionParts[2];

        if (fractionParts[1] < 0 && fractionParts[2] < 0) //if the numerator and denominator are both negative
        {
            fractionParts[1] = (fractionParts[1] < 0) ? -fractionParts[1] : fractionParts[1];

            fractionParts[2] = (fractionParts[2] < 0) ? -fractionParts[2] : fractionParts[2];
        }

        return new MixedNumber(fractionParts);
    }

    @Override
    public String toString() {
        if (numerator == 0) {
            return "" + integral;
        } else if (integral == 0) {
            return String.format("%d/%d", numerator, denominator);
        } else {
            return String.format("%d %d/%d", integral, numerator, denominator);
        }
    }
}

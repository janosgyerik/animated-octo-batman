package com.janosgyerik.stackoverflow.ambigram_maker;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class Rational {

    /**
     * A public constant that defines the rational value of 0.
     */
    public static final Rational ZERO = new Rational(0, 1);
    /**
     * A public constant that defines the rational value of 1.
     */
    public static final Rational ONE = new Rational(1, 1);

    private static double precision = 1E-10;

    private final long num;
    private final long den;
    private final double result;

    /**
     * Creates a new {@code Rational} with the given numerator and
     * denominator. It has special cases for Positive Infinity
     * ({@link java.lang.Double#POSITIVE_INFINITY Double.POSITIVE_INFINITY}),
     * Negative Infinity ({@link java.lang.Double#NEGATIVE_INFINITY
     * Double.NEGATIVE_INFINITY}) and Not A Number
     * ({@link java.lang.Double#NaN Double.NaN}.
     *
     * @param numerator   The numerator.
     * @param denominator The denominator.
     */
    public Rational(long numerator, long denominator) {
        // for dealing with "infinities" and "NaN":
        if (denominator == 0) {
            den = 0;
            if (numerator > 0) {
                num = 1;
                result = Double.POSITIVE_INFINITY;
            } else if (numerator < 0) {
                num = -1;
                result = Double.NEGATIVE_INFINITY;
            } else {
                num = 0;
                result = Double.NaN;
            }
            return;
        }
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        num = numerator;
        den = denominator;
        result = (double) numerator / denominator;
    }

    /**
     * Creates a new {@code Rational} object that approximates the value of
     * the decimal to the set level of {@link #getPrecision() precision}. It
     * is also capable of approximating irrational values like
     * {@link java.lang.Math#PI Math.PI}, {@link java.lang.Math#E Math.E} or
     * the golden ratio, &phi;.
     *
     * @param decimal The value to approximate.
     */
    public static Rational fromDouble(double decimal) {
        if (decimal == Double.NaN) {
            return new Rational(0, 0);
        }
        if (decimal == Double.POSITIVE_INFINITY) {
            return new Rational(1, 0);
        }
        if (decimal == Double.NEGATIVE_INFINITY) {
            return new Rational(-1, 0);
        }

        long nu, de;
        long whole = 0;     // fail-safe value

        boolean negative = decimal < 0;

        decimal = Math.abs(decimal);

        boolean hasWhole = decimal >= 1;
        if (hasWhole) {     // keep fractional part.
            whole = (long) decimal;
            decimal -= whole;
        }

        if (decimal == 0) { // no fractional part present or 0 input
            return new Rational(negative ? -whole : whole, 1);
        }
        // initially, the extreme points are 0 and 1.
        // decimal always lies in the interval: (n1/d1, n2/d2)
        long n1 = 0, d1 = 1;
        long n2 = 1, d2 = 1;

        double epsilon;     // the error amount in the approximation.
        while (true) {
            long n = n1 + n2, d = d1 + d2;
            double result = (double) n / d;
            epsilon = Math.abs(result - decimal);
            if (epsilon <= precision) {     // goal reached
                nu = n;
                de = d;
                break;
            } else if (result < decimal) {  // increase lower bound
                n1 = n;
                d1 = d;
            } else {                        // increase upper bound
                n2 = n;
                d2 = d;
            }
        }
        if (hasWhole) {     // add the whole part to the fraction
            nu += de * whole;
        }
        return new Rational(negative ? 0L - nu : nu, de);
    }

    /**
     * Returns the set level of precision. The <i>default</i> level of
     * precision is {@code 1.0E-10}, unless changed.
     *
     * @return The level of precision.
     */
    public static double getPrecision() {
        return precision;
    }

    /**
     * Returns the Highest Common Factor of two integers. It employs the
     * Euclidean division method.
     *
     * @param a One of the two numbers.
     * @param b The other number.
     * @return The H.C.F of {@code a} and {@code b}.
     */
    private static long hcf(long a, long b) {
        if (a == 0 || b == 0) {
            return 0;       // ???
        }
        // turn all the negative arguments to positive.
        if (a < 0) a = 0L - a;
        if (b < 0) b = 0L - b;

        if (a < b) {
            long t = a;
            a = b;
            b = t;
        }
        long r;
        do {
            r = a % b;
            a = b;
            b = r;
        } while (r > 0);
        return a;
    }

    // Demo
    public static void main(String[] args) {
        Rational PI = Rational.fromDouble(Math.PI);
        System.out.println("Pi = " + PI);
    }

    /**
     * This method returns the {@code Rational} object that is the reduced
     * form of {@code this Rational}. (More specifically,
     * the numerator and denominator have no common factor.)
     *
     * @return The reduced form of {@code this Rational}.
     */
    public Rational reduce() {
        long hcf = hcf(num, den);
        if (hcf == 0) { // infinities and NaN
            return this;
        } else {
            long n = num / hcf;
            long d = den / hcf;
            return new Rational(n, d);
        }
    }

    /**
     * Returns the <i>sum</i> of {@code rational} with {@code this}.
     *
     * @param rational The {@code Rational} to add.
     * @return Their sum.
     */
    public Rational add(Rational rational) {
        if (this.result == Double.NaN) {
            return this;
        } else //noinspection ConstantConditions
            if (rational.result == Double.NaN) {
                return rational;
            }
        Rational o = reduce();
        long n1 = o.num, d1 = o.den;
        o = rational.reduce();
        long n2 = o.num, d2 = o.den;
        return new Rational(n1 * d2 + n2 * d1, d1 * d2).reduce();
    }

    /**
     * Returns the <i>difference</i> of {@code rational} with {@code this}.
     *
     * @param rational The {@code Rational} to subtract.
     * @return Their difference.
     */
    public Rational subtract(Rational rational) {
        return add(new Rational(0L - rational.num, rational.den));
    }

    /**
     * Returns the <i>product</i> of {@code rational} and {@code this}.
     *
     * @param rational The {@code Rational} to multiply with.
     * @return Their product.
     */
    public Rational multiply(Rational rational) {
        return new Rational(
                this.num * rational.num,
                this.den * rational.den)
                .reduce();
    }

    /**
     * <i>Divides</i> {@code this} with {@code rational}.
     *
     * @param rational The divisor.
     * @return The required quotient.
     */
    public Rational divide(Rational rational) {
        return multiply(rational.reciprocate());
    }

    /**
     * Returns the reciprocal of {@code this}.
     *
     * @return the reciprocal of {@code this}.
     */
    public Rational reciprocate() {
        return new Rational(den, num);
    }

    /**
     * Returns the {@code String} representation of tis {@code Rational}.
     *
     * @return the {@code String} representation of tis {@code Rational}.
     */
    @Override
    public String toString() {
        return Long.toString(num).concat("/").concat(Long.toString(den));
    }

    /**
     * Compares {@code this} object with another with respect to class and
     * then the values of the numerator and denominator.
     *
     * @param another The {@code Object} to compare with.
     * @return {@code true} if the objects are equal, {@code false} otherwise.
     */
    @Override
    public boolean equals(Object another) {
        if (another instanceof Rational) {
            Rational oldF = reduce();
            Rational newF = ((Rational) another).reduce();
            return (oldF.num == newF.num) &&
                    (oldF.den == newF.den);
        }
        return false;
    }

    /**
     * Returns {@code this} to the power {@code p}.
     *
     * @param p The power to be raised to.
     * @return {@code this} to the power {@code p}.
     */
    public Rational pow(int p) {
        Rational result = ONE;
        Rational n = new Rational(num, den);
        boolean neg = p < 0;
        if (neg) p = 0 - p;
        while (p > 0) {
            if ((p & 1) == 1) {
                result = result.multiply(n);
                p--;
            }
            n = n.multiply(n);
            p >>>= 1;
        }
        return neg ? result.reciprocate() : result;
    }

    /**
     * Returns {@code this} to the <i>fractional</i> power {@code p}.
     *
     * @param p The power to be raised to.
     * @return {@code this} to the power {@code p}.
     */
    public Rational pow(Rational p) {
        return Rational.fromDouble(Math.pow(this.result, p.result));
    }
}

public class RationalTest {

    @Test
    public void testAdd() {
        Rational r1, r2;
        r1 = new Rational(10, 20);
        r2 = new Rational(20, 30);
        assertEquals(new Rational(7, 6), r1.add(r2));
    }

    @Test
    public void testSubtract() {
        Rational r1, r2;
        r1 = new Rational(20, 30);
        r2 = new Rational(10, 20);
        assertEquals(new Rational(1, 6), r1.subtract(r2));
    }

    @Test
    public void testMultiply() {
        Rational r1, r2;
        r1 = Rational.fromDouble(0.5);
        r2 = Rational.fromDouble(2);
        assertEquals(Rational.ONE, r1.multiply(r2));
    }

    @Test
    public void testDivide() {
        Rational r1, r2;
        r1 = Rational.fromDouble(0.5);
        r2 = Rational.fromDouble(0.0625);
        assertEquals(Rational.fromDouble(8), r1.divide(r2));
    }

    @Test
    public void testPow() {
        assertEquals(Rational.ONE, Rational.fromDouble(2).pow(Rational.ZERO));
        assertEquals(new Rational(1, 64), new Rational(1, 4).pow(3));
    }
}

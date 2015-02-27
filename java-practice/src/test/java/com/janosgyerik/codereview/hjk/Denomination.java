package com.janosgyerik.codereview.hjk;

import java.util.Objects;

enum Denomination {
    A_MILLION(1_000_000, "$1 million"),
    FIFTY_DOLLARS(50, "$50"),
    TWENTY_DOLLARS(20, "$20"),
    TEN_DOLLARS(10, "$10"),
    FIVE_DOLLARS(5, "$5"),
    DOLLAR_NINETY_NINE(1.99, "$1.99"),
    A_DOLLAR(1, "$1"),
    QUARTER(0.25, "25¢"),
    DIME(0.1, "10¢"),
    NICKEL(0.05, "5¢"),
    A_CENT(0.01, "1¢");

    private final int value;
    private String description;

    private static final int MULTIPLIER = 100;

    private Denomination(double value, final String description) {
        this.value = Double.valueOf(MULTIPLIER * value).intValue();
        this.description = Objects.requireNonNull(description);
    }

    /**
     * @param input the value to compare against
     * @return <code>true</code> if <code>input</code> is not smaller than the current value
     */
    public boolean canBreakdown(double input) {
        return MULTIPLIER * input >= value;
    }

    /**
     * Breaks down the input against the current value.
     *
     * @param input the input to start
     * @return a two-element array, the first being the quotient (aka multiplier) and the second
     *         being the remainder
     */
    public Breakdown breakdown(double input) {
        int intValue = Double.valueOf(MULTIPLIER * input).intValue();
        int count = intValue / value;
        int remainder = intValue % value;
        return new Breakdown(count, (double) remainder / MULTIPLIER);
    }

    @Override
    public String toString() {
        return description;
    }

    /**
     * @param multiplier the value to represent
     * @return a representation of the multiplier and the current value
     */
    public String toString(int multiplier) {
        return String.format("%d x %s", multiplier, toString());
    }

    /**
     * @param multiplier the value to multiply with
     * @return the product of the multiplier and the current value
     */
    public double multiply(int multiplier) {
        return (double) value * multiplier / MULTIPLIER;
    }

    public static class Breakdown {
        final int count;
        final double remainder;

        public Breakdown(int count, double remainder) {
            this.count = count;
            this.remainder = remainder;
        }
    }
}

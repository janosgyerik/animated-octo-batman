package com.janosgyerik.codereview.hjk;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Utility class for manipulating with {@link com.janosgyerik.codereview.hjk.Denomination} enums.
 */
class Calculator {

    private Calculator() {
        // empty
    }

    /**
     * Break down the input into {@link com.janosgyerik.codereview.hjk.Denomination} values.
     *
     * @param input the value to break down
     * @return an unmodifiable {@link java.util.Map} with the {@link com.janosgyerik.codereview.hjk.Denomination} as keys and a positive
     *         integer, the multiplier, as values
     */
    public static Map<Denomination, Integer> getBreakdown(double input) {
        final Map<Denomination, Integer> result = new EnumMap<>(Denomination.class);
        double remainder = input;
        for (final Denomination denomination : Denomination.values()) {
            if (denomination.canBreakdown(remainder)) {
                Denomination.Breakdown breakdown = denomination.breakdown(remainder);
                result.put(denomination, breakdown.count);
                remainder = breakdown.remainder;
            }
        }
        return Collections.unmodifiableMap(result);
    }

    /**
     * @param map the {@link java.util.Map} to generate from
     * @return a human-reable output
     */
    public static String format(Map<Denomination, Integer> map) {
        return Objects.requireNonNull(map)
                .entrySet().stream()
                .map(e -> e.getKey().toString(e.getValue()))
                .collect(Collectors.joining(", "));
    }

    /**
     * @param map the {@link java.util.Map} to generate from
     * @return the sum of the product of the map's keys and values
     */
    public static double compute(Map<Denomination, Integer> map) {
        return Objects.requireNonNull(map).entrySet().stream()
                .mapToDouble(e -> e.getKey().multiply(e.getValue())).sum();
    }
}

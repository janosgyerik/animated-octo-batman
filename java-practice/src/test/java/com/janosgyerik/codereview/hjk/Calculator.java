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
        double temp = input;
        for (final Denomination current : Denomination.values()) {
            if (current.canBreakdown(temp)) {
                final double[] parts = current.breakdown(temp);
                result.put(current, Double.valueOf(parts[0]).intValue());
                temp = parts[1];
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

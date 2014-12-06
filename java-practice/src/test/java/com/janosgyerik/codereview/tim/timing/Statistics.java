package com.janosgyerik.codereview.tim.timing;

import java.util.Collections;
import java.util.List;

public class Statistics {

    private static final int REMOVE_BEST_PERCENT = 10;
    private static final int REMOVE_WORST_PERCENT = 10;

    /**
     * removes the x lowest and x highest values from the list.
     *
     * Also sorts the list ascending.
     *
     * @param list list
     */
    public static void removeWorstAndBest(List<Long> list) {
        // sort ascending
        Collections.sort(list,
                (Long l1, Long l2) -> (int) (l1 - l2));

        // remove x worst and x best results
        int originalSize = list.size();
        list.subList(list.size() - REMOVE_BEST_PERCENT * originalSize / 100,
                originalSize).clear();
        list.subList(0, REMOVE_WORST_PERCENT * originalSize / 100).clear();
    }

    /**
     * returns the mean of the list.
     *
     * @param list list
     * @return mean
     */
    public static double calculateMean(final List<Long> list) {
        double mean = 0;
        final int length = list.size();
        for (Long item : list) {
            mean += item / (double) length;
        }
        return mean;
    }

    /**
     * returns the median of the list.
     *
     * Expects a sorted list.
     *
     * @param list list
     * @return median
     */
    public static double calculateMedian(List<Long> list) {
        return list.get(list.size() / 2);
    }

    /**
     * returns the
     *
     * Expects a sorted list.
     *
     * @param list list
     * @param percentile percentile
     * @return percentile
     */
    public static double percentile(List<Long> list, int percentile) {
        int rank = (int) Math.ceil(percentile / 100 * list.size());
        return list.get(rank);
    }
}
package com.janosgyerik.codereview.hosch250;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StdTest {
    private static double standardDeviation(List<Double> numberSet, double divisor) {
        double mean = numberSet.stream().mapToDouble(x -> x).average().getAsDouble();
        return Math.sqrt(numberSet.stream().mapToDouble(x -> Math.pow(x - mean, 2)).sum() / divisor);
    }

    static double populationStandardDeviation(List<Double> numberSet) {
        return standardDeviation(numberSet, numberSet.size());
    }

    static double sampleStandardDeviation(List<Double> numberSet) {
        return standardDeviation(numberSet, numberSet.size() - 1);
    }

    @Test
    public void testSampleStd() {
        assertEquals(6.8068592855540455, sampleStandardDeviation(Arrays.asList(1., 11., 13., 17.)), .0000001);
    }

    @Test
    public void testPopulationStd() {
        assertEquals(5.894913061275798, populationStandardDeviation(Arrays.asList(1., 11., 13., 17.)), .0000001);
    }

}

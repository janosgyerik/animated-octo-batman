package com.janosgyerik.codereview.Anesh;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class EditDistance {

    public static int getEditDistance(String sourceString, String destinationString) {
        if (sourceString == null || destinationString == null){
            throw new IllegalArgumentException("String cannot be null");
        }

        int sourceLength = sourceString.length();
        int destLength = destinationString.length();
        int len = Math.min(sourceLength, destLength);

        int distance = 0;
        for (int i = 0; i < len; ++i) {
            if (sourceString.charAt(i) != destinationString.charAt(i)) {
                ++distance;
            }
        }
        distance += Math.abs(sourceLength - destLength);
        return distance;
    }

    public static int getEditDistance0(String sourceString, String destinationString) {
        if (sourceString == null || destinationString == null){
            throw new IllegalArgumentException("String cannot be null");
        }

        int distance = 0;
        int sourceLength = sourceString.length() , destLength = destinationString.length();

        for (int i = 0; i < sourceLength && i < destLength; i++) {
            //how can I optimize loop condition, 3 conditions are checked on
            //every iteration
            if (sourceString.charAt(i) != destinationString.charAt(i)){
                //if characters are unequal and increment the distance
                distance++;
            }
        }
        if (destLength !=  sourceLength ){
            distance += Math.abs(sourceLength - destLength);
        }
        return distance;
    }
}

public class EditDistanceTest {
    @Test
    public void test_coti_cat() {
        assertEquals(2, EditDistance.getEditDistance("coti", "cat"));
    }

    @Test
    public void test_alpha_beta() {
        assertEquals(5, EditDistance.getEditDistance("alpha", "beta"));
    }

    @Test
    public void test_beta_pedal() {
        assertEquals(3, EditDistance.getEditDistance("beta", "pedal"));
    }

    @Test
    public void test_empty() {
        assertEquals(3, EditDistance.getEditDistance("", "123"));
    }
}

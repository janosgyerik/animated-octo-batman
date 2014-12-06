package com.janosgyerik.codereview.user4047248;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class TrainDepartures {

    public static int findMinutesToNextDeparture() {
        long time = System.currentTimeMillis();
        int initialTime = (int) (time / (1000 * 60) % 60);
        return findMinutesToNextDeparture2(initialTime);
    }

    public static int findMinutesToNextDeparture(int initialTime) {
        List<Integer> times = new ArrayList<>(new TreeSet<>(Arrays.asList(10, 30, 20, 40, 50, 52, 55, 56, 57)));
        // index of the search key, if it is contained in the array;
        // otherwise, (-(insertion point) - 1).
        // The insertion point is defined as the point at which
        // the key would be inserted into the array:
        // the index of the first element greater than the key, or a.length
        // if all elements in the array are less than the specified key.
        // Note that this guarantees that the return value will be >= 0
        // if and only if the key is found.
        int index = Collections.binarySearch(times, initialTime);
        final int nextDeparture;
        if (-index > times.size()) {
            nextDeparture = times.get(0) + 60;
        } else if (index < 0) {
            nextDeparture = times.get(-index - 1);
        } else {
            nextDeparture = times.get(index + 1);
        }
        return nextDeparture - initialTime;
    }

    public static int findMinutesToNextDeparture2(int initialTime) {
        int minutes = initialTime;

        while (true) {
            minutes++;
            switch (minutes % 60) {
                case 10:
                case 20:
                case 30:
                case 40:
                case 50:
                case 52:
                case 55:
                case 56:
                case 57:
                    return minutes - initialTime;
            }
        }
    }
}

public class TrainDeparturesTest {
    @Test
    public void testMinutesBeforeFirst() {
        assertEquals(10, TrainDepartures.findMinutesToNextDeparture(0));
        assertEquals(7, TrainDepartures.findMinutesToNextDeparture(3));
    }

    @Test
    public void testMinutesInBetween() {
        assertEquals(7, TrainDepartures.findMinutesToNextDeparture(13));
        assertEquals(7, TrainDepartures.findMinutesToNextDeparture(43));
    }

    @Test
    public void testExactMatches() {
        assertEquals(2, TrainDepartures.findMinutesToNextDeparture(50));
        assertEquals(3, TrainDepartures.findMinutesToNextDeparture(52));
    }

    @Test
    public void testMinutesBeyondLast() {
        assertEquals(12, TrainDepartures.findMinutesToNextDeparture(58));
        assertEquals(11, TrainDepartures.findMinutesToNextDeparture(59));
        assertEquals(9, TrainDepartures.findMinutesToNextDeparture(61));
    }
}

package com.janosgyerik.codereview.junk;

import org.junit.Test;

public class TimeFormatTest {
    private String timeDescription(String description, int seconds) {
        return putTimeInXX(description, secondsToString(seconds));
    }

    private String secondsToString(int seconds) {
        return String.format("%02d:%02d", seconds / 60, seconds % 60);
    }

    private String putTimeInXX(String description, String timeString) {
        return description.replaceAll("XX", timeString);
    }

    @Test
    public void testExample() {
        System.out.println(timeDescription("You need stay here for (XX)", 34));
        System.out.println(timeDescription("You need stay here for (XX)", 134));
    }
}

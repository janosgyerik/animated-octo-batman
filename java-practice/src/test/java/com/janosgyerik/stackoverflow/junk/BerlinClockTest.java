package com.janosgyerik.stackoverflow.junk;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class BerlinClock {
    public static String getTime(String time) {
        if (!time.matches("\\d\\d:\\d\\d:\\d\\d")) {
            throw new IllegalArgumentException("Time must be in the format HH:MM:SS");
        }

        StringBuilder timeSB = new StringBuilder();
        String[] timeElements = time.split(":");

        timeSB.append(new Second().getLamps(Integer.parseInt(timeElements[2]))).append("\n");
        timeSB.append(new Hour().getLamps(Integer.parseInt(timeElements[0]))).append("\n");
        timeSB.append(new Minute().getLamps(Integer.parseInt(timeElements[1]))).append("\n");

        return timeSB.toString();
    }
}

interface TimeUnit {

    public String getLamps(int unit);

}

class Hour implements TimeUnit {

    private static final String ALL_LIGHTS_OFF = "OOOO";
    private static final String RED_LIGHT = "R";
    private static final int MULTIPLE_OF_FIVE = 5;

    @Override
    public String getLamps(int hrs) {

        return getLampsForHoursMultiplesOfFive(hrs) + "\n" + getLampsForSingleHours(hrs);
    }

    public String getLampsForHoursMultiplesOfFive(int hours) {

        StringBuilder lamps = new StringBuilder(ALL_LIGHTS_OFF);

        for (int i = 0; i < (hours / MULTIPLE_OF_FIVE); i++) {
            lamps.replace(i, i + 1, RED_LIGHT);
        }
        return lamps.toString();
    }

    public String getLampsForSingleHours(int hours) {

        StringBuilder lamps = new StringBuilder(ALL_LIGHTS_OFF);

        for (int i = 0; i < (hours % MULTIPLE_OF_FIVE); i++) {  //dont do calculaion ever loop, do outside loop
            lamps.replace(i, i + 1, RED_LIGHT);
        }
        return lamps.toString();
    }
}

class Minute implements TimeUnit {

    private static final String RED_LIGHT = "R";
    private static final String YELLOW_LIGHT = "Y";
    private static final String ALL_FOUR_LIGHTS_OFF = "OOOO";
    private static final String ALL_ELEVEN_LIGHTS_OFF = "OOOOOOOOOOO";

    @Override
    public String getLamps(int minutes) {

        int minutesDividedBy5 = minutes / 5;
        int minutesModulus5 = minutes % 5;

        return getLampsForMinutesMultiplesOfFive(minutesDividedBy5) + "\n" + getLampsForSingleMinutes(minutesModulus5);
    }

    private String getLampsForMinutesMultiplesOfFive(int minutes) {

        StringBuilder lamps = new StringBuilder(ALL_ELEVEN_LIGHTS_OFF);

        for (int i = 0; i < minutes; i++) {
            if (0 == (i + 1) % 3) {
                lamps.replace(i, i + 1, RED_LIGHT);
            } else {
                lamps.replace(i, i + 1, YELLOW_LIGHT);
            }
        }
        return lamps.toString();
    }

    private String getLampsForSingleMinutes(int minutes) {

        StringBuilder lamps = new StringBuilder(ALL_FOUR_LIGHTS_OFF);

        for (int i = 0; i < minutes; i++) {
            lamps.replace(i, i + 1, YELLOW_LIGHT);
        }
        return lamps.toString();
    }
}

class Second implements TimeUnit {

    @Override
    public String getLamps(int seconds) {

        if (0 == seconds%2) {
            return "Y";
        }

        return "O";
    }
}

public class BerlinClockTest {
    @Test
    public void test_00_00_00() {
        assertEquals("Y\n" +
                "OOOO\n" +
                "OOOO\n" +
                "OOOOOOOOOOO\n" +
                "OOOO\n", BerlinClock.getTime("00:00:00"));
    }

    @Test
    public void test_13_17_01() {
        assertEquals("O\n" +
                "RRRO\n" +
                "RROO\n" +
                "YYOOOOOOOOO\n" +
                "YYYO\n", BerlinClock.getTime("13:17:01"));
    }

    @Test
    public void test_23_59_59() {
        assertEquals("O\n" +
                "RRRR\n" +
                "RRRO\n" +
                "YYRYYRYYRYY\n" +
                "YYYY\n", BerlinClock.getTime("23:59:59"));
    }

    @Test
    public void test_24_00_00() {
        assertEquals("Y\n" +
                "RRRR\n" +
                "RRRR\n" +
                "OOOOOOOOOOO\n" +
                "OOOO\n", BerlinClock.getTime("24:00:00"));
    }
}



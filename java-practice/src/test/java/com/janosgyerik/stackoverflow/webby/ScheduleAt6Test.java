package com.janosgyerik.stackoverflow.webby;

import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertEquals;

public class ScheduleAt6Test {
    private int getHoursUntilTarget(int targetHour) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        return hour < targetHour ? targetHour - hour : targetHour - hour + 24;
    }

    @Test
    public void testGetHoursUntilTarget() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        assertEquals(24, getHoursUntilTarget(hour));
        assertEquals(1, getHoursUntilTarget(hour + 1));
        assertEquals(23, getHoursUntilTarget(hour - 1));
    }
}

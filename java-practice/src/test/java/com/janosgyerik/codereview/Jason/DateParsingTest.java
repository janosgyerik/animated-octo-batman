package com.janosgyerik.codereview.Jason;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;

public class DateParsingTest {

    public Date convertIntToDate(Integer intDate) {
        if (intDate < 100000 || intDate > 999999) {
            System.out.printf("Unable to parse int date %s", intDate);
            return null;
        }

        int intYear = intDate / 100;
        int intMonth = intDate % 100;

        Calendar result = new GregorianCalendar();
        result.set(Calendar.YEAR, intYear);
        result.set(Calendar.MONTH, intMonth - 1);
        result.set(Calendar.DAY_OF_MONTH, 1);

        return result.getTime();
    }

    private String toString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    @Test
    public void test_999999() {
//        assertEquals("3999-03-01", toString(convertIntToDate(999999)));
        assertEquals("3999-03-01", toString(convertIntToDate(201599)));
    }

    @Test
    public void test_399903() {
        assertEquals("3999-03-01", toString(convertIntToDate(399903)));
    }
}

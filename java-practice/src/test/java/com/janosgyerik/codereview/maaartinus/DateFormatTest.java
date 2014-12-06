package com.janosgyerik.codereview.maaartinus;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

class DateFormat {
    private static class SimpleDateFormatHolder {
        private final String format;
        private final Locale locale;
        private final SimpleDateFormat simpleDateFormat;

        private SimpleDateFormatHolder(String format, Locale locale) {
            this.format = format;
            this.locale = locale;
            this.simpleDateFormat = new SimpleDateFormat(format, locale);
        }

        private boolean matches(String format, Locale locale) {
            return this.format == format && this.locale == locale;
        }
    }

    public String toString(Date obj, String format, Locale locale) {
        return getSimpleDateFormat(format, locale).format(obj);
    }

    private SimpleDateFormat getSimpleDateFormat(String format, Locale locale) {
        SimpleDateFormatHolder holder = threadLocal.get();
        if (holder != null && holder.matches(format, locale)) {
            return holder.simpleDateFormat;
        }
        SimpleDateFormatHolder newHolder = new SimpleDateFormatHolder(format, locale);
        threadLocal.set(newHolder);
        return newHolder.simpleDateFormat;
    }

    private SimpleDateFormat getSimpleDateFormat2(String format, Locale locale) {
        Object[] list = SDF.get();
        // An identity check is faster and correct as missing an entry is allowed.
        // It's surely good enough as passing equals but not same parameters hardly ever happens.
        if (list!=null && list[0] == format && list[1] == locale) {
            return (SimpleDateFormat) list[2];
        }
        final SimpleDateFormat result = new SimpleDateFormat(format, locale);
        list = new Object[] {format, locale, result};
        SDF.set(list);
        return result;
    }

    /** Contains triples (String format, Locale locale, SimpleDateFormat simpleDateFormat) */
    private static final ThreadLocal<Object[]> SDF = new ThreadLocal<Object[]>();

    private static final ThreadLocal<SimpleDateFormatHolder> threadLocal = new ThreadLocal<>();
}

public class DateFormatTest {
    @Test
    public void test() {
        DateFormat format = new DateFormat();
        assertEquals("20140921", format.toString(new Date(), "yyyyMMdd", Locale.FRANCE));
        assertEquals("20140921", format.toString(new Date(), "yyyyMMdd", Locale.FRANCE));
        assertEquals("20140921", format.toString(new Date(), "yyyyMMdd", Locale.FRANCE));
        assertEquals("20140921", format.toString(new Date(), "yyyyMMdd", Locale.FRANCE));
        assertEquals("20140921", format.toString(new Date(), "yyyyMMdd", Locale.FRANCE));
    }

}

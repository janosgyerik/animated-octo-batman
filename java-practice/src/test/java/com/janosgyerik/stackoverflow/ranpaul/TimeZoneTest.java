package com.janosgyerik.stackoverflow.ranpaul;

import org.junit.Test;

import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

public class TimeZoneTest {
//    protected TimeZone getTimeZone() {
//        TimeZone preferredTimeZone = getPreferredTimeZone();
//        if (preferredTimeZone != null) {
//            return preferredTimeZone;
//        }
//        return defaultTimeZone();
//    }
//
//    private TimeZone getPreferredTimeZone() {
//        IUserPreferences preferences = userProfileService.getCurrentUserPreferences();
//        if (preferences != null) {
//            String timeZoneText = preferences.getPreferredTimeZone();
//            if (timeZoneText != null && !timeZoneText.isEmpty()) {
//                return TimeZone.getTimeZone(timeZoneText);
//            }
//        }
//        return null;
//    }
//
//    public TimeZone defaultTimeZone() {
//        Locale defaultLocale = new Locale(appConfiguration.getDefaultLocale());
//        Calendar calendar = Calendar.getInstance(defaultLocale);
//        return calendar.getTimeZone();
//    }

    @Test
    public void testTimeZone_NonExistentWontThrow() {
        TimeZone timeZone = TimeZone.getTimeZone("nonexistent");
        System.out.println(timeZone.getDisplayName());
    }
}

package com.janosgyerik.codereview.emanon;

import org.joda.time.DateTime;
import org.joda.time.MutableDateTime;
import org.joda.time.ReadWritableDateTime;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class WorkDateUtils {
	/**
	 *
	 * @param year
	 * @param dayOfWeek day of week, for example 1 = Monday
	 * @return
	 */
	public static DateTime getFirstWorkDateOfYear(int year, int dayOfWeek) {
		ReadWritableDateTime readWritableDateTime = new MutableDateTime(year, 1, 1, 0, 0, 0, 0);
		while (readWritableDateTime.getDayOfWeek() != dayOfWeek) {
			readWritableDateTime.addDays(1);
		}
		return readWritableDateTime.toDateTime();
	}
}

public class WorkDateUtilsTest {
	@Test
	public void test2014() {
		assertEquals(new DateTime(2014, 1, 6, 0, 0), WorkDateUtils.getFirstWorkDateOfYear(2014, 1));
		assertEquals(new DateTime(2014, 1, 5, 0, 0), WorkDateUtils.getFirstWorkDateOfYear(2014, 7));
	}

	@Test
	public void test2014_WithLoop() {
		DateTime start = new DateTime(2014, 1, 6, 0, 0);
		for (int i = 0; i < 1000; ++i) {
			System.out.println(start.plusDays(i));
			assertEquals(start.plusDays((0 + i) % 7), WorkDateUtils.getFirstWorkDateOfYear(2014, 1 + i));
		}
	}
}

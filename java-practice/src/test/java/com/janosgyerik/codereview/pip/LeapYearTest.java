package com.janosgyerik.codereview.pip;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LeapYearTest {
	/**
	 * Returns if the specified year is a leap year
	 */
	private boolean isLeapYear(int year) {
		return year > 1582 && year % 4 == 0 && (year % 100 != 0 || year % 400 == 0);
//		// No leap years occured before 1582 because that is when they were added to the calendar
//		if (year > 1582) {
//			// All code past here only executed when year mod 4 is true
//			if (year % 4 != 0) {
//				return false;
//			}
//
//			// Makes sure that it does not register the beginning of centuries as leap years
//			// unless they actually are.
//			return year % 100 != 0 || year % 400 == 0;
//		}
//		return false;
	}

	@Test
	public void test_NotLeapYear_IfNotDivisibleBy4() {
		assertFalse(isLeapYear(2003));
	}

	@Test
	public void test_LeapYear_Loop() {
		for (int year = 2000; year < 3000; ++year) {
			boolean result = isLeapYear(year);
			if (year % 400 == 0) {
				assertTrue(result);
			} else if (year % 100 == 0) {
				assertFalse(result);
			} else if (year % 4 == 0) {
				assertTrue(result);
			} else {
				assertFalse(result);
			}
		}
	}

	@Test
	public void test_LeapYear_IfDivisibleBy_4_Not_100_400() {
		assertTrue(isLeapYear(2004));
		assertFalse(isLeapYear(2100));
	}

	@Test
	public void test_NotLeapYear_IfDivisibleBy_4_100_Not_400() {
		assertTrue(isLeapYear(2000));
		assertFalse(isLeapYear(2100));
		assertFalse(isLeapYear(2200));
		assertFalse(isLeapYear(2300));
		assertTrue(isLeapYear(2400));
	}

	@Test
	public void test_LeapYear_IfDivisibleBy_4_100_400() {
		assertTrue(isLeapYear(2000));
		assertTrue(isLeapYear(2400));
		assertTrue(isLeapYear(2800));
	}

	@Test
	public void test_NotLeapYear_Before1582_EvenIfDivisibleBy_4_100_400() {
		assertFalse(isLeapYear(1000));
		assertFalse(isLeapYear(1400));
	}

	@Test
	public void test_NotLeapYear_Before1582() {
		assertFalse(isLeapYear(1004));
		assertFalse(isLeapYear(1404));
		assertFalse(isLeapYear(1405));
	}
}

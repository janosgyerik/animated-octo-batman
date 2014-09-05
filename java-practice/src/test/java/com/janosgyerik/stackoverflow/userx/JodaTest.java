package com.janosgyerik.stackoverflow.userx;

import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

class JodaDemo {

	static String sumTimes(String time1, String time2) {
		PeriodFormatter formatter = new PeriodFormatterBuilder()
				.minimumPrintedDigits(2)
				.printZeroAlways()
				.appendHours()
				.appendLiteral(":")
				.appendMinutes()
				.toFormatter();

		Period period1 = formatter.parsePeriod(time1);
		Period period2 = formatter.parsePeriod(time2);
		return formatter.print(period1.plus(period2));
	}

	static String sumTimes2(String time1, String time2) {
		String output;
		long start = System.currentTimeMillis();

		if (time1.equals("00:00") && time2.equals("00:00")) {
			output = "00:00";
		} else if (time1.equals("00:00")) {
			output = time2;
		} else if (time2.equals("00:00")) {
			output = time1;
		} else {
			boolean sign_time1 = false;
			boolean sign_time2 = false;
			String[] time1_out, time2_out;

			boolean negative = false;
			String output_split[];

			if (time1.contains("-")) {
				time1_out = time1.split(":");
				time1_out[0] = time1_out[0].replace("-", "");
				time1_out[1] = time1_out[1].replace("-", "");
				time1 = time1_out[0] + ':' + time1_out[1];
				sign_time1 = true;
			}
			if (time1.contains("-")) {
				time1 = time1.replaceAll("-", "");
				sign_time1 = true;
			}

			if (time2.contains("-")) {
				time2_out = time2.split(":");
				time2_out[0] = time2_out[0].replace("-", "");
				time2_out[1] = time2_out[1].replace("-", "");
				time2 = time2_out[0] + ':' + time2_out[1];
				sign_time2 = true;
			}

			PeriodFormatterBuilder builder = new PeriodFormatterBuilder();
			builder.minimumPrintedDigits(2);
			builder.printZeroAlways();
			builder.appendHours();
			builder.appendLiteral(":");
			builder.appendMinutes();
			PeriodFormatter pf = builder.toFormatter();

			Period period1 = pf.parsePeriod(time1);
			Period period2 = pf.parsePeriod(time2);
			Period normalized;
			Period total = null;


			if (sign_time1 && sign_time2) {
				total = period1.plus(period2);
				negative = true;
			}

			if (sign_time1 && !sign_time2) {
				total = period2.minus(period1);
			}

			if (!sign_time1 && sign_time2) {
				total = period1.minus(period2);
			}

			if (!sign_time1 && !sign_time2) {
				total = period1.plus(period2);
				negative = false;
			}

			normalized = total.normalizedStandard(PeriodType.time());
			output_split = pf.print(normalized).split(":");

			if (output_split[1].contains("-")) {
				output = output_split[0] + ":" + output_split[1].replace("-", "");
			} else {
				output = (negative ? "-" : "") + output_split[0] + ":" + output_split[1];
			}
		}

		long end = System.currentTimeMillis();

		System.out.println("exec time sum1: " + (end - start) + " ms");
		return output;
	}
}

public class JodaTest {
	@Test
	public void testSum8Plus8() {
		String time1 = "08:00";
		String time2 = "08:00";
		assertEquals("16:00", JodaDemo.sumTimes(time1, time2));
	}

	@Test
	public void testSum8Plus18() {
		String time1 = "08:00";
		String time2 = "18:00";
		assertEquals("26:00", JodaDemo.sumTimes(time1, time2));
	}

	@Test
	public void testSum8Minus18() {
		String time1 = "08:00";
		String time2 = "-18:00";
		assertEquals("-10:00", JodaDemo.sumTimes(time1, time2));
	}
}

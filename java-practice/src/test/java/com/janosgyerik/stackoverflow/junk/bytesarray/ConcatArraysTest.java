package com.janosgyerik.stackoverflow.junk.bytesarray;

import javafx.util.Pair;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ConcatArraysTest {
	byte[] concatArrays(byte[]... arrays) {
		int totalLength = 0;
		for (byte[] arr : arrays) {
			totalLength += arr.length;
		}
		byte[] result = new byte[totalLength];
		int start = 0;
		for (byte[] arr : arrays) {
			System.arraycopy(arr, 0, result, start, arr.length);
			start += arr.length;
		}
		return result;
	}

	@Test
	public void test() {
		byte b1 = 0x1;
		byte b2 = 0x2;
		byte b3 = (byte) 0x100003;
		assertEquals(3, b3);
		byte[] arr = new byte[]{0x3, 0x5, 0x4};
		assertArrayEquals(new byte[]{1, 2, 3, 5, 4}, concatArrays(new byte[]{b1, b2}, arr));
	}

	enum Members {
		A(1), B(2), C(3);

		private final int size;

		Members(int size) {
			this.size = size;
		}
	}

	enum Direction {
		NORTH("North", 10),
		EAST("East", 15),
		SOUTH("South", 1),
		WEST("West", 3);

		private final String label;
		private final int magic;

		Direction(String label, int magic) {
			this.label = label;
			this.magic = magic;
		}
	}
	static class Person {
		private final String name;
		Person(String name) {
			this.name = name;
		}
	}
	static class EmployeeDatabase {
		private static final Person CEO = new Person("Jack");
	}

	@Test
	public void test3() {
//		assertEquals(123, Character.MAX_VALUE);
		assertEquals('@', "@ABCD".charAt(0));
	}

	@Test
	public void test4() {
		Map<String, List<Double>> map = new HashMap<>();
		map.put("jack", Arrays.asList(1d, 2d, 3d));
		map.put("mike", Arrays.asList(1d, 32d, 3d));
		Map<String, Double> result =
				map.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().stream().max(Double::compare).get()));
		Pair<String, Double> result2 = result
				.entrySet().stream().max(Map.Entry.comparingByValue(Double::compare))
				.map(e -> new Pair<String, Double>(e.getKey(), e.getValue())).get();
		assertEquals(3d, Arrays.asList(1d, 3d, 2d).stream().max(Double::compare).get(), .0000001);
		assertEquals("mike=32.0", result2.toString());
	}

//	@Test
	public void test2() {
		System.out.println("Centimeters|Inches");
		System.out.println("-----------+-----------");
		DecimalFormat df = new DecimalFormat("#.##");
		for (double c = 1; c <= 45; c += 0.5) {
			double d = (c * 0.381);
			System.out.println(String.format("%-11s|   %s", c, df.format(d)));
//			System.out.println(c+ "     |   "+df.format(d));
		}
	}
}

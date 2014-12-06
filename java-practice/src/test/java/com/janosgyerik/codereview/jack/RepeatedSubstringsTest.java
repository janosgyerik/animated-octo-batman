package com.janosgyerik.codereview.jack;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class RepeatedSubstringsTest {
	public Map<String, Integer> findOccurences(String str, int length) {
		Map<String, Integer> map = new HashMap<>();
		int limit = str.length() - length + 1;
		for (int i = 0; i < limit; i++) {
			String sub = str.substring(i, i + length);
			Integer counter = map.get(sub);
			if (counter == null) {
				counter = 0;
			}
			map.put(sub, ++counter);
		}
		return map;
	}

	@Test
	public void test() {
		Map<String, Integer> map = new HashMap<>();
		map.put("his", 2);
		map.put("Thi", 2);
		map.put(" Th", 1);
		map.put("s T", 1);
		map.put("is ", 2);
		assertEquals(map, findOccurences("This This ", 3));
	}
}

package com.janosgyerik.codereview.jack;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringCompressionTest2 {

	public String compress(String str) {
		if (str.isEmpty()) {
			return "";
		}

		char[] chars = str.toCharArray();
		StringBuilder builder = new StringBuilder();

		int count = 1;
		char prev = chars[0];
		for (int i = 1; i < chars.length; i++) {
			char current = chars[i];
			if (current == prev) {
				count++;
			} else {
				builder.append(count).append(prev);
				count = 1;
			}
			prev = current;
		}
		return builder.append(count).append(prev).toString();
	}

	@Test
	public void test_aabcccccaaa() {
		assertEquals("2a1b5c3a", compress("aabcccccaaa"));
	}

	@Test
	public void test_a5() {
		assertEquals("5a", compress("aaaaa"));
	}

	@Test
	public void test_empty() {
		assertEquals("", compress(""));
	}

	@Test
	public void test_a() {
		assertEquals("1a", compress("a"));
	}

	@Test
	public void test_a3b4() {
		assertEquals("3a4b", compress("aaabbbb"));
	}

	@Test
	public void test_abc() {
		assertEquals("1a1b1c", compress("abc"));
	}

	@Test
	public void test_wwwggopp() {
		assertEquals("3w2g1o2p", compress("wwwggopp"));
	}
}

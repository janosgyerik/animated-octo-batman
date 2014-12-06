package com.janosgyerik.codereview.scratch;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

public class CategorizerTest {
	public static String getCategory(Collection obj) {
		return "Collection";
	}

	public static String getCategory(String string) {
		return "String";
	}

	public static String getCategory(Object obj) {
		return "Object";
	}

	@Test
	public void testString() {
		assertEquals("String", getCategory("hello"));
	}

	@Test
	public void testCollection() {
		assertEquals("Collection", getCategory(Arrays.asList(3, 4)));
	}

	@Test
	public void testObject() {
		assertEquals("Object", getCategory(3));
	}

	@Test
	public void testMix() {
		Object[] objs = {"hello", 3, Arrays.asList(1, 2, 3)};
		for (Object o : objs) {
			assertEquals("Object", getCategory(o));
		}
	}
}

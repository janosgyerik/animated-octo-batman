package com.janosgyerik.codereview.arunprakash;

import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.assertEquals;

class ReverseWords {

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.println("Enter the string");
			Scanner scanner = new Scanner(System.in);
			System.out.println(reverseWords(scanner.nextLine()));
		} else {
			for (String arg : args) {
				System.out.println(reverseWords(arg));
			}
		}
	}

	public static String reverseWords(String sentence) {
		String[] parts = sentence.trim().split("\\s+");

		StringBuilder builder = new StringBuilder();
		builder.append(parts[parts.length - 1]);

		for (int i = parts.length - 2; i >= 0; --i) {
			builder.append(" ").append(parts[i]);
		}

		return builder.toString();
	}

}


public class ReverseWordsTest {
	@Test
	public void testEmptyString() {
		assertEquals("", ReverseWords.reverseWords(""));
	}

	@Test
	public void testWithSingleWord() {
		assertEquals("awesome", ReverseWords.reverseWords("awesome"));
	}

	@Test
	public void testWithTwoWords() {
		assertEquals("is awesome", ReverseWords.reverseWords("awesome is"));
	}

	@Test
	public void testWithSentence() {
		assertEquals("awesome is codereview", ReverseWords.reverseWords("codereview is awesome"));
	}

	@Test
	public void testWithTrailingSpaces() {
		assertEquals("awesome is codereview", ReverseWords.reverseWords("codereview is awesome  "));
	}

	@Test
	public void testWithLeadingSpaces() {
		assertEquals("awesome is codereview", ReverseWords.reverseWords("  codereview is awesome"));
	}

	@Test
	public void testWithMultipleSpaces() {
		assertEquals("awesome is codereview", ReverseWords.reverseWords("codereview   is awesome"));
	}

	@Test
	public void test() {
		System.out.println("Enter the string");
		Scanner scanner = new Scanner("this is awesome");
		System.out.println(ReverseWords.reverseWords(scanner.nextLine()));
	}
}

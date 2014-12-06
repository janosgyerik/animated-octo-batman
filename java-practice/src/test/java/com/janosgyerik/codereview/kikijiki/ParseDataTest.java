package com.janosgyerik.codereview.kikijiki;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class Uti {
	public static float safeParseFloat(String s) {
		return Float.parseFloat(s);
	}
}

interface Parser {
	void setData(String data);

	float parseFloat();
}

class ParserData implements Parser {
	String s;
	int cur = 0;
	int len = 0;

	float prev_x = .0f;
	float prev_y = .0f;

	float prev_cx = .0f;
	float prev_cy = .0f;

	float start_x = .0f;
	float start_y = .0f;

	char prev_curve;
	char next_curve = 0;

	public void setData(String data) {
		s = data;
		len = s.length();

		cur = 0;
		prev_x = 0;
		prev_y = 0;

		prev_cx = .0f;
		prev_cy = .0f;

		start_x = .0f;
		start_y = .0f;

		prev_curve = 0;
		next_curve = 0;
	}

	public boolean hasNext() {
		return cur < len;
	}

	public char getNext() {
		return s.charAt(cur++);
	}

	public char peekNext() {
		return s.charAt(cur);
	}

	public void skipSpaces() {
		while (hasNext()) {
			char c = s.charAt(cur);

			switch (c) {
				case ' ':
				case ',':
				case '\n':
				case '\t':
				case '\r':
					break;

				default:
					return;
			}

			cur++;
		}
	}

	public float parseFloat() {
		boolean exp = false;
		boolean last = false;

		skipSpaces();

		int j = cur;

		if (s.charAt(j) == '-') {
			getNext();
		}

		while (hasNext() && !last) {
			char c = getNext();

			switch (c) {
				case '0':
				case '1':
				case '2':
				case '3':
				case '4':
				case '5':
				case '6':
				case '7':
				case '8':
				case '9':
				case '+':
				case '.':
					exp = false;
					break;

				case '-':
					if (exp) {
						exp = false;
					} else {
						cur--;
						return Uti.safeParseFloat(s.substring(j, cur));
					}
					break;

				case 'e':
				case 'E':
					exp = true;
					break;

				default:
					cur--;
					return Uti.safeParseFloat(s.substring(j, cur));
			}
		}

		return Uti.safeParseFloat(s.substring(j, cur));
	}
}

class MyParser implements Parser {

	private String data;
	private Scanner scanner;

	@Override
	public void setData(String data) {
		this.data = data;
		scanner = new Scanner(data);
	}

	@Override
	public float parseFloat() {
		return scanner.nextFloat();
	}
}

class RegexSplitParser implements Parser {

	private String data;

	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public float parseFloat() {
		return 0;
	}
}

class CustomParser {

	public static List<Float> parse(String data) {
		String[] parts = data.replaceAll("[^\\d.-]|(?=-)", " ").trim().split(" +");
		List<Float> numbers = new ArrayList<>(parts.length);
		for (String s : parts) {
			numbers.add(Float.parseFloat(s));
		}
		return numbers;
	}
}

public class ParseDataTest {
	private final String SAMPLE = "\n" +
			"M245.792,247.275-0.828,1.036,0.794,2.333,0.924,3.53c0.161,1.442,0.122,2.924,0.016,4.377c-0.109,1.469-0.365,2.93-0.626,4.382\n" +
			"    c-0.353,1.946-0.965,3.814-1.914,,,,garbage5.563c-0.365,0.67-0.647,1.397-1.086,2.019c-0.804,1.14-1.705,2.212-2.548,3.326\n" +
			"    c-1.484,1.967-3.566,3.112-5.648,4.304c-1.356,0.778-2.707,1.553-4.194,1.98c-2.577,0.742-5.196,1.243-7.904,0.672\n" +
			"    c-1.02-0.213-2.065-0.286-3.099-0.44c-2.901-0.436-4.463-2.366-5.446-4.917c-0.84-2.179-0.598-4.317,0.067-6.526\n" +
			"    c0.899-2.974,2.905-5.095,4.931-7.278c1.564-1.688,3.005-3.498,4.384-5.339c1.188-1.581,0.951-2.972-0.513-4.263\n" +
			"    c-1.453-0.238-1.456-0.238-2.139,1.176c-1.119,2.31-2.463,4.513-4.409,6.169c-2.983,2.536-6.378,4.271-10.45,4.197\n" +
			"    c-3.49-0.062-6.985-0.234-10.473-0.174c-1.821,0.03-3.679,0.295-5.442,0.754c-1.948,0.508-3.091,2.061-3.743,3.915\n" +
			"    c-0.615,1.764-0.973,3.642-1.755,5.324c-1.615,3.472-3.705,6.573-7.654,7.836c-0.857,0.273-1.614,0.861-2.471,1.143\n" +
			"    c-2.482,0.814-5.011,0.832-7.585,0.362c-1.683-0.308-3.407-0.384-4.915-1.309c-1.85-1.138-3.589-2.405-4.922-4.163\n" +
			"    c-0.35-0.46-0.777-0.859-1.171-1.287";

	//	@Test
	public void test() {
		ParserData parser = new ParserData();
		parser.setData(SAMPLE);
		assertEquals(245.79200744628906, parser.parseFloat(), .0001);
		assertEquals(247.274, parser.parseFloat(), .001);
	}

	@Test
	public void testScanner() {
		String PATTERN = "([^\\d.-]+)|(?=-)";
		String[] parts = SAMPLE.replaceAll(PATTERN, " ").trim().split(" +");
		assertEquals("245.792", parts[0]);
		assertEquals("247.275", parts[1]);
		assertEquals("-0.828", parts[2]);
		assertEquals("1.036", parts[3]);
		assertEquals("0.794", parts[4]);
		assertEquals("-1.914", parts[24]);
		assertEquals("3.814", parts[23]);
		assertEquals("5.563", parts[25]);
		assertEquals("-0.365", parts[26]);
		assertEquals("0.67", parts[27]);
		assertEquals(0.34, Float.parseFloat(".34"), .001);
		assertEquals(-.34, Float.parseFloat("-.34"), .001);
		assertEquals(Arrays.asList(), CustomParser.parse(SAMPLE));
	}
}

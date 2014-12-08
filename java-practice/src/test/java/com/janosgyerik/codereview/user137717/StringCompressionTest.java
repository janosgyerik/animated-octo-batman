package com.janosgyerik.codereview.user137717;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class RunLength {
    public static String compress(String str) {
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
                builder.append(prev).append(count);
                count = 1;
            }
            prev = current;
        }
        return builder.append(prev).append(count).toString();
    }
}

public class StringCompressionTest {

    private String compress(String input) {
        return RunLength.compress(input);
    }

    //	public static void mainx(String[] args){
    //		StringCompression test = new StringCompression();
    //
    //		test.compress("aabcccccaaa");
    //		test.compress("aaaaa");
    //		test.compress("aaaabbb");
    //		test.compress("aaabbbccc");
    //		test.compress("abc");
    //		test.compress("a");
    //		test.compress("");
    //	}

    @Test
    public void test_aabcccccaaa() {
        assertEquals("a2b1c5a3", compress("aabcccccaaa"));
    }

    @Test
    public void test_a5() {
        assertEquals("a5", compress("aaaaa"));
    }

    @Test
    public void test_empty() {
        assertEquals("", compress(""));
    }

    @Test
    public void test_a() {
        assertEquals("a1", compress("a"));
    }

    @Test
    public void test_a3b4() {
        assertEquals("a3b4", compress("aaabbbb"));
    }

    @Test
    public void test_abc() {
        assertEquals("a1b1c1", compress("abc"));
    }
}

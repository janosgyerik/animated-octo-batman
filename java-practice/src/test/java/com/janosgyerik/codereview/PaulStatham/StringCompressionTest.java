package com.janosgyerik.codereview.PaulStatham;

import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertEquals;

class CompressString {

    public static String compressString(String s) {
        Map<Character, Integer> charCountMap = createCharacterCountMap(s);
        StringBuilder result = new StringBuilder();

        for (Map.Entry<Character, Integer> entry : charCountMap.entrySet()) {
            Character c = entry.getKey();
            int val = entry.getValue();
            result.append(c);
            if (val > 1) {
                result.append(val);
            }
        }
        return result.toString();
    }

    private static LinkedHashMap<Character, Integer> createCharacterCountMap(String s) {
        char[] charArray = s.toCharArray();
        int[] charCountArray = new int[128];
        LinkedHashMap<Character, Integer> charCountMap = new LinkedHashMap<Character, Integer>();
        for (int i = 0; i < charArray.length; i++) {
            int intVal = charArray[i];
            charCountArray[intVal]++;
            charCountMap.put(charArray[i], charCountArray[intVal]);
        }
        return charCountMap;
    }
}

class RunLength {
    static String compress2(final String raw) {
        // check edge cases.
        if (raw == null || raw.length() < 1) {
            return raw;
        }

        final StringBuilder sb = new StringBuilder();
        char prevChar = raw.charAt(0);
        int charCount = 1;
        for (int i = 1; i < raw.length(); i++) {
            final char c = raw.charAt(i);
            if (prevChar != c || i == raw.length() - 1) {
                sb.append(prevChar);
                if (charCount != 1) {
                    sb.append(charCount);
                }
                charCount = 1;
                prevChar = c;
            } else {
                charCount++;
            }
        }
        return sb.toString();
    }

    public static String compress(String input) {
        if (input.length() < 2) {
            return input;
        }

        char[] chars = input.toCharArray();
        StringBuilder builder = new StringBuilder();

        int count = 1;
        char prev = chars[0];
        for (int i = 1; i < chars.length; ++i) {
            char current = chars[i];
            if (current == prev) {
                ++count;
            } else {
                builder.append(prev);
                if (count > 1) {
                    builder.append(count);
                }
                count = 1;
            }
            prev = current;
        }
        builder.append(prev);
        if (count > 1) {
            builder.append(count);
        }
        return builder.toString();
    }
}

public class StringCompressionTest {

    private String compress(String input) {
                return CompressString.compressString(input);
//        return RunLength.compress(input);
    }

//    @Test
    public void test_aabcccccaaa() {
        assertEquals("a2bc5a3", compress("aabcccccaaa"));
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
        assertEquals("a", compress("a"));
    }

    @Test
    public void test_a3b4() {
        assertEquals("a3b4", compress("aaabbbb"));
    }

    @Test
    public void test_abc() {
        assertEquals("abc", compress("abc"));
    }

//    @Test
    public void test_aabaaa() {
        assertEquals("a2ba3", compress("aabaaa"));
    }

    @Test
    public void test_aabccccddddddde() {
        assertEquals("a2bc4d7e", compress("aabccccddddddde"));
    }
}

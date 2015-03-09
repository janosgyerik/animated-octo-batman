package com.janosgyerik.codereview.Legato;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

class MToLast {
    static char retrieveAndPrintMToLast0(String line) {
        String sanitized = line.replaceAll("\\s", "");
        if (!sanitized.isEmpty()) {
            int limit = sanitized.length() - 1;
            int targetIndex = Character.getNumericValue(sanitized.charAt(limit));
            sanitized = sanitized.substring(0, limit);

            if (targetIndex <= limit) {
                return sanitized.charAt(limit - targetIndex);
            }
        }
        return 0;
    }

    static char retrieveAndPrintMToLast(String line) {
        int limit = (line.length() - 1) / 2;
        int indexAfterLastSpace = line.lastIndexOf(' ') + 1;
        int targetIndex = Integer.parseInt(line.substring(indexAfterLastSpace));

        if (targetIndex <= limit) {
            return line.charAt(indexAfterLastSpace - 2 * targetIndex);
        }
        return 0;
    }
}

public class MToLastTest {
    private char getMthToLast(String line) {
        return MToLast.retrieveAndPrintMToLast(line);
    }

    @Test
    public void test_hello_1() {
        assertEquals('o', getMthToLast("h e l l o 1"));
    }

    @Test
    public void test_hello_5() {
        assertEquals('h', getMthToLast("h e l l o 5"));
    }

    @Test
    public void test_hello_9() {
        assertEquals(0, getMthToLast("h e l l o 9"));
    }

    @Test
    public void test_helloworld_10() {
        assertEquals('h', getMthToLast("h e l l o w o r l d 10"));
    }

    @Test
    public void test_helloworld_11() {
        assertEquals(0, getMthToLast("h e l l o w o r l d 11"));
    }

    @Test
    public void test_1_2_3_4_2() {
        assertEquals('3', getMthToLast("1 2 3 4 2"));
    }

    @Test
    public void test_empty_1() {
        assertEquals(0, getMthToLast("1"));
    }
}

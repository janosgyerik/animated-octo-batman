package com.janosgyerik.stackoverflow.riteshbhakre;

import org.junit.Test;

import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class RemoveCharsTest {

    private String strip(String first, String second) {
        return Pattern.compile("[" + second + "]",
                Pattern.CASE_INSENSITIVE).matcher(first).replaceAll("");
    }

    @Test
    public void test() {
        assertEquals("Jhe", strip("John Lennon", "Paul ON"));
    }
}

package com.janosgyerik.codereview.randomuser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParseStringTest {

    // dest = comp ; jump
    // D = X
    // X ; Y
    // D = X ; Y
    private List<String> parseInput(String input) {
        final String comp;
        final String dest;
        final String jump;

        String[] partsWithEquals = input.split("\\s+=\\s+");
        String secondPart;
        if (partsWithEquals.length > 1) {
            dest = partsWithEquals[0];
            secondPart = partsWithEquals[1];
        } else {
            dest = null;
            secondPart = partsWithEquals[0];
        }

        String[] parts = secondPart.split("\\s+;\\s+");
        if (parts.length > 1) {
            comp = parts[0];
            jump = parts[1];
        } else {
            comp = parts[0];
            jump = null;
        }

        return Arrays.asList(dest, comp, jump);
    }

    @Test
    public void test1() {
        assertEquals(Arrays.asList("D", "X", "Y"), parseInput("D = X ; Y"));
        assertEquals(Arrays.asList(null, "X", "Y"), parseInput("X ; Y"));
        assertEquals(Arrays.asList("D", "X", null), parseInput("D = X"));
    }
}

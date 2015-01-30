package com.janosgyerik.codereview.scratch;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AsciiTest {
    @Test
    public void test() {
        assertEquals(65, (int) 'A');
        assertEquals(97, (int) 'a');
        assertEquals(48, (int) '0');
    }
}

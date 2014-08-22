package com.janosgyerik.stackoverflow.junk;

import org.junit.Assert;
import org.junit.Test;

public class StringBuilderWithByteArray {
    @Test
    public void testAppendByteArray() {
        String sample = "some string";
        StringBuilder builder = new StringBuilder();
        builder.append(sample.getBytes());
        Assert.assertFalse(sample.equals(builder.toString()));
    }

    @Test
    public void testAppendByteArrayConverted() {
        String sample = "some string";
        StringBuilder builder = new StringBuilder();
        builder.append(new String(sample.getBytes()));
        Assert.assertTrue(sample.equals(builder.toString()));
    }
}

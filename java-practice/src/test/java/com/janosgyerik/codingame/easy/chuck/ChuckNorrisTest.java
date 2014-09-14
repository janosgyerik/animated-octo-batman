package com.janosgyerik.codingame.easy.chuck;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChuckNorrisTest {

    @Test
    public void testC() {
        Assert.assertEquals("0 0 00 0000 0 00", Solution.encodeChuckNorrisSequence("C"));
    }

    @Test
    public void testCC() {
        Assert.assertEquals("0 0 00 0000 0 000 00 0000 0 00", Solution.encodeChuckNorrisSequence("CC"));
    }
}

package com.janosgyerik.codereview.ambigram_maker;

import org.junit.Test;

import java.io.IOException;

public class UnZipperTest {
    @Test
    public void test() throws IOException {
        UnZipper.unzip("/tmp/test.zip", "/tmp/out");
    }
}

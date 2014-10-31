package com.janosgyerik.stackoverflow.benchmark;

import microbench.api.BenchmarkRunner;
import microbench.api.annotation.MeasureTime;

public class SimpleBenchmarkDemo {
    public static void main(String[] args) {
        BenchmarkRunner.run(new SimpleBenchmarkDemo());
    }

    @MeasureTime
    public void test1() {

    }
}

package com.janosgyerik.benchmark;

import com.janosgyerik.microbench.api.BenchmarkRunner;
import com.janosgyerik.microbench.api.annotation.Benchmark;
import com.janosgyerik.microbench.api.annotation.MeasureTime;

@Benchmark(iterations = 5)
public class ExampleBenchmark {
    public static void main(String[] args) {
        new BenchmarkRunner(new ExampleBenchmark()).run();
    }

    @MeasureTime
    public void bubbleSort() {
        System.out.println("just a demo");
    }
}

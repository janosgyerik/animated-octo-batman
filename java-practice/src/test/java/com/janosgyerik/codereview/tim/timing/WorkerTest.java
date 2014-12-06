package com.janosgyerik.codereview.tim.timing;

import org.junit.Test;

import java.util.function.IntFunction;

public class WorkerTest {
    @Test
    public void test() {
        Timing timing = new Timing();
        IntFunction<String> intToString = (int i) -> String.valueOf(i) + "test";
        IntFunction<Integer> intToInt = (int i) -> i;

        // time function string->string:
        timing.add(WorkerTest::functionToTimeString, intToString, "s + s");
        timing.add(WorkerTest::functionToTimeString2, intToString, "s + s + s");
        // [...]

        // we can also time int->int functions at the same time:
        timing.add(WorkerTest::functionToTimeInt, intToInt, "i + i");

        timing.time();

        // output to stdo
        timing.output(System.out::println, Timing.Sort.DESC);
    }

    private static String functionToTimeString(String s) {
        int sum = 0;
        for (int i = 0; i < 1000000000; ++i) {
            for (int j = 0; j < 1000000000; ++j) {
                sum += i;
            }
        }
        return s + s;
    }

    private static String functionToTimeString2(String s) {
        return s + s + s;
    }

    private static int functionToTimeInt(int i) {
        return i + i;
    }
}

package com.janosgyerik.stackoverflow.user2773985;

import com.janosgyerik.microbench.api.annotation.Benchmark;
import com.janosgyerik.microbench.api.annotation.MeasureTime;
import com.janosgyerik.microbench.api.annotation.Validate;
import com.janosgyerik.microbench.api.BenchmarkRunner;

import java.util.Arrays;
import java.util.List;

@Benchmark(iterations = 10, warmUpIterations = 10)
public class ThreeSumBench {
    private final int[] num = new int[1000];

    private final List<List<Integer>> expected;
    private List<List<Integer>> result;

    ThreeSumBench() {
//        Random random = new Random(0);
//        for (int i = 0; i < num.length; ++i) {
//            num[i] = random.nextInt(100) - 50;
//        }
//        Arrays.sort(num);
        for (int i = 0; i < num.length; ++i) {
            num[i] = (i - num.length / 2) / 2;
        }
        System.out.println("---pt1---");
        System.out.println(Arrays.toString(num));
        System.out.println("---pt2---");
//        System.exit(1);
        expected = ThreeSumOrig.threeSum(num);
    }

    public static void main(String[] args) {
        new BenchmarkRunner(new ThreeSumBench()).run();
    }

    @Validate
    public void validate() {
        if (!expected.equals(result)) {
            System.out.println("---pt3---");
            System.out.println(expected);
            System.out.println("---pt4---");
            System.out.println(result);
            System.out.println("---pt5---");
            System.out.println(expected.size() + " : " + result.size());
            System.out.println(expected.get(0) + " : " + result.get(0));
            System.out.println(expected.get(expected.size() - 1) + " : " + result.get(result.size() - 1));
            System.out.println(expected.get(expected.size() - 2) + " : " + result.get(result.size() - 2));
            for (int i = 0; i < result.size(); ++i) {
                if (!result.get(i).equals(expected.get(i))) {
                    System.out.println("i=" + i + " " + result.get(i) + " : " + expected.get(i));
                    break;
                }
            }
            System.exit(1);
            throw new IllegalStateException("Result is not equal to what was expected");
        }
    }

    @MeasureTime
    public void runOrig() {
        result = ThreeSumOrig.threeSum(num);
    }

    @MeasureTime
    public void runNew() {
        result = ThreeSumNew.threeSum(num);
    }
}

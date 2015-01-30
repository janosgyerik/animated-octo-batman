package com.janosgyerik.codereview.malachi;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class FizzBuzz {
    private final SortedSet<Fizzer> fizzers;

    private static class Fizzer implements Comparable<Fizzer> {
        private final String name;
        private final int divisor;

        private Fizzer(String name, int divisor) {
            this.name = name;
            this.divisor = divisor;
        }

        @Override
        public int compareTo(Fizzer o) {
            return Integer.compare(divisor, o.divisor);
        }
    }

    public static class Builder {
        private final List<Fizzer> fizzers = new ArrayList<>();

        public Builder add(String name, int divisor) {
            fizzers.add(new Fizzer(name, divisor));
            return this;
        }

        public FizzBuzz build() {
            return new FizzBuzz(this);
        }
    }

    private FizzBuzz(Builder builder) {
        fizzers = new TreeSet<>(builder.fizzers);
    }

    public String getValue(int num) {
        String output = "";
        for (Fizzer fizzer : fizzers) {
            if (num % fizzer.divisor == 0) {
                output += fizzer.name;
            }
        }
        return output.isEmpty() ? Integer.toString(num) : output;
    }

    public static Builder builder() {
        return new Builder();
    }
    public void calc(int num) {

    }
    public void calc(long num) {

    }
    public void misc() {
        int num1 = 3;
        long num2 = 4;
        calc(num2);
    }
}

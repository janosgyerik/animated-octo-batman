package com.janosgyerik.codereview.malachi;

import org.junit.Test;

import static java.util.stream.IntStream.rangeClosed;

class FizzBuzzDemo {
    public static void main(String[] args) {
        FizzBuzz fizzBuzz = FizzBuzz.builder().add("Fizz", 3).build();
        rangeClosed(1, 100).mapToObj(fizzBuzz::getValue).forEach(System.out::println);
    }
}

public class FizzBuzzTest {
    @Test
    public void test() {
        FizzBuzzDemo.main(null);
    }
}

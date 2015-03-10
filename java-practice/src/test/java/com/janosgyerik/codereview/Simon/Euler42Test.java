package com.janosgyerik.codereview.Simon;

import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.StreamSupport;

class ProjEuler42 {

    public static int wordValue(String str) {
        return str.chars().map(ch -> ch - 'A' + 1).sum();
    }

    public static boolean isTriangular(int number) {
        // The Nth triangular number, T(n), is n*(n+1)/2,
        // by doing T(n) - number = 0 we get a quadratic equation.
        // If the solution to this equation is an integer, it is a triangular number
        double n = Math.round(-0.5 + Math.sqrt(0.25 + 2 * number));
        return n * (n + 1) / 2 == number;
    }

    public static void main() throws IOException {

        URL url = new URL("https://projecteuler.net/project/resources/p042_words.txt");
        InputStream in = url.openStream();

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(in, StandardCharsets.UTF_8))) {
            long count = reader.lines()
                    .flatMap(line -> Arrays.stream(line.split("\\W+")))
                    .skip(1)
                    .filter(str -> isTriangular(wordValue(str)))
                    .peek(System.out::println)
                    .mapToInt(ProjEuler42::wordValue)
                    .peek(System.out::println)
                    .count();
            System.out.println("There are a total of " + count + " triangular words");
        }
    }
}

public class Euler42Test {
    @Test
    public void test() throws IOException {
        ProjEuler42.main();
    }
}

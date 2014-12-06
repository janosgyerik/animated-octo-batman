package com.janosgyerik.codereview.niese;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class BrutePermutationGenerator {

    private Random random;

    BrutePermutationGenerator(Random random) {
        this.random = random;
    }

    public int[] getRandomPermutation() {
        int[] array = new int[10];

        for (int count = 0; count < array.length; ) {
            boolean fill = true;
            int r = random.nextInt(10) + 1;

            for (int item : array) {
                if (item == r) {
                    fill = false;
                }
            }

            if (fill) {
                array[count] = r;
                count++;
            }
        }

        return array;
    }

    public String nextPermutation() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            builder.append(Arrays.toString(getRandomPermutation())).append("\n");
        }
        return builder.toString();
    }
}

class SmartPermutationGenerator {
    private Random random = new Random();
    private int size;

    public SmartPermutationGenerator(Random random) {
        this.random = random;
        this.size = 10;
    }

    public List<Integer> getRandomPermutation() {
        List<Integer> unused = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            unused.add(i + 1);
        }

        List<Integer> permutation = new ArrayList<>();
        for (int k = 0; k < size; k++) {
            int pos = random.nextInt(unused.size());
            permutation.add(unused.get(pos));
            unused.remove(pos);
        }

        return permutation;
    }

    public String nextPermutation() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            builder.append(getRandomPermutation()).append("\n");
        }
        return builder.toString();
    }
}

public class BrutePermutationGeneratorTest {
    @Test
    public void testExample() {
        BrutePermutationGenerator brute = new BrutePermutationGenerator(new Random(0));
        SmartPermutationGenerator smart = new SmartPermutationGenerator(new Random(0));

        System.out.println("\n" + "Random arrays using Brute Force: ");
        assertEquals("[1, 9, 10, 8, 6, 4, 2, 5, 3, 7]\n" +
                "[3, 10, 8, 7, 9, 4, 6, 2, 1, 5]\n" +
                "[7, 4, 8, 3, 6, 2, 10, 9, 1, 5]\n" +
                "[1, 6, 7, 5, 3, 9, 4, 10, 2, 8]\n" +
                "[2, 4, 8, 9, 7, 6, 1, 10, 3, 5]\n" +
                "[4, 10, 3, 1, 6, 2, 5, 9, 7, 8]\n" +
                "[1, 3, 4, 5, 7, 6, 9, 8, 10, 2]\n" +
                "[8, 5, 10, 3, 1, 9, 6, 2, 7, 4]\n" +
                "[3, 6, 9, 2, 7, 4, 1, 10, 8, 5]\n" +
                "[2, 6, 1, 5, 8, 4, 7, 3, 9, 10]\n", brute.nextPermutation());

        System.out.println("\n" + "Random arrays using Smart Force: ");
        assertEquals("[1, 9, 3, 5, 10, 7, 6, 2, 8, 4]\n" +
                "[8, 10, 4, 3, 9, 7, 6, 1, 5, 2]\n" +
                "[4, 1, 3, 8, 6, 2, 5, 9, 10, 7]\n" +
                "[6, 3, 1, 2, 7, 8, 5, 10, 9, 4]\n" +
                "[1, 9, 5, 2, 8, 4, 7, 6, 10, 3]\n" +
                "[10, 4, 6, 8, 5, 2, 1, 9, 3, 7]\n" +
                "[8, 9, 6, 10, 1, 4, 2, 7, 3, 5]\n" +
                "[9, 5, 1, 6, 4, 2, 7, 3, 10, 8]\n" +
                "[8, 7, 9, 2, 5, 4, 1, 6, 3, 10]\n" +
                "[4, 9, 1, 5, 6, 8, 10, 2, 7, 3]\n", smart.nextPermutation());

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(list);
        // [1, 2, 3, 4, 5]
        int[] arr = {1, 2, 3, 4, 5};
        System.out.println(Arrays.toString(arr));
        // [1, 2, 3, 4, 5]
    }

    int[] getRandomPermutation(int num, Random random) {

        int[] result = new int[num];
        for (int i = 0; i < num; ++i) {
            result[i] = i + 1;
        }
        return result;
    }
}

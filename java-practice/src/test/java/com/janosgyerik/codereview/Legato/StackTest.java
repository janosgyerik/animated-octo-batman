package com.janosgyerik.codereview.Legato;

import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.assertEquals;

interface Stack<T> {

    void push(T item);

    T pop();
}

class StackImpl<T> implements Stack<T> {

    LinkedList<T> list = new LinkedList<>();

    @Override
    public void push(T item) {
        list.add(item);
    }

    @Override
    public T pop() {
        return list.removeLast();
    }
}

class Solution {

    //    public static void main(String[] args) throws FileNotFoundException {
    //        Scanner input = new Scanner(new File(args[0]));
    //
    //        while (input.hasNextLine()) {
    //            System.out.println(retrieveAlternates(input.nextLine().split("\\s+")));
    //        }
    //    }

    static String retrieveAlternates(String[] input) {
//        Stack<Integer> stack = new StackImpl<>();
        StringBuilder result = new StringBuilder();

//        for (String s : input) {
//            stack.push(Integer.parseInt(s));
//        }

        for (int i = 0; i < input.length; i++) {
            if ((i & 1) == 0) {
                //                result.append(' ').append(stack.pop());
                result.append(' ').append(input[input.length - i - 1]);
            } else {
                //                stack.pop();
            }
        }

        return result.substring(1);
    }
}

public class StackTest {
    void assertAlternates(String expected, String input) {
        assertEquals(expected, Solution.retrieveAlternates(input.split("\\s+")));
    }

    @Test
    public void test_1_2_3_4_gives_4_2() {
        assertAlternates("4 2", "1 2 3 4");
    }

    @Test
    public void test_10_m2_3_4_gives_4_m2() {
        assertAlternates("4 -2", "10 -2 3 4");
    }

    @Test
    public void test_1_gives_1() {
        assertAlternates("1", "1");
    }

    @Test
    public void test_1_2_gives_2() {
        assertAlternates("2", "1 2");
    }
}

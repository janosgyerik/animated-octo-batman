package com.janosgyerik.codereview.randomuser;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProblemFourTest {

    private final ProblemFour p = new ProblemFour();

    @Test
    public void test_50_2_1_9_to_95021() {
        assertEquals("95021", p.largestNumberFromList(Arrays.asList(50, 2, 1, 9)));
    }

    @Test
    public void test_50_5_56_to_56550() {
        assertEquals("56550", p.largestNumberFromList(Arrays.asList(50, 5, 56)));
    }

    @Test
    public void test_99_2_20_20_29_91_1_9_to_9999129220201() {
        assertEquals("9999129220201", p.largestNumberFromList(Arrays.asList(99, 2, 20, 20, 29, 91, 1, 9)));
    }

}


class ProblemFour {

    public String largestNumberFromList(List<Integer> input) {

        return getStringFromList(sortIntegerList(input));
    }

    ;

    private String getStringFromList(List<Integer> input) {
        String s = "";
        for (int i = 0; i < input.size(); i++) {
            s += input.get(i);
        }

        return s;

    }

    private List<Integer> sortIntegerList(List<Integer> input) {

        for (int i = 0; i < input.size(); i++) {

            for (int j = i + 1; j < input.size(); j++) {

                int a = input.get(i);
                int b = input.get(j);

                if (b == getLargestLexiographcal(a, b)) {
                    input.set(i, b);
                    input.set(j, a);
                }
            }

        }
        ;

        return input;

    }

    int getLargestLexiographcal(int a, int b) {

        String first = "" + a;
        String second = "" + b;

        for (int i = 0; i < Math.max(first.length(), second.length()); i++) {

            int firstVal = Integer.valueOf(first.charAt(Math.min(i, first.length() - 1)));
            int secondVal = Integer.valueOf(second.charAt(Math.min(i, second.length() - 1)));

            if (firstVal != secondVal) {
                if (firstVal > secondVal) {
                    return Integer.valueOf(first);
                } else {
                    return Integer.valueOf(second);
                }
            }
        }

        return 0;

    }

}
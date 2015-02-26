package com.janosgyerik.codereview.randomuser;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class NzecTest {

    @Test
    public void test() {
        Scanner sc = new Scanner("2\n" +
                "1 3 5 7 8 -1\n" +
                "12 23 16 0 2 -1");
        int testcase = Integer.parseInt(sc.nextLine());

        //        while (testcase-- > 0) {
        //            long num;
        //            long largest = Long.MIN_VALUE;
        //            long secondLargest = Long.MIN_VALUE;
        //            while ((num = sc.nextLong()) != -1) {
        //                if (num > largest) {
        //                    secondLargest = largest;
        //                    largest = num;
        //                } else if (num > secondLargest) {
        //                    secondLargest = num;
        //                }
        //            }
        //            System.out.println(secondLargest);
        //        }

        while (testcase-- > 0) {
            List<Long> numbers = new ArrayList<>();
            long num;
            while ((num = sc.nextLong()) != -1) {
                numbers.add(num);
            }
            Collections.sort(numbers);
            if (numbers.size() == 0) {
                System.err.println("");
            } else if (numbers.size() == 1) {
                System.out.println(numbers.get(0));
            } else {
                System.out.println(numbers.get(numbers.size() - 2));
            }
        }
    }

}

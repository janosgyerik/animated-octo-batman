package com.janosgyerik.practice.so;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestCollatzSequenceTest {
    int findLongestStart(int maxStart) {
        int longest = 8;
        int longestStart = 3;
        Map<Integer, Integer> knownLengths = new HashMap<Integer, Integer>();
        for (int start = 4; start < maxStart; ++start) {
            int length = countCollatzSequence(start, knownLengths);
            if (length > longest) {
                longestStart = start;
                longest = length;
            }
        }
        return longestStart;
    }

    private int countCollatzSequence(int start, Map<Integer, Integer> knownLengths) {
        int count = 1;
        int n = start;
        List<Integer> nums = new ArrayList<Integer>();
        while (n != 1) {
            if (knownLengths.containsKey(n)) {
                count += knownLengths.get(n) - 1;
                break;
            }
            nums.add(n);
            ++count;
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
        }
        int result = count;
        for (Integer num : nums) {
            knownLengths.put(num, count--);
        }
        return result;
    }

    private void printCollatzSequence(int start) {
        int n = start;
        while (n != 1) {
            System.out.print(n + " -> ");
            if (n % 2 == 0) {
                n /= 2;
            } else {
                n = 3 * n + 1;
            }
        }
        System.out.println("1");
    }

    @Test
    public void testCountCollatzSequence() {
        Map<Integer, Integer> knownLengths = new HashMap<Integer, Integer>();
        Assert.assertEquals(8, countCollatzSequence(3, knownLengths));
        Assert.assertEquals(10, countCollatzSequence(13, knownLengths));
        Assert.assertEquals(18, countCollatzSequence(14, knownLengths));
        Assert.assertEquals(18, countCollatzSequence(15, knownLengths));
        Assert.assertEquals(13, countCollatzSequence(17, knownLengths));
        Assert.assertEquals(21, countCollatzSequence(19, knownLengths));
        Assert.assertEquals(20, countCollatzSequence(58, knownLengths));
        Assert.assertEquals(19, countCollatzSequence(29, knownLengths));
        Assert.assertEquals(18, countCollatzSequence(88, knownLengths));
        Assert.assertEquals(17, countCollatzSequence(44, knownLengths));
        Assert.assertEquals(16, countCollatzSequence(22, knownLengths));
        Assert.assertEquals(15, countCollatzSequence(11, knownLengths));
        Assert.assertEquals(34, countCollatzSequence(119, knownLengths));
    }

    @Test
    public void testLongestCollatzSequence() {
        Assert.assertEquals(6171, findLongestStart(10000));
        Assert.assertEquals(77031, findLongestStart(100000));
        Assert.assertEquals(106239, findLongestStart(110000));
//        Assert.assertEquals(106239, findLongestStart(120000));
        Assert.assertEquals(9, findLongestStart(13));
        Assert.assertEquals(9, findLongestStart(14));
        Assert.assertEquals(9, findLongestStart(15));
    }
}

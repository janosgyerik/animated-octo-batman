package com.janosgyerik.practice.codility.so;

import org.junit.Assert;
import org.junit.Test;

public class FindMinCoinsTest {
    private int findMinCoins(int[] coins, int sum, int index, int count) {
        if (sum == 0) {
            return count;
        }
        if (index == coins.length) {
            return 0;
        }
        if (sum < 0) {
            return 0;
        }
        int countUsingIndex = findMinCoins(coins, sum - coins[index], index, count + 1);
        int countWithoutUsingIndex = findMinCoins(coins, sum, index + 1, count);
        if (countUsingIndex == 0) {
            return countWithoutUsingIndex;
        }
        if (countWithoutUsingIndex == 0) {
            return countUsingIndex;
        }
        return Math.min(countUsingIndex, countWithoutUsingIndex);
    }

    private int findMinCoins2(int[] coins, int sum) {
        return findMinCoins(coins, sum, 0, 0);
    }

    private int findMinCoins(final int[] coins, final int sum) {
        int[] calculationsCache = new int[sum + 1];
        for (int i = 0; i <= sum; i++) {
            calculationsCache[i] = Integer.MAX_VALUE-1;
        }
        calculationsCache[0] = 0;
        for (int i = 1; i <= sum; i++) {
            for (int coin : coins) {
                if (i >= coin && calculationsCache[i - coin] + 1 < calculationsCache[i]) {
                    calculationsCache[i] = calculationsCache[i - coin] + 1;
                }
            }
        }
        return calculationsCache[sum];
    }

    @Test
    public void testExample() {
        Assert.assertEquals(2, findMinCoins(new int[]{1, 2, 5}, 10));
        Assert.assertEquals(3, findMinCoins(new int[]{1, 2, 5}, 11));
        Assert.assertEquals(2, findMinCoins(new int[]{1, 2, 5}, 4));
        Assert.assertEquals(2, findMinCoins(new int[]{1, 2, 5}, 7));
        Assert.assertEquals(3, findMinCoins(new int[]{1, 2, 5}, 8));
        Assert.assertEquals(3, findMinCoins(new int[]{1, 2, 5}, 9));
        Assert.assertEquals(3, findMinCoins(new int[]{1, 5, 2}, 9));
        Assert.assertEquals(3, findMinCoins(new int[]{5, 1, 2}, 9));
        Assert.assertEquals(3, findMinCoins(new int[]{5, 2, 1}, 9));
        Assert.assertEquals(2, findMinCoins(new int[]{1, 2, 5}, 3));
        Assert.assertEquals(2, findMinCoins(new int[]{2, 5}, 7));
        Assert.assertEquals(3, findMinCoins(new int[]{5, 7}, 15));
    }
}

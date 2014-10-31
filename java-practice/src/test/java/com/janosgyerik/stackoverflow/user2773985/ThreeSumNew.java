package com.janosgyerik.stackoverflow.user2773985;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ThreeSumNew {
    public static int[] maxTwoDups(int[] nums) {
        List<Integer> maxTwoDups = new ArrayList<>(nums.length);
        int prev1 = nums[0];
        maxTwoDups.add(prev1);
        int prev2 = nums[1];
        maxTwoDups.add(prev2);
        for (int i = 2; i < nums.length; ++i) {
            if (nums[i] != prev1) {
                maxTwoDups.add(nums[i]);
                prev1 = prev2;
                prev2 = nums[i];
            } else if (nums[i] == 0) {
                maxTwoDups.add(0);
                while (i < nums.length && nums[i] == 0) {
                    ++i;
                }
            }
        }
        int[] result = new int[maxTwoDups.size()];
        for (int i = 0; i < maxTwoDups.size(); ++i) {
            result[i] = maxTwoDups.get(i);
        }
        return result;
    }

    public static List<List<Integer>> threeSum(int[] origNum) {
        if (origNum.length < 3) {
            return Collections.emptyList();
        }

        Arrays.sort(origNum);
        int[] num = maxTwoDups(origNum);

        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < num.length && num[i] <= 0; i++) {
            for (int j = i + 1; j < num.length; j++) {
                int ab = num[i] + num[j];
                if (ab > 0 && num[j] > 0) {
                    break;
                }
                for (int k = j + 1; k < num.length; k++) {
                    if (ab + num[k] == 0) {
                        res.add(Arrays.asList(num[i], num[j], num[k]));
                    }
                    if (k + 1 < num.length && num[k] == num[k + 1]) {
                        k++;
                    }
                }
                while (j + 1 < num.length && num[j] == num[j + 1]) {
                    j++;
                }
            }
            if (i + 1 < num.length && num[i] == num[i + 1]) {
                i++;
            }
        }
        return res;
    }
}

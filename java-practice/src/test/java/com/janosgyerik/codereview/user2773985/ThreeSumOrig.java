package com.janosgyerik.codereview.user2773985;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumOrig {
    public static List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();

        if (num.length < 3)
            return res;

        Arrays.sort(num);

        for (int i = 0; i < num.length; i++) {
            if (num[i] > 0)
                break;
            for (int j = i + 1; j < num.length; j++) {
                if (num[i] + num[j] > 0 && num[j] > 0)
                    break;
                for (int k = j + 1; k < num.length; k++) {
                    if (num[i] + num[j] + num[k] == 0) {
                        List<Integer> curr = new ArrayList<Integer>();
                        curr.add(num[i]);
                        curr.add(num[j]);
                        curr.add(num[k]);
                        res.add(curr);
                    }
                    while (k + 1 < num.length && num[k] == num[k + 1])
                        k++;
                }
                while (j + 1 < num.length && num[j] == num[j + 1])
                    j++;
            }
            while (i + 1 < num.length && num[i] == num[i + 1])
                i++;
        }
        return res;
    }
}

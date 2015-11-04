package com.janosgyerik.practice.oj.leetcode.medium.ContainerWithMostWater;

import java.util.*;

public class Solution {
    public int maxArea(int[] height) {
        int length = height.length;

        if (length < 2) {
            return 0;
        }

        SortedMap<Integer, Integer> firstPosMap = new TreeMap<>();
        firstPosMap.put(height[0], 0);

        int maxArea = 0;
        for (int pos = 1; pos < height.length; ++pos) {
            Integer firstPos = firstPosMap.get(height[pos]);
            if (firstPos == null) {
                List<Integer> list = new ArrayList<>(firstPosMap.keySet());
                int index = Collections.binarySearch(list, height[pos]);
                int insertionPoint = -(index + 1);
                if (insertionPoint <= 0) {
                    firstPos = pos;
                } else {
                    firstPos = firstPosMap.get(list.get(insertionPoint - 1));
                }
                firstPosMap.put(height[pos], pos);
            }
            int area = (pos - firstPos) * height[firstPos];
            if (area > maxArea) {
                maxArea = area;
            }
        }
        return maxArea;
    }
}

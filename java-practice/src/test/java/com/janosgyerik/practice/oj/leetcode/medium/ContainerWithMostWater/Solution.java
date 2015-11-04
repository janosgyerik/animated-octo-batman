package com.janosgyerik.practice.oj.leetcode.medium.ContainerWithMostWater;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class Solution {
    public int maxArea(int[] height) {
        TreeMap<Integer, Integer> firstPosMap = new TreeMap<>();
        int maxArea = 0;
        for (int pos = 0; pos < height.length; ++pos) {
            Integer firstPos = firstPosMap.get(height[pos]);
            if (firstPos == null) {
                if (!firstPosMap.isEmpty()) {
                    List<Integer> list = new ArrayList<>(firstPosMap.keySet());
                    int index = Collections.binarySearch(list, height[pos]);
                    int insertionPoint = -(index + 1);
                    if (insertionPoint <= 0) {
                        firstPos = pos;
                    } else {
                        firstPos = firstPosMap.get(list.get(insertionPoint - 1));
                    }
                } else {
                    firstPos = pos;
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

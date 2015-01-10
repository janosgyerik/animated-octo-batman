package com.janosgyerik.codingame.medium.worstloss;

import java.util.Scanner;

class Solution {
    static int solution(int[] curve) {
        int worstLoss = 0;
        int high = curve[0];
        int low = curve[0];
        for (int i = 1; i < curve.length; ++i) {
            int current = curve[i];
            if (current > high) {
                high = current;
            } else if (current <= low) {
                low = current;
                int possibleWorstLoss = low - high;
                if (possibleWorstLoss < worstLoss) {
                    worstLoss = possibleWorstLoss;
                }
            }
        }
        return worstLoss;
    }

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        in.nextLine();
        String vs = in.nextLine();

        String[] strValues = vs.split(" ");
        int[] curve = new int[strValues.length];
        for (int i = 0; i < strValues.length; ++i) {
            curve[i] = Integer.parseInt(strValues[i]);
        }

        System.out.println(solution(curve));
    }
}

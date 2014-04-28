import java.util.*;
import java.io.*;
import java.math.*;

class Player {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);

        int bikes = in.nextInt();
        int minToSurvive = in.nextInt();
        in.nextLine();
        char[][] road = new char[4][];
        for (int i = 0; i < road.length; ++i) {
            String line = in.nextLine();
            road[i] = line.toCharArray();
        }
        
        while (true) {
            // Read information from standard input
            int speed = in.nextInt();
            int[] x = new int[bikes];
            int[] y = new int[bikes];
            int[] states = new int[bikes];
            for (int i = 0; i < bikes; ++i) {
                x[i] = in.nextInt();
                y[i] = in.nextInt();
                states[i] = in.nextInt();
            }
            
            // System.err.println(String.format("speed=%s x=%s", speed, x));

            String action = computeAction(minToSurvive, road, speed, x, y, states);

            // Write action to standard output
            System.out.println(action);
        }
    }
    private static final char ROAD = '.';
    private static final char GAP = '0';

    private static class GapInfo {
        private final int start;
        private final int size;
        private final int lane;

        public GapInfo(int start, int size, int lane) {
            this.start = start;
            this.size = size;
            this.lane = lane;
        }
        public String toString() {
            return String.format("start=%s size=%s lane=%s", start, size, lane);
        }
    }
    private static String computeAction(int minToSurvive, char[][] road, int speed, int[] allX, int[] y, int[] states) {
        int x = getSurvivedX(allX, states);
        System.err.println(String.format("x=%s speed=%s", x, speed));
        GapInfo gapInfo = nextMostSignificantGap(road, x);
        System.err.println(gapInfo);
        int minSpeed = gapInfo.size + 1;
        if (speed < minSpeed || x > gapInfo.start) {
            return "SPEED";
        }
        if (x + speed >= gapInfo.start + gapInfo.size) {
            return "JUMP";
        }
        if (shouldSlowDown(x, speed, gapInfo)) {
            return "SLOW";
        }
        if (x + speed + 1 < gapInfo.start) {
            return "SPEED";
        }
        return "WAIT";
    }
    private static boolean shouldSlowDown(int x, int speed, GapInfo gapInfo) {
        int i = x;
        for (; i < gapInfo.start && speed > gapInfo.size + 1; --speed, i += speed);
        return i < gapInfo.start && i + speed >= gapInfo.start + gapInfo.size;
    }
    private static GapInfo nextMostSignificantGap(char[][] road, int x) {
        for (int i = x + 1; i < road[0].length; ++i) {
            for (int lane = 0; lane < road.length; ++lane) {
                if (road[lane][i] == GAP) {
                    return new GapInfo(i, gapSize(road, lane, i), lane);
                }
            }
        }
        return new GapInfo(0, 0, 0);
    }

    private static int gapSize(char[][] road, int lane, int start) {
        int length = 1;
        for (int i = start + length; i < road[lane].length && road[lane][i] == GAP; ++i, ++length);
        return length;
    }
    private static int getSurvivedX(int[] x, int[] states) {
        for (int i = 0; i < states.length; ++i) {
            if (states[i] == 1) {
                return x[i];
            }
        }
        return 0;
    }
    
}
#!/bin/bash
# Run this script to easily test your solution
test=1

# Uncomment these next lines to test other inputs!
test=2
test=3  # fail
test=4  # pass
test=5  # mission fail, too many losses
test=6  # mission fail, wrong hole pos
test=7  # pass
test=8  # mission fail, too many losses
test=9  # fail, should move up/down
test=10 # fail, should move up/down
# test=11
# test=12

cat in$test.txt

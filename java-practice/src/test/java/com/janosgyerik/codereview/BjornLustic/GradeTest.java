package com.janosgyerik.codereview.BjornLustic;

import org.junit.Test;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import static org.junit.Assert.assertEquals;

class Grades {

    private static final Map<Integer, String> limits;

    static {
        limits = new TreeMap<>();
        limits.put(50, "B");
        limits.put(90, "B+");
        limits.put(92, "A-");
        limits.put(96, "A");
        limits.put(101, "A+");
    }

    public static int promptScore() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("What is the percentage of the student: ");
            int x = scanner.nextInt();

            if (0 <= x && x <= 100) {
                return x;
            }
            System.out.println("Please put in a percent from 0 - 100!\n");
        }
    }

    public static String getGrade(int score) {
        for (Map.Entry<Integer, String> entry : limits.entrySet()) {
            int limit = entry.getKey();
            if (score < limit) {
                return entry.getValue();
            }
        }
        throw new AssertionError("Impossible case");
    }

    public static void main(String[] args) {
        System.out.println(getGrade(promptScore()));
    }
}

public class GradeTest {
    @Test
    public void testB() {
        assertEquals("B", Grades.getGrade(0));
        assertEquals("B", Grades.getGrade(49));
    }

    @Test
    public void testB_Plus() {
        assertEquals("B+", Grades.getGrade(50));
        assertEquals("B+", Grades.getGrade(89));
    }

    @Test
    public void testA_Minus() {
        assertEquals("A-", Grades.getGrade(90));
        assertEquals("A-", Grades.getGrade(91));
    }

    @Test
    public void testA() {
        assertEquals("A", Grades.getGrade(92));
        assertEquals("A", Grades.getGrade(95));
    }

    @Test
    public void testA_Plus() {
        assertEquals("A+", Grades.getGrade(96));
        assertEquals("A+", Grades.getGrade(100));
    }
}

package com.janosgyerik.stackoverflow;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.assertEquals;

public class AnswerSession20141206 {
    @Test
    public void test() {
        HashMap<Integer, List<Integer>> testData = new HashMap<>();
        testData.put(1, Arrays.asList(777));

        HashMap<Integer, List<Integer>> testData2 = new HashMap<>();
        for (Map.Entry<Integer, List<Integer>> entry : testData.entrySet()) {
            testData2.put(entry.getKey(), new ArrayList<>(entry.getValue()));
        }
        testData2.get(1).add(888);
        System.out.println(testData);
        System.out.println(testData2);
    }

    private String pattern(int num) {
        if (num == 1) {
            return "1";
        }
        String prev = pattern(num - 1);
        return prev + " " + num + " " + prev;
    }

    @Test
    public void test2() {
        assertEquals("1", pattern(1));
        assertEquals("1 2 1", pattern(2));
        assertEquals("1 2 1 3 1 2 1", pattern(3));
        assertEquals("1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 6 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 7 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 6 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1 5 1 2 1 3 1 2 1 4 1 2 1 3 1 2 1", pattern(7));
    }

    static void doTestByteFiles() throws IOException {
        File file = new File("sample1.data");
        try (FileOutputStream outStream = new FileOutputStream(file)) {

            byte[] outByteArray = {10, 20, 30, 40, 50, 60, 70, 80, (byte) 'J', (byte) 'a', (byte) 'v', (byte) 'a'};

            outStream.write(outByteArray);
        }

        try (FileInputStream inStream = new FileInputStream(file)) {
            int fileSize = (int) file.length();
            byte[] inByteArray = new byte[fileSize];
            inStream.read(inByteArray);

            for (int i = 0; i < fileSize; i++) {
                System.out.println((char) inByteArray[i]);
            }
        }
    }

    @Test
    public void testLargestNum() {
        int aCounter=0;
        int bCounter=0;
        int cCounter=0;
        int largest = Math.max(aCounter, Math.max(bCounter, cCounter));
    }

    @Test
    public void testMultiCriteriaSort() {
        String s1 = "a1 5 2014-12-05";
        String s2 = "a2 10 2014-12-06";
        String s3 = "a3 5 2014-12-04";
        List<String> items = Arrays.asList(s1, s2, s3);
        Collections.sort(items, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                String[] parts1 = o1.split(" ");
                String[] parts2 = o2.split(" ");
                int compare;
                if ((compare = parts1[1].compareTo(parts2[1])) != 0) {
                    return compare;
                }
                return parts1[2].compareTo(parts2[2]);
            }
        });
        System.out.println(items);
    }
}


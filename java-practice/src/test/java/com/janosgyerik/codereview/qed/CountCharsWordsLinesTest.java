package com.janosgyerik.codereview.qed;

import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

import static org.junit.Assert.assertEquals;

class CountCharsWordsLines {
    public static long[] countOrig(String filename) throws IOException {
        long[] counts = new long[3];
        try(InputStream in = new FileInputStream(filename);
            Reader reader = new InputStreamReader(in, Charset.forName("UTF-8"))
        ) {
            int c;
            while((c = reader.read()) != -1) {
                char character = (char) c;
                if(character == '\n') {
                    counts[2]++;
                    counts[1]++;
                } else if(Character.isWhitespace(character)) {
                    counts[1]++;
                }
                counts[0]++;
            }
        }
        return counts;
    }

    private static class CountResult {
        private final long chars;
        private final long words;
        private final long lines;

        private CountResult(long chars, long words, long lines) {
            this.chars = chars;
            this.words = words;
            this.lines = lines;
        }
    }

    public static long[] countMine(String filename) throws IOException {
        long[] counts = new long[3];
        try(InputStream in = new FileInputStream(filename);
            BufferedReader reader = new BufferedReader(new InputStreamReader(in, Charset.forName("UTF-8")))
        ) {
            String line;
            long chars = 0;
            long words = 0;
            long lines = 0;
            while ((line = reader.readLine()) != null) {
                lines++;
                words += line.split("\\s+").length;
                chars += line.length() + 1;
            }
            counts[2] = lines;
            counts[1] = words;
            counts[0] = chars;
        }
        return counts;
    }

    public static long[] count(String filename) throws IOException {
        return countMine(filename);
    }
}

public class CountCharsWordsLinesTest {
    @Test
    public void test() throws IOException {
        long[] counts = CountCharsWordsLines.count("/tmp/test1/ls.out");
        System.out.printf("Chars: %d; Words: %d; Lines: %d;\n", counts[0], counts[1], counts[2]);
        assertEquals(124, counts[0]);
        assertEquals(13, counts[1]);
        assertEquals(8, counts[2]);
    }
}

package com.janosgyerik.stackoverflow.winterbash;

import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;


public class AnswerSession20141214 {
    @Test
    public void test1() {
        assertEquals("a%20b", "a b".replaceAll(" ", "%20"));
        assertEquals("a%20b", replace("a b"));
    }

    public String replace(String str) {
        String[] words = str.split(" ");
        StringBuilder sentence = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; ++i) {
            sentence.append("%20");
            sentence.append(words[i]);
        }

        return sentence.toString();
    }

    @Test
    public void test2() {
        assertEquals("12:34", "12:34:36".replaceAll(":[^:]+$", ""));
        String str = "12:34:56";
        assertEquals("12:34", str.substring(0, str.lastIndexOf(":")));
        String[] parts = str.split(":");
        assertEquals("12:34", String.format("%02d:%02d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
        str = "1:70:00";
        parts = str.split(":");
        assertEquals("01:70", String.format("%02d:%02d", Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
    }

    @Test
    public void testDate() throws ParseException {
        String input = "2014-12-12T10:39:40Z";
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        Date date = parser.parse(input);
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy H:mm:ss a");
        System.out.println(formatter.format(date));
    }

    @Test
    public void testDate2() throws ParseException {
        String input = "1:70:00";
        SimpleDateFormat parser = new SimpleDateFormat("HH:mm:ss");
        Date date = parser.parse(input);
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        System.out.println(formatter.format(date));
    }

}

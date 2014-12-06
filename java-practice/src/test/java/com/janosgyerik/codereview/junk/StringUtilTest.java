package com.janosgyerik.codereview.junk;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class StringUtility {

    /**
     * Split a string using a single char delimiter
     * @param strToSplit string to use
     * @param delimiter delimiter
     * @return String[] of portions
     */
    public static String[] split2(String strToSplit, char delimiter) {
        ArrayList<String> arr = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strToSplit.length(); i++) {
            char at = strToSplit.charAt(i);
            if (at == delimiter) {
                arr.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(at);
            }
        }
        arr.add(sb.toString());
        return arr.toArray(new String[arr.size()]);
    }

    public static String[] split(String strToSplit, char delimiter) {
        List<String> arr = new ArrayList<>();
        int foundPosition;
        int startIndex = 0;
        while ((foundPosition = strToSplit.indexOf(delimiter, startIndex)) > -1) {
            arr.add(strToSplit.substring(startIndex, foundPosition));
            startIndex = foundPosition + 1;
        }
        arr.add(strToSplit.substring(startIndex));
        return arr.toArray(new String[arr.size()]);
    }
}

public class StringUtilTest {

    private String join(char delim, String...parts) {
        StringBuilder builder = new StringBuilder();
        builder.append(parts[0]);
        for (int i = 1; i < parts.length; ++i) {
            builder.append(delim).append(parts[i]);
        }
        return builder.toString();
    }

    @Test
    public void testNormalSentence() {
        final String str1 = "Because";
        final String str2 = "I'm";
        final String str3 = "Batman";
        final char delim = ' ';
        String[] parts = StringUtility.split(str1 + delim + str2 + delim + str3, delim);
        Assert.assertEquals(Arrays.asList(str1, str2, str3), Arrays.asList(parts));
    }

    @Test
    public void testStartingWithDelim() {
        final String str1 = "";
        final String str2 = "I'm";
        final String str3 = "Batman";
        final char delim = ' ';
        String[] parts = StringUtility.split(str1 + delim + str2 + delim + str3, delim);
        Assert.assertEquals(Arrays.asList(str1, str2, str3), Arrays.asList(parts));
    }

    @Test
    public void testEmptyString() {
        final String str1 = "";
        final char delim = ' ';
        String[] parts = StringUtility.split(str1, delim);
        Assert.assertEquals(Arrays.asList(str1), Arrays.asList(parts));
    }

    @Test
    public void testOnlyDelim() {
        final String str1 = "";
        final char delim = ' ';
        String[] parts = StringUtility.split("" + delim + delim, delim);
        Assert.assertEquals(Arrays.asList(str1, str1, str1), Arrays.asList(parts));
    }

    @Test
    public void testNotContainingDelim() {
        final String str1 = "hello";
        final char delim = 'x';
        String[] parts = StringUtility.split(str1, delim);
        Assert.assertEquals(Arrays.asList(str1), Arrays.asList(parts));
    }
}

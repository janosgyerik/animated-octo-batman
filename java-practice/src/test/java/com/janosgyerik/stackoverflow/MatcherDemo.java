package com.janosgyerik.stackoverflow;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatcherDemo {
    public static void main(String[] args) {
        final String PATTERN = "(\\d+)\\s*<(\\w+)>\\s*([\\d\\s]+)\\s*</\\2>.*";
        String s = "53248 <CERCLE> 321 211 55 </CERCLE>";
        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(3).trim());
        }
    }
}

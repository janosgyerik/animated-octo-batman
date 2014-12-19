package com.janosgyerik.stackoverflow.winterbash;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class AnswerSession20141218 {
    @Test
    public void testSet() {
        Set<String> values = new HashSet<>();
        values.add("hello");
        values.add("there");
        assertEquals(values, new HashSet<>(Arrays.asList("hello", "there")));
        assertEquals(values, new HashSet<>(Arrays.asList(new String[]{"hello", "there"})));
        String[] v2 = new String[]{"hello", "there"};
        assertEquals(values, new HashSet<>(Arrays.asList(v2)));
    }

//    @Test
    public void testReadInputStream() throws IOException {
//        InputStream input = request.getInputStream();
        InputStream stream = new FileInputStream(new File("/tmp/date.out"));
        byte[] buffer = new byte[511];
        StringBuilder builder = new StringBuilder();
        while (stream.read(buffer) != -1) {
            builder.append(new String(buffer));
        }
        System.out.println(builder.toString());
        System.out.println(builder);
    }

    @Test
    public void testVarargs() {
        Example example = new Example(null);
        System.out.println(example);
    }
}

interface Strategy {
    boolean isEnabled(String state);
}

class ValueMatchingStrategy implements Strategy {

    private final Set<String> values;

    public ValueMatchingStrategy(String... values) {
        this.values = new HashSet<String>(Arrays.asList(values));
    }

    @Override
    public boolean isEnabled(String value) {
        return values.contains(value);
    }
}

class Example {
    private final Set<String> values;

    public Example(String... values) {
        this.values = new HashSet<String>(Arrays.asList(values));
    }

    @Override
    public String toString() {
        return values.toString();
    }
}

class StrategyTester {
    void method() {
        Strategy buttonOneStrategy = new ValueMatchingStrategy("hello", "there");
    }
}


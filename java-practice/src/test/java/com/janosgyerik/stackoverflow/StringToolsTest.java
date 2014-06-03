package com.janosgyerik.stackoverflow;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

// TODO move to java-examples
public class StringToolsTest {

    private String join(Iterable<?> items, String sep) {
        Iterator<?> iter = items.iterator();
        if (!iter.hasNext()) {
            return "";
        }

        StringBuilder builder = new StringBuilder();
        builder.append(iter.next());
        while (iter.hasNext()) {
            builder.append(sep).append(iter.next());
        }

        return builder.toString();
    }

    @Test
    public void testEmptyCollection() {
        Assert.assertTrue(join(Collections.emptyList(), ", ").isEmpty());
    }

    @Test
    public void testJoinSingleItem() {
        String item = "hello";
        Assert.assertEquals(item, join(Collections.singletonList(item), ", "));
    }

    @Test
    public void testJoinStrings() {
        Object item1 = "hello";
        Object item2 = "there";
        String sep = ", ";
        String expected = item1 + sep + item2;
        Assert.assertEquals(expected, join(Arrays.asList(item1, item2), sep));
    }

    @Test
    public void testJoinNumbers() {
        Integer item1 = 4;
        Integer item2 = 9;
        String sep = ", ";
        String expected = item1 + sep + item2;
        Assert.assertEquals(expected, join(Arrays.asList(item1, item2), sep));
    }

}

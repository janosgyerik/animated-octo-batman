package com.janosgyerik.stackoverflow;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class StringToolsTest {

    private String join(Collection<? extends Object> items, String sep) {
        if (items.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        Iterator<? extends Object> iter = items.iterator();
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
    public void testJoinTwoItems() {
        String item1 = "hello";
        String item2 = "hi";
        String sep = ", ";
        String expected = item1 + sep + item2;
        Assert.assertEquals(expected, join(Arrays.asList(item1, item2), sep));
    }

}

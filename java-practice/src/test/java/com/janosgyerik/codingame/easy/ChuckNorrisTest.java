package com.janosgyerik.codingame.easy;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ChuckNorrisTest {

    class SequenceItem {
        final int num;
        final int count;

        public SequenceItem(int num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public String encodeChuckNorrisSequence(String text) {
        List<SequenceItem> items = new ArrayList<SequenceItem>();
        boolean first = true;
        int prevNum = 0;
        int count = 0;
        for (int c : text.toCharArray()) {
            c = (c << 1) & 0xff;
            for (int i = 0; i < 7; ++i) {
                if (first) {
                    first = false;
                    prevNum = c & 0x80;
                    c = (c << 1) & 0xff;
                    count = 1;
                    continue;
                }
                int x = c & 0x80;
                c = (c << 1) & 0xff;
                if (x == prevNum) {
                    ++count;
                } else {
                    items.add(new SequenceItem(prevNum, count));
                    prevNum = x;
                    count = 1;
                }
            }
        }
        StringBuilder builder = new StringBuilder();
        for (SequenceItem item : items) {
            if (item.num == 0) {
                builder.append("00 ");
            } else {
                builder.append("0 ");
            }
            for (int i = 0; i < item.count; ++i) {
                builder.append("0");
            }
            builder.append(" ");
        }
        if (prevNum == 0) {
            builder.append("00 ");
        } else {
            builder.append("0 ");
        }
        for (int i = 0; i < count; ++i) {
            builder.append("0");
        }
        return builder.toString();
    }

    @Test
    public void testC() {
        Assert.assertEquals("0 0 00 0000 0 00", encodeChuckNorrisSequence("C"));
    }

    @Test
    public void testCC() {
        Assert.assertEquals("0 0 00 0000 0 000 00 0000 0 00", encodeChuckNorrisSequence("CC"));
    }
}

package com.janosgyerik.codility.so;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
    private final int start, end;

    public Range(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new RangeIterator();
    }

    // Inner class example
    private class RangeIterator implements
            Iterator<Integer> {
        private int cursor;

        public RangeIterator() {
            this.cursor = start;
        }

        public boolean hasNext() {
            return this.cursor < end;
        }

        public Integer next() {
            if (this.hasNext()) {
                return cursor++;
            }
            throw new NoSuchElementException();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }


    public static void main(String[] args) {
        Range range = new Range(1, 10);

        // Long way
//        Iterator<Integer> it = range.iterator();
//        while(it.hasNext()) {
//            int cur = it.next();
//            System.out.println(cur);
//        }

        // Shorter, nicer way:
        // Read ":" as "in"
        for (Integer cur : range) {
            System.out.println(cur);
        }
    }
}
package com.janosgyerik.codereview.junk.shuffle;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

class ShuffleGenerator {

    private final Random random;
    private final int size;

    ShuffleGenerator(int size, long seed) {
        this.random = new Random(seed);
        this.size = size;
    }

    Iterable<Integer> generate() {
        List<Integer> remaining = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            remaining.add(i);
        }

        return new Iterable<Integer>() {
            @Override
            public Iterator<Integer> iterator() {
                return new Iterator<Integer>() {
                    @Override
                    public boolean hasNext() {
                        return !remaining.isEmpty();
                    }

                    @Override
                    public Integer next() {
                        return remaining.remove(random.nextInt(remaining.size()));
                    }
                };
            }
        };
    }
}

public class ShuffleGeneratorTest {
    @Test
    public void testGen5() {
        ShuffleGenerator gen = new ShuffleGenerator(5, 0);
        Iterator<Integer> iter = gen.generate().iterator();
        assertEquals(0, (int) iter.next());
        assertEquals(4, (int) iter.next());
        assertEquals(2, (int) iter.next());
        assertEquals(3, (int) iter.next());
        assertEquals(1, (int) iter.next());
    }

    @Test
    public void testCannotIterateBeyond() {
        int size = 5;
        ShuffleGenerator gen = new ShuffleGenerator(size, 0);
        int i = 0;
        for (Integer item : gen.generate()) {
            assertNotNull(item);
            ++i;
        }
        assertEquals(size, i);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void testRemoveIter() {
        ShuffleGenerator gen = new ShuffleGenerator(5, 0);
        Iterator<Integer> iter = gen.generate().iterator();
        iter.remove();
    }

    @Test
    public void testReshuffle() {
        int size = 5;
        ShuffleGenerator gen = new ShuffleGenerator(size, 0);
        int i = 0;
        for (Integer item : gen.generate()) {
            assertNotNull(item);
            ++i;
        }
        assertEquals(size, i);
        i = 0;
        for (Integer item : gen.generate()) {
            assertNotNull(item);
            ++i;
        }
        assertEquals(size, i);
    }
}

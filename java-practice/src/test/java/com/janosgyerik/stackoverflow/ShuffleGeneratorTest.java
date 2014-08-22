package com.janosgyerik.stackoverflow;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ShuffleGeneratorTest {
    @Test
    public void testGen_2_5() {
        ShuffleGenerator gen = new ShuffleGenerator();
        gen.initialize(2, 5);
        assertEquals(3, gen.getCurrent());
        assertEquals(1, gen.getNext());
        assertEquals(4, gen.getNext());
        assertEquals(0, gen.getNext());
        assertEquals(2, gen.getNext());
    }
}

class ShuffleGenerator {

    private final List<Integer> indexList;
    private int currentListIndex;

    /**
     * Class constructor.
     */
    public ShuffleGenerator() {
        indexList = new ArrayList<Integer>();
        currentListIndex = 0;
    }

    /**
     * This will create a shuffled list with the indices from 0 to size-1.
     * When calling again, it will re-create a new shuffle list as long as the seed is different.
     *
     * @param seed for shuffling. Should be always different for re-initialization.
     * @param size the amount of indices for the shuffled list.
     */
    public void initialize(long seed, int size) {

        indexList.clear();
        currentListIndex = 0;

        if (size > 0) {

            //fill it with indices
            List<Integer> indices = new ArrayList<Integer>();
            for (int i = 0; i < size; i++) {
                indices.add(i);
            }

            Random rnd = new Random(seed);

            do {

                int index = rnd.nextInt(indices.size());
                indexList.add(indices.remove(index));

            } while (!indices.isEmpty());
        }
    }

    public int getCurrent() {
        //if currentIndex is out of bounds, return -1
        if (currentListIndex >= indexList.size()) {
            return -1;
        }

        return indexList.get(currentListIndex);
    }

    /**
     * Get the next index of the shuffled list.
     *
     * @return -1 if failed, otherwise the index.
     */
    public int getNext() {
        //if currentIndex is out of bounds, return -1
        if (currentListIndex + 1 >= indexList.size()) {
            return -1;
        }

        return indexList.get(++currentListIndex);
    }

    /**
     * Get the previous index of the shuffled list.
     *
     * @return -1 if failed, otherwise the index.
     */
    public int getPrevious() {
        //if currentIndex is out of bounds, return -1
        if (currentListIndex - 1 < 0 || indexList.isEmpty()) {
            return -1;
        }

        return indexList.get(--currentListIndex);
    }

    /**
     * This method will try to find the index you passed in the shuffled list
     * and set the current index of it to the position of your index.
     * The methods getPrevious() and getNext() will then give you the index before
     * or after that index.
     *
     * @param index The index from your list.
     * @return True if succeeded, otherwise false.
     */
    public boolean setTrack(int index) {
        /*
         * Loop through the whole list and stop when it has been found
         * Found will then be returned.
         */
        boolean found = false;
        for (int listIndex : indexList) {
            if (listIndex == index) {
                currentListIndex = listIndex;
                break;
            }
        }
        return found;
    }
}

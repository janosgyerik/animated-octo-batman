package com.janosgyerik.examples.lists;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static org.junit.Assert.assertEquals;

public class RemoveListItemsWhileIteratingTest {
    @Test(expected = ConcurrentModificationException.class)
    public void testConcurrentModificationNotAllowed() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        for (Integer num : nums) {
            // will throw
            nums.remove(num);
        }
    }

    @Test
    public void testCanRemoveFromCopyOnWriteArrayList() {
        List<Integer> nums = new CopyOnWriteArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        for (Integer num : nums) {
            if (num < 3) {
                nums.remove(num);
            }
        }
        assertEquals(Arrays.asList(3, 4, 5), nums);
    }

    @Test
    public void testCollectAndRemoveLater() {
        List<Integer> nums = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        List<Integer> toRemove = new ArrayList<>();
        for (Integer num : nums) {
            if (num < 3) {
                toRemove.add(num);
            }
        }
        nums.removeAll(toRemove);
        assertEquals(Arrays.asList(3, 4, 5), nums);
    }
}

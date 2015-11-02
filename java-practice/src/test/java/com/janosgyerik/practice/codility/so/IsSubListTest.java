package com.janosgyerik.practice.codility.so;

import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsSubListTest {
    public <T> boolean isSubList(List<T> smallerList, List<T> biggerList) {
        Iterator<T> biggerListIter = biggerList.iterator();
        for (T itemFromSmaller : smallerList) {
            T itemFromBigger;
            do {
                if (!biggerListIter.hasNext()) {
                    return false;
                }
                itemFromBigger = biggerListIter.next();
            } while (!itemFromSmaller.equals(itemFromBigger));
        }
        return true;
    }

    @Test
    public void test() {
        List<Integer> s1 = Arrays.asList(1, 2, 3, 4);
        assertTrue(isSubList(s1, s1));
        assertTrue(isSubList(Arrays.asList(1, 2), s1));
        assertTrue(isSubList(Arrays.asList(1, 2, 3), s1));
        assertFalse(isSubList(Arrays.asList(1, 2, 3, 4, 5), s1));
        assertFalse(isSubList(Arrays.asList(1, 3, 2), s1));
        assertTrue(isSubList(Arrays.asList(2, 3), s1));
        assertTrue(isSubList(Arrays.asList(1, 3), s1));
        assertTrue(isSubList(Arrays.asList(1, 3, 4), s1));
    }
}

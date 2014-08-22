package com.janosgyerik.stackoverflow.junk.deletealternate;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

class LinkedListWithEliminateDups<T> extends LinkedList<T> {

    public LinkedListWithEliminateDups(List<T> items) {
        super(items);
    }

    public void eliminateDuplicates() {
        final Set<T> set = new HashSet<T>();
        Node<T> prev = null;
        for (Node<T> node = first; node != null; node = node.next) {
            if (set.contains(node.item)) {
                prev.next = node.next;
            } else {
                set.add(node.item);
                prev = node;
            }
        }
        last = prev;
    }
}


public class LinkedListWithEliminateDupsTest {

    private void assertEqualsAfterDeleteDups(List<Integer> expected, List<Integer> orig) {
        LinkedList<Integer> linkedList = new LinkedList<>(orig);
        linkedList.eliminateDuplicates();
        assertEquals(new LinkedList<>(expected), linkedList);
    }

    private void assertNotEqualsAfterDeleteDups(List<Integer> expected, List<Integer> orig) {
        LinkedList<Integer> linkedList = new LinkedList<>(orig);
        linkedList.eliminateDuplicates();
        assertNotEquals(new LinkedList<>(expected), linkedList);
    }

    @Test
    public void testHashCode() {
        LinkedList<Integer> a = new LinkedList<>(Arrays.asList(1));
        LinkedList<Integer> b = new LinkedList<>(Arrays.asList(1));
        System.out.printf("%b %b%n", a.equals(b), a.hashCode() == b.hashCode());
        System.out.printf("%b %d%n", a.equals(b), a.hashCode());
        System.out.printf("%b %d%n", a.equals(b), b.hashCode());
    }

    @Test
    public void testEliminateSingleDup() {
        assertEqualsAfterDeleteDups(Arrays.asList(1, 2, 3, 4, 5, 7), Arrays.asList(1, 2, 3, 4, 5, 4, 3, 7));
        assertNotEqualsAfterDeleteDups(Arrays.asList(1, 3, 4, 5, 7), Arrays.asList(1, 2, 3, 4, 5, 4, 3, 7));
        assertNotEqualsAfterDeleteDups(Arrays.asList(1, 2, 3), Arrays.asList(4, 5, 6));
    }

    @Test
    public void testEliminateSingleDupAtStart() {
        assertEqualsAfterDeleteDups(Arrays.asList(1, 2, 3), Arrays.asList(1, 1, 2, 3));
    }

    @Test
    public void testEliminateSingleDupAtEnd() {
        assertEqualsAfterDeleteDups(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3, 3));
    }

    @Test
    public void testEliminateManyDups() {
        assertEqualsAfterDeleteDups(Arrays.asList(1, 2, 3), Arrays.asList(1, 1, 2, 2, 2, 3, 3, 3));
    }

    @Test
    public void testEliminateNothing() {
        assertEqualsAfterDeleteDups(Arrays.asList(1, 2, 3), Arrays.asList(1, 2, 3));
        assertNotEqualsAfterDeleteDups(Arrays.asList(1, 2), Arrays.asList(1, 2, 3));
    }

    @Test
    public void testEmpty() {
        assertEqualsAfterDeleteDups(Arrays.asList(), Arrays.asList());
        assertNotEqualsAfterDeleteDups(Arrays.asList(2), Arrays.asList());
    }

    @Test
    public void testSingletonList() {
        assertEqualsAfterDeleteDups(Arrays.asList(2), Arrays.asList(2));
        assertNotEqualsAfterDeleteDups(Arrays.asList(3), Arrays.asList(2));
    }


    @Test
    public void test2() {
        LinkedListWithEliminateDups<Integer> edu2 = new LinkedListWithEliminateDups<Integer>(Arrays.asList(7, 2, 5, 10, 4, 2, 9, 10));
        edu2.eliminateDuplicates();

        LinkedListWithEliminateDups<Integer> eduExpected2 = new LinkedListWithEliminateDups<Integer>(Arrays.asList(7, 2, 5, 10, 4, 9));
        assertEquals(eduExpected2, edu2);
    }

    @Test
    public void test2x() {
        assertEqualsAfterDeleteDups(Arrays.asList(7, 2, 5, 10, 4, 9), Arrays.asList(7, 2, 5, 10, 4, 2, 9, 10));
    }

    @Test
    public void testAllDuplicates() {
        LinkedListWithEliminateDups<Integer> edu3 = new LinkedListWithEliminateDups<Integer>(Arrays.asList(2, 2, 2, 2, 2));
        edu3.eliminateDuplicates();

        LinkedListWithEliminateDups<Integer> eduExpected3 = new LinkedListWithEliminateDups<Integer>(Arrays.asList(2));
        assertEquals(eduExpected3, edu3);
    }

    private void assertEqualsAfterDeleteAlternate(List<Integer> expected, List<Integer> orig) {
        LinkedList<Integer> linkedList = new LinkedList<>(orig);
        linkedList.deleteAlternate();
        assertEquals(new LinkedList<>(expected), linkedList);
    }

    private void assertNotEqualsAfterDeleteAlternate(List<Integer> expected, List<Integer> orig) {
        LinkedList<Integer> linkedList = new LinkedList<>(orig);
        linkedList.deleteAlternate();
        assertNotEquals(new LinkedList<>(expected), linkedList);
    }

    @Test
    public void testWithSingleItem() {
        assertEqualsAfterDeleteAlternate(Arrays.asList(1), Arrays.asList(1));
        assertNotEqualsAfterDeleteAlternate(Arrays.asList(1, 2), Arrays.asList(1));
    }

    @Test
    public void testWith2Items() {
        assertEqualsAfterDeleteAlternate(Arrays.asList(1), Arrays.asList(1, 2));
        assertNotEqualsAfterDeleteAlternate(Arrays.asList(1, 2), Arrays.asList(1, 2));
        assertNotEqualsAfterDeleteAlternate(Arrays.asList(1, 2, 3), Arrays.asList(1, 2));
    }

    @Test
    public void testWith8Items() {
        assertEqualsAfterDeleteAlternate(Arrays.asList(1, 3, 5, 7), Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
    }


    @Test
    public void test4() {
        LinkedList<Integer> dAlternate4 = new LinkedList<>(Arrays.asList(1, 2, 3, 4));
        dAlternate4.deleteAlternate();
        assertEquals(new LinkedList<>(Arrays.asList(1, 3)), dAlternate4);
    }


    @Test
    public void test5() {
        LinkedList<Integer> dAlternate5 = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5));
        dAlternate5.deleteAlternate();
        assertEquals(new LinkedList<>(Arrays.asList(1, 3, 5)), dAlternate5);
        assertNotEquals(new LinkedList<>(Arrays.asList(1, 4, 5)), dAlternate5);
        assertNotEquals(new LinkedList<>(Arrays.asList(1, 4)), dAlternate5);
    }

}
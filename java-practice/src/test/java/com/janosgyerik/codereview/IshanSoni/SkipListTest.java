package com.janosgyerik.codereview.IshanSoni;

import org.junit.Test;

import static org.junit.Assert.*;

class SkipList<T extends Comparable<T>> {
    private SkipNode<T> start;
    private SkipNode<T> end;
    private SkipNode<T> supportStart;
    private SkipNode<T> supportEnd;
    private int size;
    private int sizeSupport;

    public T getStart() {
        return start.getData();
    }

    public T getEnd() {
        return end.getData();
    }

    public int getSize() {
        return size;
    }

    public void add(T data) {
        if (start == null) {
            insertAsFirstElement(data);
        } else {
            insert(data);
        }
    }

    private void insertAsFirstElement(T data) {
        SkipNode<T> node = new SkipNode<>(data);
        start = node;
        end = node;
        size++;

        SkipNode<T> supportNode = new SkipNode<>(data);
        supportStart = supportNode;
        supportEnd = supportNode;
        supportNode.setDown(node);
        sizeSupport++;
    }

    //Adding element in the end assuming user enters data in ascending order
    private void insert(T data) {
        SkipNode<T> node = new SkipNode<>(data);
        end.setNext(node);
        node.setPrevious(end);
        end = node;
        size++;

        int expectedSupportSize = (int) Math.sqrt(size);
        if (sizeSupport < expectedSupportSize) {
            SkipNode<T> supportNode = new SkipNode<>(data);
            supportEnd.setNext(supportNode);
            supportNode.setPrevious(supportEnd);
            supportEnd = supportNode;
            supportNode.setDown(node);
            sizeSupport++;

            if (sizeSupport > 2)
                reAjustSupportList();

        }
    }

    /*readjusting the support list so that they point to the correct nodes when new
    *support nodes are added
    */
    private void reAjustSupportList() {
        SkipNode<T> navigationNode = supportStart.getNext();
        int i = 1;

        while (navigationNode != supportEnd) {
            SkipNode<T> tempNode = navigationNode.getDown();
            for (int j = 1; j <= i; j++) {
                tempNode = tempNode.getNext();
            }
            navigationNode.setDown(tempNode);
            navigationNode.setData(tempNode.getData());
            navigationNode = navigationNode.getNext();

            i++;
        }
    }

    public boolean search_orig(T data){
        SkipNode<T> navigationNode = supportStart;

        if(data.compareTo(navigationNode.getData()) < 1){
            return false;
        }

        while(navigationNode != null && navigationNode.getNext() != null && (data.compareTo(navigationNode.getNext().getData()) > 0 || data.compareTo(navigationNode.getData()) == 0)){
            navigationNode = navigationNode.getNext();
        }

        SkipNode<T> searchNodeStart = navigationNode.getDown();
        SkipNode<T> searchNodeEnd  = navigationNode.getNext().getDown();

        while(searchNodeStart != searchNodeEnd){
            if(searchNodeStart.getData().compareTo(data) == 0){
                return true;
            }
            searchNodeStart = searchNodeStart.getNext();
        }
        return false;
    }

    public boolean search(T data) {
        SkipNode<T> navigationNode = supportStart;
        int compare;

        while ((compare = data.compareTo(navigationNode.getData())) > 0
                && navigationNode.getNext() != null) {
            navigationNode = navigationNode.getNext();
        }

        if (compare == 0) {
            return true;
        }

        if (compare < 0) {
            navigationNode = navigationNode.getPrevious();
        }
        navigationNode = navigationNode.getDown().getNext();

        while ((compare = data.compareTo(navigationNode.getData())) > 0
                && navigationNode.getNext() != null) {
            navigationNode = navigationNode.getNext();
        }
        return compare == 0;
    }

    private static class SkipNode<T> {

        public SkipNode(T data) {
            this.data = data;
        }

        private SkipNode<T> next = null;
        private SkipNode<T> previous = null;
        private SkipNode<T> down = null;
        private T data;

        public SkipNode<T> getNext() {
            return next;
        }

        public void setNext(SkipNode<T> next) {
            this.next = next;
        }

        public SkipNode<T> getPrevious() {
            return previous;
        }

        public void setPrevious(SkipNode<T> previous) {
            this.previous = previous;
        }

        public SkipNode<T> getDown() {
            return down;
        }

        public void setDown(SkipNode<T> down) {
            this.down = down;
        }

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return String.format("%s -> %s -v %s", data, next != null, down != null);
        }
    }
}

public class SkipListTest {
    @Test
    public void test_with_30_50() {
        SkipList<Integer> list = new SkipList<>();
        list.add(30);
        list.add(50);
        assertEquals(2, list.getSize());
        assertEquals(new Integer(30), list.getStart());
        assertEquals(new Integer(50), list.getEnd());
        assertTrue(list.search(30));  // -> false
        assertTrue(list.search(50));  // -> npe
        assertFalse(list.search(51));  // -> npe
        assertFalse(list.search(33));  // -> npe
    }

    @Test
    public void test_with_30_40_50_60_70_80_90() {
        SkipList<Integer> list = new SkipList<>();
        list.add(30);
        list.add(40);
        list.add(50);
        list.add(60);
        list.add(70);
        list.add(80);
        list.add(90);
        assertEquals(7, list.getSize());
        assertEquals(new Integer(30), list.getStart());
        assertEquals(new Integer(90), list.getEnd());
        assertTrue(list.search(30));  // -> false
        assertTrue(list.search(40));
        assertTrue(list.search(50));
        assertTrue(list.search(60));  // -> false
        assertTrue(list.search(70));  // -> npe
        assertTrue(list.search(80));  // -> npe
        assertTrue(list.search(90));  // -> npe
        assertFalse(list.search(33));
        assertFalse(list.search(51));
        assertFalse(list.search(63));  // -> npe
        assertFalse(list.search(133));  // -> npe
    }
}

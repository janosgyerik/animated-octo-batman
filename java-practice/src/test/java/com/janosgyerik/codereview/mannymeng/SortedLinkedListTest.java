package com.janosgyerik.codereview.mannymeng;


import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

class SortedList<E extends Comparable<E>> implements List<E> {

    private Node first;
    private int size;

    public SortedList() {
        this.first = null;
        this.size = 0;
    }

    @Override
    public boolean add(E element) {
        if (element == null) {
            throw new NullPointerException();
        }
        size++;
        if (first != null) {
            Node before = null;
            Node current = first;
            for (; element.compareTo(current.getValue()) > 0; before = current, current = current
                    .getNext()) {
                if(current.getNext() == null) {
                    current.setNext(new Node(element));
                    return true;
                }
            }
            Node newNode = new Node(element);
            if (before != null) {
                before.setNext(newNode);
                newNode.setNext(current);
            } else {
                newNode.setNext(first);
                first = newNode;
            }
        } else {
            first = new Node(element);
        }
        return true;
    }

    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        for (E element : elements) {
            add(element);
        }
        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        this.first = null;
    }

    @Override
    public boolean contains(Object obj) {
        return obj != null && indexOf(obj) != -1;
    }

    @Override
    public boolean containsAll(Collection<?> elements) {
        for (Object element : elements) {
            if (!contains(element)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index " + index
                    + " is out of range.");
        }
        Node result = first;
        while (index > 0) {
            index--;
            result = result.getNext();
        }
        return result.getValue();
    }

    @Override
    public int indexOf(Object obj) {
        Node result = first;
        for (int i = 0; result != null; i++, result = result.getNext()) {
            if (result.getValue().equals(obj)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public Iterator<E> iterator() {
        return new SortedListIterator();
    }

    @Override
    public int lastIndexOf(Object obj) {
        Node result = first;
        int lastIndex = -1;
        for (int i = 0; result != null; i++, result = result.getNext()) {
            if (result.getValue().equals(obj)) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }

    @Override
    public ListIterator<E> listIterator() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ListIterator<E> listIterator(int startIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object obj) {
        for (Node before = null, current = first; current != null; before = current, current = current
                .getNext()) {
            if (current.getValue().equals(obj)) {
                size--;
                if(before == null) {
                    first = current.getNext();
                } else {
                    before.setNext(current.getNext());
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index " + index
                    + " is out of range.");
        }
        Node removed = first;
        Node before = null;
        while (index > 0) {
            index--;
            before = removed;
            removed = removed.getNext();
        }
        before.setNext(removed.getNext());
        size--;
        return removed.getValue();
    }

    @Override
    public boolean removeAll(Collection<?> elements) {
        boolean result = true;
        for (Object element : elements) {
            result &= remove(element);
        }
        return result;
    }

    @Override
    public boolean retainAll(Collection<?> elements) {
        boolean hasChanged = false;
        SortedList<E> result = new SortedList<E>();
        for (Object element : elements) {
            if (contains(element)) {
                result.add((E) element);
                hasChanged = true;
            }
        }
        if (hasChanged) {
            this.first = result.first;
            this.size = result.size;
        }
        return hasChanged;
    }

    @Override
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public List<E> subList(int startIndex, int endIndex) {
        SortedList<E> result = new SortedList<E>();
        Node current = first;
        {
            int i = 0;
            while (i < startIndex) {
                current = current.getNext();
                i++;
            }
        }
        result.first = new Node(current);
        {
            int i = startIndex;
            while (i < endIndex) {
                i++;
                current = current.getNext();
            }
        }
        current.setNext(null);
        return result;
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int counter = 0;
        for (Node current = first; current != null; current = current.getNext(), counter++) {
            result[counter] = current.getValue();
        }
        return result;
    }

    @Override
    public <T> T[] toArray(T[] array) {
        T[] result = array.length < size ? Arrays.copyOf(array, size) : array;
        int counter = 0;
        for (Node current = first; current != null; current = current.getNext(), counter++) {
            result[counter] = (T) current.getValue();
        }
        return result;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (first == null ? 0 : first.hashCode());
        result = prime * result + size;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SortedList<?>)) {
            return false;
        }
        SortedList<?> other = (SortedList<?>) obj;
        if (size != other.size) {
            return false;
        }
        for(Iterator<?> thisIterator = this.iterator(), otherIterator = other.iterator(); thisIterator.hasNext() && otherIterator.hasNext();) {
            if(!thisIterator.next().equals(otherIterator.next())) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        if (!isEmpty()) {
            builder.append(first.getValue());
            for (Node current = first.next; current != null; current = current.getNext()) {
                builder.append(", ").append(current.getValue());
            }
        }
        builder.append("]");
        return builder.toString();
    }

    class Node {

        private E value;
        private Node next = null;

        public Node(E value) {
            this.value = value;
        }

        public Node(Node node) {
            this.value = node.value;
            this.next = node.next == null ? null : new Node(node.next);
        }

        public E getValue() {
            return value;
        }

        public void setValue(E value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((next == null) ? 0 : next.hashCode());
            result = prime * result + ((value == null) ? 0 : value.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof SortedList<?>.Node)) {
                return false;
            }
            SortedList.Node other = (SortedList.Node) obj;
            return this.equals(other)
                    && (next == null ^ other.next == null)
                    && next.equals(other.next)
                    && (value == null ^ other.value == null)
                    && value.equals(other.value);
        }

    }

    class SortedListIterator implements Iterator<E> {

        private Node current;

        public SortedListIterator() {
            this.current = first;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E value = current.getValue();
            current = current.getNext();
            return value;
        }

    }

}

public class SortedLinkedListTest {
    @Test
    public void testEmpty() {
        SortedList<Integer> list = new SortedList<>();
        assertEquals(Arrays.asList().toString(), list.toString());
    }

    @Test
    public void test() {
        SortedList<Integer> list = new SortedList<>();
        list.addAll(Arrays.asList(3, 1, 7, 3, 2, 4, 3));
        assertEquals(Arrays.asList(1, 2, 3, 3, 3, 4, 7).toString(), list.toString());
    }

    @Test
    public void testPQ() {
        Collection<Integer> collection = new PriorityQueue<>();
        collection.addAll(Arrays.asList(3, 1, 7, 3, 2, 4, 3));
        assertEquals(Arrays.asList(3, 1, 7, 3, 2, 4, 3).toString(), collection.toString());
    }

    @Test
    public void testRemove0() {
        SortedList<Integer> list = new SortedList<>();
        list.addAll(Arrays.asList(3, 1, 7, 3, 2, 4, 3));
        list.remove(0);
    }
}

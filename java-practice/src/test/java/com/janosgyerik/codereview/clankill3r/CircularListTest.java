package com.janosgyerik.codereview.clankill3r;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

class Node<T> {
    Node previous;
    Node next;
    final T data;

    Node(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return String.format("%s :: %s :: %s", data, previous == null ? "null" : previous.data, next == null ? "null" : next.data);
    }
}

public class CircularListTest {

    private List<Node<Integer>> newNodeList(int num) {
        List<Node<Integer>> items = new ArrayList<>();
        for (int i = 0; i < num; ++i) {
            items.add(new Node<>(i));
        }
        return items;
    }

    private String toString(List<?> items) {
        StringBuilder builder = new StringBuilder();
        for (Object item : items) {
            builder.append(item).append(", ");
        }
        return builder.toString();
    }

    private String orig() {
        Node previous, current, next;

        List<Node<Integer>> nodeList = newNodeList(5);
        previous = nodeList.get(nodeList.size() - 1);

        for (int i = 0; i < nodeList.size(); i++) {
            current = nodeList.get(i);
            next = i + 1 == nodeList.size() ? nodeList.get(0) : nodeList.get(i + 1);

            current.previous = previous;
            current.next = next;

            previous = current;
        }
        return toString(nodeList);
    }

    private String simpler() {
        List<Node<Integer>> nodeList = newNodeList(5);

        Node<Integer> previous = nodeList.get(nodeList.size() - 1);
        for (Node<Integer> node : nodeList) {
            node.previous = previous;
            previous.next = node;
            previous = node;
        }
        return toString(nodeList);
    }

    private String simpler2() {
        List<Node<Integer>> nodeList = newNodeList(5);

        Iterator<Node<Integer>> iter = nodeList.iterator();

        Node<Integer> first = iter.next();
        Node<Integer> previous = first;
        Node<Integer> node = null;

        while (iter.hasNext()) {
            node = iter.next();
            node.previous = previous;
            previous.next = node;
            previous = node;
        }

        if (node != null) {
            node.next = first;
            first.previous = node;
        }
        return toString(nodeList);
    }

    @Test
    public void test() {
//        simpler();
        assertEquals(orig(), simpler());
        assertEquals(orig(), simpler2());
    }

}

package com.janosgyerik.stackoverflow.MeenaChaudhary;

import org.junit.Test;

import java.util.*;

class Node {

    private final char label;
    private final Map<Node, Integer> neighbourList;

    public Node(char label) {
        this.label = label;
        this.neighbourList = new LinkedHashMap<>();
    }

    public void addNeighbourer(Node node, int weight) {
        neighbourList.put(node, weight);
    }

    public char getLabel() {
        return label;
    }

    public Map<Node, Integer> neighbourerList() {
        return neighbourList;
    }
}

class Main {
    public static void primMinimumWeightSpanningTree(Map<Node, Integer> freeMap) {

        Set<Node> mstSet = new LinkedHashSet<>();
        while (freeMap.size() > 0) {

            Node minNode = null;
        /* finds minimum node in freeMap as per corresponding value.*/
            for (Map.Entry<Node, Integer> entry : freeMap.entrySet()) {
                if (minNode == null) {
                    minNode = entry.getKey();
                } else {
                    if (entry.getValue() < freeMap.get(minNode)) {
                        minNode = entry.getKey();
                    }
                }
            }

            freeMap.remove(minNode); /* remove minimum node from freeMap*/
            mstSet.add(minNode); /* add minimum node to MST set(mstSet)*/
        /* update values of adjacent nodes in freeMap*/
            for (Map.Entry<Node, Integer> entry : minNode.neighbourerList().entrySet()) {
                if (freeMap.containsKey(entry.getKey())) {
                    int value = freeMap.get(entry.getKey());
                    if (value > entry.getValue()) {
                        freeMap.replace(entry.getKey(), entry.getValue());
                    }
                }
            }
        }
    /* display vertices once all are added to mstSet*/
        for (Node node : mstSet) {
            System.out.print(node.getLabel() + " ");
        }
    }
}

public class PrimsTest {
    @Test
    public void test() {
        Map<Node, Integer> freeMap = new HashMap<>();
        freeMap.put(new Node('A'), 0);
        freeMap.put(new Node('B'), Integer.MAX_VALUE);
        freeMap.put(new Node('C'), Integer.MAX_VALUE);
        freeMap.put(new Node('D'), Integer.MAX_VALUE);
        freeMap.put(new Node('E'), Integer.MAX_VALUE);
        freeMap.put(new Node('F'), Integer.MAX_VALUE);
        Main.primMinimumWeightSpanningTree(freeMap);
    }
}

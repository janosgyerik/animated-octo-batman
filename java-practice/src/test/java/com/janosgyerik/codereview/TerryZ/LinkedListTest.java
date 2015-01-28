package com.janosgyerik.codereview.TerryZ;

import org.junit.Test;

import java.util.LinkedList;
import java.util.ListIterator;

class LinkNode {

    private Node root;

    private static class Node {
        private final String data;
        private Node nextNode;

        public Node() {
            this.data = null;
        }

        public Node(String data) {
            this.data = data;
        }

        public void add(Node nextNode) {
            if (this.nextNode == null) {
                this.nextNode = nextNode;
            } else {
                this.nextNode.add(nextNode);
            }
        }

        public String getData() {
            return this.data;
        }

        public Node getNext() {
            return this.nextNode;
        }

        public void print() {
            System.out.println(this.data);
            if (this.nextNode != null) {
                this.nextNode.print();
            }
        }

        public boolean search(String data) {
            if (data.equals(this.data)) {
                return true;
            } else {
                return this.nextNode != null && this.nextNode.search(data);
            }
        }

        public boolean delete(Node previousNode, String data) {
            if (this.data.equals(data)) {
                previousNode.nextNode = this.nextNode;
                return true;
            }
            if (this.nextNode != null) {
                this.nextNode.delete(this, data);
            }
            return false;
        }
    }

    public void addNode(String data) {
        Node newNode = new Node(data);
        if (this.root == null) {
            this.root = newNode;
        } else {
            this.root.add(newNode);
        }
    }

    public void printNode() {
        if (this.root != null) {
            this.root.print();
        }
    }

    public boolean isContain(String data) {
        return this.root.search(data);
    }

    public void deleteNode(String data) {
        if (this.root.data.equals(data)) {
            if (this.root.getNext() == null) {
                this.root = new Node();
            } else {
                this.root = this.root.getNext();
            }
        } else {
            this.root.delete(root, data);
        }

    }

}

public class LinkedListTest {
    @Test
    public void test() {
        LinkNode linkNode = new LinkNode();
        linkNode.addNode("A");
        linkNode.addNode("B");
        linkNode.addNode("C");
        linkNode.addNode("D");
        linkNode.addNode("E");
        linkNode.addNode("F");
        linkNode.addNode("G");

        System.out.println("before delete nodes");
        linkNode.printNode();
        linkNode.deleteNode("A");
        linkNode.deleteNode("E");
        System.out.println("after delete nodes");
        linkNode.printNode();

        LinkedList<String> testList = new LinkedList<String>();
        StringBuffer strBuffer = new StringBuffer();
        for(int i = 0; i < 10; i++){
            strBuffer.append(i);
            testList.add(strBuffer.toString());
        }
        System.out.println(testList);
        System.out.println();
//        for(int i = 0; i < testList.size(); i++){
//            if(testList.get(i).equals("0123456") || testList.get(i).equals("012345678")){
//                testList.remove(i);
//                i--;
//            }
//        }
        ListIterator<String> iter = testList.listIterator();
        while (iter.hasNext()) {
            String item = iter.next();
            if (item.equals("0123456") || item.equals("012345678")) {
                iter.remove();
            }
        }
        System.out.println(testList);
    }
}

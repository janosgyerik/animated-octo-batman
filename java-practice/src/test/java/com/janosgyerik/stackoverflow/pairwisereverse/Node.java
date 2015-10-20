package com.janosgyerik.stackoverflow.pairwisereverse;

class Node {

    private Node next;
    private int data;

    public Node(int data) {
        this.data = data;
    }

    public int getData() {
        return data;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    @Override
    public String toString() {
        return Integer.toString(data);
    }

}
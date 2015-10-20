package com.janosgyerik.stackoverflow.pairwisereverse;

public class SinglyLinkedList {

    private Node mFirst;
    private Node mLast;

    public SinglyLinkedList(Node first) {
        this.mFirst = first;
        this.mLast = first;
    }

    public SinglyLinkedList(int []array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException();
        }
        this.mFirst = new Node(array[0]);
        this.mLast = this.mFirst;
        for (int i = 1; i < array.length; i++) {
            addLast(new Node(array[i]));
        }
    }

    public void addLast(Node node) {
        mLast.setNext(node);
        mLast = node;
    }

    public Node getFirst() {
        return mFirst;
    }

    public Node getLast() {
        return mLast;
    }

    public void print() {
        Node current = mFirst;
        System.out.print("[");
        while (current != null) {
            System.out.print(current);
            current = current.getNext();
            if (current != null) {
                System.out.print(", ");
            }
        }
        System.out.print("]");
        System.out.println();
    }

    public void reversePairs() {
        Node sentinel = new Node(0);
        sentinel.setNext(mFirst);

        Node node = sentinel;
        while (true) {
            Node next = node.getNext();
            if (next == null || next.getNext() == null) {
                break;
            }
            node.setNext(swap(next));
            node = next;
            mLast = next;
        }

        mFirst = sentinel.getNext();

        if (mLast.getNext() != null) {
            mLast = mLast.getNext();
        }
    }

    private Node swap(Node node) {
        Node next = node.getNext();
        Node nextnext = next.getNext();
        node.setNext(nextnext);
        next.setNext(node);
        return next;
    }
}
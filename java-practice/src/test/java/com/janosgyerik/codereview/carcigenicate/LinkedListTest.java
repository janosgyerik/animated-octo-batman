package com.janosgyerik.codereview.carcigenicate;

class Node<T> {
    private final T cargo;
    private Node<T> tail;

    public Node(T cargo) {
        this.cargo = cargo;
        tail = null;
    }

    public Node<T> getNext() {
        return tail;
    }

    public T getCargo() {
        return cargo;
    }

    public boolean isLast() {
        return tail == null;
    }

    public Node<T> getLast() {
        Node<T> curNode = this;
        while (!curNode.isLast()) {
            curNode = curNode.getNext();
        }
        return curNode;
    }

    public void add(T nCargo) {
        tail = new Node<>(nCargo);
    }

    public boolean canLink() {
        return this.getNext().getNext() != null;
    }

    public void deleteNext() {
        tail = canLink() ? tail.getNext() : null;
    }
}

class Vect<T> {
    Node<T> head;
    int size;

    public Vect() {
        head = null;
        size = 0;
    }

    public Vect(T cargo) {
        head = new Node<>(cargo);
        size = 1;
    }

    public void pushBack(T cargo) {
        Node<T> last = head.getLast();
        ++size;
        last.add(cargo);
    }

    private Node<T> getNodeAt(int index) {
        Node<T> node = head;
        for (int i = 0; i < index; i++) {
            node = node.getNext();
        }
        return node;
    }

    public T getAt(int i) {
        return getNodeAt(i).getCargo();
    }

    public int length() {
        return size;
    }

    public void deleteHead() {
        head = head.getNext();
        --size;
    }

    public void deleteAt(int i) {
        if (i == 0) {
            deleteHead();
        } else {
            Node<T> curNode = this.getNodeAt(i - 1);
            curNode.deleteNext();
            --size;
        }
    }
}

class Main {

    public static void main(String[] args) {
        Vect<Integer> v = new Vect<>(2);
        v.pushBack(3);
        v.pushBack(4);
        v.pushBack(5);
        v.pushBack(6);

        for (int i = 0; i < v.length(); ++i) {
            System.out.println(v.getAt(i));
        }

        v.deleteAt(2);

        for (int i = 0; i < v.length(); ++i) {
            System.out.println(v.getAt(i));
        }
    }
}


public class LinkedListTest {
}

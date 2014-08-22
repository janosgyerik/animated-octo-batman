package com.janosgyerik.stackoverflow.junk;

class CustomQueue {

    private int maxSize;
    private int[] queArray;
    private int front;
    private int rear;
    private int nItems;

    public CustomQueue(int s) {
        maxSize = s;
        queArray = new int[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public void insert(int j) {
        if (rear == maxSize - 1) {
            rear = -1;
        }

        queArray[++rear] = j;
        nItems++;
    }

    public int remove() {
        int removed = queArray[front++];
        if (front == maxSize) {
            front = 0;
        }

        nItems--;

        return removed;
    }

    public int peek() {
        return queArray[front];
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int size() {
        return nItems;
    }

    public void display() {
        System.out.println("First Inserted Item to Last Inserted Item");

        if (isEmpty()) {
            System.out.println("Queue is Empty!");
        } else if (rear < front) {
            for (int i = front; i < maxSize; i++) {
                System.out.println(queArray[i]);
            }
            for (int i = 0; i <= rear; i++) {
                System.out.println(queArray[i]);
            }
        } else if (rear == front) {
            System.out.println(queArray[front]);
        }
    }
}
package impl.queue;

import api.queue.IntQueue;

public class LinkedQueue implements IntQueue {
    private QueueNode front;
    private QueueNode end;
    private final int capacity;
    private int size;

    public LinkedQueue(int maxSize) {
        front = null;
        end = null;
        capacity = maxSize;
        size = 0;
    }

    @Override
    public void add(int input) {
        if (size == capacity) {
            throw new IllegalStateException();
        }

        QueueNode newNode = new QueueNode(input);
        if (front == null) {
            front = newNode;
        } else {
            QueueNode currentEnd = end;
            currentEnd.setNext(newNode);
            newNode.setPrev(currentEnd);
        }
        end = newNode;
        size++;
    }

    @Override
    public Integer remove() {

        if (front == null)
            return null;

        int value = front.getKey();
        front = front.getNext();
        size--;

        if (front != null) {
            front.setPrev(null);
        }

        return value;
    }

    @Override
    public Integer element() {

        if (front == null) {
            return null;
        }

        return front.getKey();
    }

    @Override
    public int getSize() {
        return size;
    }

    private static class QueueNode {
        private int key;
        private QueueNode prev;
        private QueueNode next;

        public QueueNode(int key) {
            this.key = key;
            next = null;
            prev = null;
        }

        public int getKey() {
            return key;
        }

        public QueueNode getNext() {
            return next;
        }

        public void setNext(QueueNode next) {
            this.next = next;
        }

        public void setPrev(QueueNode prev) {
            this.prev = prev;
        }
    }
}

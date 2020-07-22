package impl.queue;

import api.queue.IntQueue;

public class LinkedQueue implements IntQueue {
    private QueueNode front;
    private final int capacity;

    public LinkedQueue(int maxSize) {
        front = null;
        capacity = maxSize;
    }

    @Override
    public void add(int input) {
        int currentSize = getSize();
        if (currentSize == capacity) {
            throw new IllegalStateException();
        }

        QueueNode newNode = new QueueNode(input);

        if (front == null) {
            newNode.setNext(null);
            front = newNode;
        }

        QueueNode iterator = front;
        while (iterator.getNext() != null) {
            iterator = iterator.getNext();
        }
        iterator.setNext(newNode);
        newNode.setNext(null);
    }

    @Override
    public Integer remove() {

        if (front == null)
            return null;

        int value = front.getKey();
        front = front.getNext();
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
        if (front == null) {
            return 0;
        }

        QueueNode iterator = front;
        int counter = 1;

        while (iterator.getNext() != null) {
            iterator = iterator.getNext();
            counter++;
        }
        return counter;
    }

    private static class QueueNode {
        private int key;
        private QueueNode next;

        public QueueNode(int key)
        {
            this.key = key;
            this.next = null;
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
    }
}

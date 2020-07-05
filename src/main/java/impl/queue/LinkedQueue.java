package impl.queue;

import api.queue.IntQueue;

public class LinkedQueue implements IntQueue {
    private QNode front;
    private QNode end;
    private final int capacity;

    public LinkedQueue(int maxSize) {
        front = null;
        end = null;
        capacity = maxSize;
    }

    @Override
    public void add(int input) {
        int currentSize = getSize();
        if (currentSize == capacity) {
            throw new IllegalStateException();
        }

        QNode newNode = new QNode(input);

        if (this.front == null) {
            newNode.setPrev(null);
            newNode.setNext(null);
            this.front = newNode;
        }

        QNode iterator = front;
        while (iterator.getNext() != null) {
            iterator = iterator.getNext();
        }
        iterator.setNext(newNode);
        newNode.setPrev(iterator);
        newNode.setNext(null);
        this.end = newNode;
    }

    @Override
    public Integer remove() {

        if (this.front == null)
            return null;

        int value = front.getKey();
        this.front = front.getNext();
        if (this.front != null) {
            this.front.setPrev(null);
        }
        return value;
    }

    @Override
    public Integer element() {

        if (this.front == null) {
            return null;
        }

        return this.front.getKey();
    }

    @Override
    public int getSize() {
        if (this.front == null) {
            return 0;
        }

        QNode iterator = this.front;
        int counter = 1;

        while (iterator.getNext() != null) {
            iterator = iterator.getNext();
            counter++;
        }
        return counter;
    }
}

package impl.queue;

import api.queue.IntQueue;

import java.util.LinkedList;

class QueueNode {
    private Integer value;
    private QueueNode next;
    private QueueNode previous;

    public QueueNode() {
        this.value = null;
        this.next = null;
        this.previous = null;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }

    public QueueNode getPrevious() {
        return previous;
    }

    public void setPrevious(QueueNode previous) {
        this.previous = previous;
    }
}

public class LinkedQueueFactory implements IntQueue {
    private static int capacity;
    private static QueueNode queueLinked;

    public static IntQueue getInstance(int maxSize) {
        capacity = maxSize;
        queueLinked = new QueueNode();
        return new LinkedQueueFactory();
    }

    @Override
    public void add(int e) {
        int currentSize = getSize();
        if (currentSize == capacity) {
            throw new IllegalStateException();
        } else if (currentSize == 0) {
            queueLinked.setValue(e);
        } else {
            QueueNode assist = queueLinked;
            while (assist.getNext() != null) {
                assist = assist.getNext();
            }
            QueueNode newElement = new QueueNode();
            newElement.setValue(e);
            newElement.setPrevious(assist);
            assist.setNext(newElement);
        }
    }

    @Override
    public Integer remove() {
        int currentSize = getSize();
        if (currentSize == 0) {
            return null;
        } else {
            Integer output = queueLinked.getValue();
            if (queueLinked.getNext() == null) {
                queueLinked = new QueueNode();
            } else {
                queueLinked = queueLinked.getNext();
                queueLinked.setPrevious(null);
            }
            return output;
        }
    }

    @Override
    public Integer element() {
        int currentSize = getSize();
        if (currentSize == 0) {
            return null;
        } else {
            return queueLinked.getValue();
        }
    }

    @Override
    public int getSize() {
        if (queueLinked.getValue() == null) {
            return 0;
        } else {
            int size = 1;
            QueueNode assist = queueLinked;
            while (assist.getNext() != null) {
                size += 1;
                assist = assist.getNext();
            }
            return size;
        }
    }
}
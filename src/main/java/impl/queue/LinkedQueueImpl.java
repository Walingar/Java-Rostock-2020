package impl.queue;

import api.queue.IntQueue;

public class LinkedQueueImpl implements IntQueue {
    private final int capacity;
    private LinkedQueueNode head;
    private int size;
    private LinkedQueueNode tail;

    public LinkedQueueImpl(int maxSize) {
        capacity = maxSize;
        size = 0;
    }

    @Override
    public void add(int e) {
        if (size == capacity) {
            throw new IllegalStateException();
        }
        if (size == 0) {
            head = new LinkedQueueNode(e);
        }
        else if (size == 1) {
            tail = new LinkedQueueNode(e);
            head.next = tail;
            tail.previous = head;
        }
        else {
            LinkedQueueNode oldTail = new LinkedQueueNode(tail.value);
            LinkedQueueNode preOldTail = tail.previous;
            preOldTail.next = oldTail;
            oldTail.previous = preOldTail;
            oldTail.next= tail;
            tail.previous = oldTail;
            tail.value = e;
        }
        size++;
    }

    @Override
    public Integer remove() {
        if (size == 0) {
            return null;
        }
        Integer output = head.value;
        if (size == 1) {
            head = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        size--;
        return output;
    }

    @Override
    public Integer element() {
        if (size == 0) {
            return null;
        }
        return head.value;
    }

    @Override
    public int getSize() {
        return size;
    }

    static class LinkedQueueNode {
        private Integer value;
        private LinkedQueueNode next;
        private LinkedQueueNode previous;

        public LinkedQueueNode(int e) {
            value = e;
            next = null;
            previous = null;
        }
    }
}

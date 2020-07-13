package impl.queue;

import api.queue.IntQueue;

public class LinkedQueueImpl implements IntQueue {
    private final int capacity;
    private LinkedQueueNode head;
    private int currentSize;
    private LinkedQueueNode tail;

    public LinkedQueueImpl(int maxSize) {
        capacity = maxSize;
        currentSize = 0;
    }

    @Override
    public void add(int e) {
        if (currentSize == capacity) {
            throw new IllegalStateException();
        }
        if (currentSize == 0) {
            head = new LinkedQueueNode(e);
        }
        else if (currentSize == 1) {
            tail = new LinkedQueueNode(e);
            head.setNext(tail);
            tail.setPrevious(head);
        }
        else {
            LinkedQueueNode oldTail = new LinkedQueueNode(tail.getValue());
            LinkedQueueNode preOldTail = tail.getPrevious();
            preOldTail.setNext(oldTail);
            oldTail.setPrevious(preOldTail);
            oldTail.setNext(tail);
            tail.setPrevious(oldTail);
            tail.setValue(e);
        }
        currentSize++;
    }

    @Override
    public Integer remove() {
        if (currentSize == 0) {
            return null;
        }
        Integer output = head.getValue();
        if (currentSize == 1) {
            head = null;
        } else {
            head = head.getNext();
            head.setPrevious(null);
        }
        currentSize--;
        return output;
    }

    @Override
    public Integer element() {
        if (currentSize == 0) {
            return null;
        }
        return head.getValue();
    }

    @Override
    public int getSize() {
        return currentSize;
    }

    class LinkedQueueNode {
        private Integer value;
        private LinkedQueueNode next;
        private LinkedQueueNode previous;

        public LinkedQueueNode(int e) {
            value = e;
            next = null;
            previous = null;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public LinkedQueueNode getNext() {
            return next;
        }

        public void setNext(LinkedQueueNode next) {
            this.next = next;
        }

        public LinkedQueueNode getPrevious() {
            return previous;
        }

        public void setPrevious(LinkedQueueNode previous) {
            this.previous = previous;
        }
    }
}

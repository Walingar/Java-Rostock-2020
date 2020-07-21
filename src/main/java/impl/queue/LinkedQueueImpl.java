package impl.queue;

import api.queue.IntQueue;

public class LinkedQueueImpl extends AbstractQueueImpl {
    private LinkedQueueNode head;
    private LinkedQueueNode tail;

    public LinkedQueueImpl(int maxSize) {
        super(maxSize);
    }

    @Override
    public void addImpl(int e) {
        if (size == 0) {
            head = new LinkedQueueNode(e);
        } else if (size == 1) {
            tail = new LinkedQueueNode(e);
            head.next = tail;
            tail.previous = head;
        } else {
            LinkedQueueNode oldTail = new LinkedQueueNode(tail.value);
            LinkedQueueNode preOldTail = tail.previous;
            preOldTail.next = oldTail;
            oldTail.previous = preOldTail;
            oldTail.next = tail;
            tail.previous = oldTail;
            tail.value = e;
        }
    }

    @Override
    public Integer removeImpl() {
        Integer output = head.value;
        if (size == 1) {
            head = null;
        } else {
            head = head.next;
            head.previous = null;
        }
        return output;
    }

    @Override
    public Integer elementImpl() {
        return head.value;
    }


    private static class LinkedQueueNode {
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

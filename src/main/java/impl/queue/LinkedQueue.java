package impl.queue;

public class LinkedQueue extends AbstractQueue {
    private QueueNode front;
    private QueueNode end;

    public LinkedQueue(int capacity) {
        super(capacity);
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
        size++;
        end = newNode;
    }

    @Override
    public Integer remove() {
        if (size == 0) {
            return null;
        }
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
        if (size == 0) {
            return null;
        }
        return front.getKey();
    }

    private static class QueueNode {
        private final int key;
        private QueueNode prev;
        private QueueNode next;

        public QueueNode(int key) {
            this.key = key;
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

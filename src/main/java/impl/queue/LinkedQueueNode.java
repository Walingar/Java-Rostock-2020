package impl.queue;

public class LinkedQueueNode {
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

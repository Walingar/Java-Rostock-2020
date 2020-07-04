package impl.queue;

public class QNode {
    private int key;
    private QNode prev;
    private QNode next;

    public QNode(int key)
    {
        this.key = key;
        this.prev = null;
        this.next = null;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public QNode getNext() {
        return next;
    }

    public void setNext(QNode next) {
        this.next = next;
    }

    public QNode getPrev() {
        return prev;
    }

    public void setPrev(QNode prev) {
        this.prev = prev;
    }
}

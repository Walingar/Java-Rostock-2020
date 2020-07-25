package impl.queue;

import api.queue.IntQueue;

public class AbstractQueue implements IntQueue {
    protected final int capacity;
    protected int size;

    public AbstractQueue(int capacity){
        this.capacity = capacity;
        size = 0;
    }

    @Override
    public void add(int input) {
        add(input);
    }

    @Override
    public Integer remove() {
        return remove();
    }

    @Override
    public Integer element() {
        return element();
    }

    @Override
    public int getSize() {
        return size;
    }
}

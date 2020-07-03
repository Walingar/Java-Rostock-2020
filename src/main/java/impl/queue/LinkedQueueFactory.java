package impl.queue;

import api.queue.IntQueue;

public class LinkedQueueFactory implements IntQueue{

    public static IntQueue getInstance(int maxSize) {
        capacity = maxSize;
        element = new QueueNode();
        return new LinkedQueueFactory();
    }

    @Override
    public void add(int e) {

    }

    @Override
    public Integer remove() {
        return null;
    }

    @Override
    public Integer element() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }
}
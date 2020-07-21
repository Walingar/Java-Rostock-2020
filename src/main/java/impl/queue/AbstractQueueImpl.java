package impl.queue;

import api.queue.IntQueue;

public abstract class AbstractQueueImpl implements IntQueue {
    protected final int capacity;
    protected int size;

    public AbstractQueueImpl(int maxSize) {
        capacity = maxSize;
    }

    @Override
    public void add(int e) {
        if (size == capacity) {
            throw new IllegalStateException();
        }
        addImpl(e);
        size++;
    }

    protected abstract void addImpl(int e);

    @Override
    public Integer remove() {
        if (size == 0) {
            return null;
        }
        Integer output = removeImpl();
        size--;
        return output;
    }

    protected abstract Integer removeImpl();

    @Override
    public Integer element() {
        if (size == 0) {
            return null;
        }
        return elementImpl();
    }

    protected abstract Integer elementImpl();

    @Override
    public int getSize() {
        return size;
    }
}

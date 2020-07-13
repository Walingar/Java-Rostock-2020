package impl.queue;

import api.queue.IntQueue;

public class ArrayQueueImpl implements IntQueue {
    private final int capacity;
    private final Integer[] queueArray;
    private int size;
    private int headIndex;

    public ArrayQueueImpl(int maxSize) {
        capacity = maxSize;
        queueArray = new Integer[capacity];
        size = 0;
        headIndex = 0;
    }

    @Override
    public void add(int e) {
        if (size == capacity) {
            throw new IllegalStateException();
        } else {
            int currentIndex = (size + headIndex) % capacity;
            queueArray[currentIndex] = e;
            size++;
        }
    }

    @Override
    public Integer remove() {
        if (size == 0) {
            return null;
        }
        Integer output = queueArray[headIndex];
        queueArray[headIndex] = null;
        size--;
        headIndex++;
        if (headIndex == capacity) {
            headIndex = 0;
        }
        return output;
    }

    @Override
    public Integer element() {
        if (size == 0) {
            return null;
        }
        return queueArray[headIndex];
    }

    @Override
    public int getSize() {
        return size;
    }
}

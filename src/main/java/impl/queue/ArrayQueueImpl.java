package impl.queue;

import api.queue.IntQueue;

public class ArrayQueueImpl implements IntQueue {
    private final int capacity;
    private final Integer[] queueArray;
    private int currentSize;
    private int currentShift;

    public ArrayQueueImpl(int maxSize) {
        capacity = maxSize;
        queueArray = new Integer[capacity];
        currentSize = 0;
        currentShift = 0;
    }

    @Override
    public void add(int e) {
        if (currentSize == capacity) {
            throw new IllegalStateException();
        } else {
            int currentIndex = currentSize + currentShift;
            if (currentIndex >= capacity) {
                currentIndex -= capacity;
            }
            queueArray[currentIndex] = e;
            currentSize++;
        }
    }

    @Override
    public Integer remove() {
        if (currentSize == 0) {
            return null;
        }
        Integer output = queueArray[currentShift];
        queueArray[currentShift] = null;
        currentSize--;
        currentShift++;
        if (currentShift == capacity) {
            currentShift = 0;
        }
        return output;
    }

    @Override
    public Integer element() {
        if (currentSize == 0) {
            return null;
        }
        return queueArray[currentShift];
    }

    @Override
    public int getSize() {
        return currentSize;
    }
}

package impl.queue;

import api.queue.IntQueue;

public class ArrayQueue implements IntQueue {

    private final int capacity;
    private final Integer[] arrayQueue;
    private int headIndex;
    private int size;

    public ArrayQueue(int maxSize) {
        capacity = maxSize;
        arrayQueue = new Integer[capacity];
        headIndex = 0;
        size = 0;
    }

    @Override
    public void add(int input) {
        if (size == capacity) {
            throw new IllegalStateException();
        }
        int insertIndex = (size + headIndex) % capacity;
        arrayQueue[insertIndex] = input;
        size++;

    }

    @Override
    public Integer remove() {
        if (size == 0) {
            return null;
        }
        Integer removedInteger = arrayQueue[headIndex];
        arrayQueue[headIndex] = 0;
        headIndex++;
        size--;

        if (headIndex == capacity) {
            headIndex = 0;
        }
        return removedInteger;

    }

    @Override
    public Integer element() {
        return arrayQueue[headIndex];
    }

    @Override
    public int getSize() {
        return size;
    }
}

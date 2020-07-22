package impl.queue;

import api.queue.IntQueue;

public class ArrayQueue implements IntQueue {

    private final int capacity;
    private final Integer[] arrayQueue;
    private int currentIndex;

    public ArrayQueue(int maxSize) {
        capacity = maxSize;
        arrayQueue = new Integer[capacity];
        currentIndex = 0;
    }

    @Override
    public void add(int input) {
        if (currentIndex == capacity) {
            throw new IllegalStateException();
        }
        arrayQueue[currentIndex] = input;
        currentIndex++;

    }

    @Override
    public Integer remove() {
        if (currentIndex == 0) {
            return null;
        }

        int removedInteger = arrayQueue[0];
        for (int i = 0; i < arrayQueue.length - 1; i++) {
            arrayQueue[i] = arrayQueue[i + 1];
        }
        arrayQueue[arrayQueue.length - 1] = null;
        arrayQueue[currentIndex-1] = null;
        currentIndex--;
        return removedInteger;

    }

    @Override
    public Integer element() {
        return arrayQueue[0];
    }

    @Override
    public int getSize() {
        int counter = 0;
        for (int i = 0; i < arrayQueue.length; i ++) {
            if (arrayQueue[i] != null)
                counter++;
        }
        return counter;
    }
}

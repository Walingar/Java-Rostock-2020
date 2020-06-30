package impl.queue;

import api.queue.IntQueue;

public class ArrayQueueFactory implements IntQueue {
    private static int capacity;
    private static Integer[] queueArray;
    private static int currentSize;

    public static IntQueue getInstance(int maxSize) {
        capacity = maxSize;
        queueArray = new Integer[capacity];
        return new ArrayQueueFactory();
    }

    @Override
    public void add(int e) {
        int currentSize = getSize();
        if (currentSize == capacity) {
            throw new IllegalStateException();
        } else {
            queueArray[currentSize] = e;
        }
    }

    @Override
    public Integer remove() {
        int currentSize = getSize();
        if (currentSize == 0) {
            return null;
        } else {
            Integer output = queueArray[0];
            for (int newPosition = 0; newPosition < capacity; newPosition++) {
                int oldPosition = newPosition + 1;
                if (oldPosition == capacity) {
                    queueArray[newPosition] = null;
                    break;
                }
                if (queueArray[oldPosition] != null) {
                    queueArray[newPosition] = queueArray[oldPosition];
                } else {
                    queueArray[newPosition] = null;
                    break;
                }
            }
            return output;
        }
    }

    @Override
    public Integer element() {
        int currentSize = getSize();
        if (currentSize == 0) {
            return null;
        } else {
            return queueArray[0];
        }
    }

    @Override
    public int getSize() {
        int size = 0;
        for (int currentElement = 0; currentElement < capacity; currentElement++) {
            if (queueArray[currentElement] != null) {
                size += 1;
            } else {
                break;
            }
        }
        return size;
    }
}
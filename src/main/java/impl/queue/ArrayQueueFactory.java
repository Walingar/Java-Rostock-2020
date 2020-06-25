package impl.queue;

import api.queue.IntQueue;

public class ArrayQueueFactory implements IntQueue{
    private static int capacity;
    private static Integer[] queueArray;

    public static IntQueue getInstance(int maxSize) {
        capacity = maxSize;
        queueArray = new Integer[capacity];
        return new ArrayQueueFactory();
    }

    @Override
    public void add(int e) {
        int currentSize = getSize();
        if (currentSize == capacity){
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
            for (int currentElement = 1; currentElement < capacity; currentElement++) {
                int newPosition = currentElement - 1;
                if (queueArray[currentElement] != null) {
                    queueArray[newPosition] = queueArray[currentElement];
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
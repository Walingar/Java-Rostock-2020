package impl.queue;

import api.queue.IntQueue;

public class ArrayQueue implements IntQueue {

    private final int capacity;
    private final int currentSize;
    private final Integer[] arrayQueue;
    private int currentIndex;

    public ArrayQueue (int maxSize){
        capacity = maxSize;
        arrayQueue = new Integer[capacity];
        currentIndex = 0;
        currentSize = 0;
    }

    @Override
    public void add(int input) {
        if (currentIndex == capacity) {
            System.out.println("Queue full!");
        } else {
            arrayQueue[currentIndex] = input;
            currentIndex++;
        }
    }

    @Override
    public Integer remove() {
        if (currentIndex == 0) {
            System.out.println("Queue empty!");
            return null;
        } else {
            int removedInteger = arrayQueue[currentIndex - 1];
            currentIndex--;
            return removedInteger;
        }
    }

    @Override
    public Integer element() {
        return arrayQueue[0];
    }

    @Override
    public int getSize() {
        return arrayQueue.length;
    }
}

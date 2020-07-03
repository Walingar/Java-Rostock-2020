package impl.queue;

import api.queue.IntQueue;

public class ArrayQueueFactory implements IntQueue {

    private static int capacity;
    private static int currentSize;
    private static Integer[] arrayQueue;
    private static int currentIndex;

    public static IntQueue getInstance(int maxSize) {
        capacity = maxSize;
        arrayQueue = new Integer[capacity];
        currentIndex = 0;
        currentSize = 0;
        return new ArrayQueueFactory();
    }

    @Override
    public void add(int input) {
        if (currentIndex == capacity) {
            System.out.println("Queue full!");
        }
        arrayQueue[currentIndex] = input;
        currentIndex++;

    }

    @Override
    public Integer remove() {
        if (currentIndex == 0) {
            System.out.println("Array empty!");
            return null;
        }
        int removedInteger = arrayQueue[currentIndex-1];
        currentIndex--;
        return removedInteger;
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
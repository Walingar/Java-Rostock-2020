package impl.queue;

import api.queue.IntQueue;
import org.w3c.dom.Node;

import java.util.LinkedList;

public class LinkedQueueFactory implements IntQueue{
    private static int capacity;
    private static LinkedList<Integer> queueLinkedList;

    public static IntQueue getInstance(int maxSize) {
        capacity = maxSize;
        queueLinkedList = new LinkedList<Integer>();
        return new LinkedQueueFactory();
    }

    @Override
    public void add(int e) {
        int currentSize = getSize();
        if (currentSize == capacity){
            throw new IllegalStateException();
        } else {
            queueLinkedList.add(e);
        }
    }

    @Override
    public Integer remove() {
        int currentSize = getSize();
        if (currentSize == 0) {
            return null;
        } else {
            Integer output = queueLinkedList.getFirst();
            queueLinkedList.removeFirst();
            return output;
        }
    }

    @Override
    public Integer element() {
        int currentSize = getSize();
        if (currentSize == 0) {
            return null;
        } else {
            return queueLinkedList.getFirst();
        }
    }

    @Override
    public int getSize() {
        return queueLinkedList.size();
    }
}
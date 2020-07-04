package impl.queue;

import api.queue.IntQueue;

public class ArrayQueueFactory {

    public static IntQueue getInstance(int maxSize) {
        IntQueue queue = new ArrayQueue(maxSize);
        return queue;
    }

}
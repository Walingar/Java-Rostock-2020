package impl.queue;

import api.queue.IntQueue;

public class ArrayQueueFactory {
    public static IntQueue getInstance(int maxSize) {
        return new ArrayQueueImpl(maxSize);
    }
}
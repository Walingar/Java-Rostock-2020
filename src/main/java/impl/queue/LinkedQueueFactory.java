package impl.queue;

import api.queue.IntQueue;

public class LinkedQueueFactory {

    public static IntQueue getInstance(int maxSize) {
        IntQueue lqueue = new LinkedQueue(maxSize);
        return lqueue;
    }


}
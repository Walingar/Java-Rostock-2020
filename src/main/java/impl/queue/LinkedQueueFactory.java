package impl.queue;

import api.queue.IntQueue;

public class LinkedQueueFactory {

    public static IntQueue getInstance(int maxSize) {
        return new LinkedQueue(maxSize);
    }


}
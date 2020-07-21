package impl.queue;

import api.queue.IntQueue;

public class ArrayQueueImpl extends AbstractQueueImpl {
    private final Integer[] queueArray;
    private int headIndex;

    public ArrayQueueImpl(int maxSize) {
        super(maxSize);
        queueArray = new Integer[capacity];
        headIndex = 0;
    }

    @Override
    public void addImpl(int e) {
        int currentIndex = (size + headIndex) % capacity;
        queueArray[currentIndex] = e;
    }

    @Override
    public Integer removeImpl() {
        Integer output = queueArray[headIndex];
        queueArray[headIndex] = null;
        headIndex++;
        if (headIndex == capacity) {
            headIndex = 0;
        }
        return output;
    }

    @Override
    public Integer elementImpl() {
        return queueArray[headIndex];
    }
}

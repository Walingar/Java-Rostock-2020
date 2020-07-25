package impl.queue;

public class ArrayQueue extends AbstractQueue {
    private final Integer[] arrayQueue;
    private int headIndex;

    public ArrayQueue(int capacity) {
        super(capacity);
        arrayQueue = new Integer[capacity];
        headIndex = 0;
    }

    @Override
    public void add(int input) {
        if (size == capacity) {
            throw new IllegalStateException();
        }
        int insertIndex = (size + headIndex) % capacity;
        arrayQueue[insertIndex] = input;
        size++;
    }

    @Override
    public Integer remove() {
        if (size == 0) {
            return null;
        }
        Integer removedInteger = arrayQueue[headIndex];
        arrayQueue[headIndex] = null;
        headIndex++;
        size--;

        if (headIndex == capacity) {
            headIndex = 0;
        }
        return removedInteger;
    }

    @Override
    public Integer element() {
        if (size == 0) {
            return null;
        }
        return arrayQueue[headIndex];
    }

}

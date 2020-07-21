package impl.pair;

import api.pair.Pair;

import java.util.Objects;

public class PairImpl<T, K> implements Pair<T, K> {
    private final T firstValue;
    private final K secondValue;

    public PairImpl(T first, K second) {
        firstValue = first;
        secondValue = second;
    }

    @Override
    public T getFirst() { return firstValue; }

    @Override
    public K getSecond() {
        return secondValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PairImpl) {
            PairImpl<?, ?> comparable = (PairImpl<?, ?>) obj;
            return comparable.getFirst().equals(firstValue) && comparable.getSecond().equals(secondValue);
        }
        return false;
    }

    @Override
    public String toString() {
        return "First: '" + firstValue + "', Second: '" + secondValue + "'";
    }
}

package impl.pair;

import api.pair.Pair;

import java.util.Objects;

public class PairImpl<K, T> implements Pair<K, T> {
    private final K first;
    private final T second;

    public PairImpl(K first, T second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public K getFirst() {
        return first;
    }

    @Override
    public T getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof PairImpl) {
            PairImpl<?, ?> comparable = (PairImpl<?, ?>) obj;
            return comparable.getFirst().equals(first) && comparable.getSecond().equals(second);
        }
        return false;
    }

    @Override
    public String toString() {
        return "First: '" + first + "', Second: '" + second + "'";
    }

}

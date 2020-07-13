package impl.pair;

import api.pair.NumberPair;

public class NumberPairImpl<T extends Number, K extends Number> implements NumberPair<T, K> {
    private final T firstValue;
    private final K secondValue;

    public NumberPairImpl(T first, K second) {
        firstValue = first;
        secondValue = second;
    }

    @Override
    public T getFirst() {
        return firstValue;
    }

    @Override
    public K getSecond() {
        return secondValue;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getFirst().hashCode();
        result = 31 * result + getSecond().hashCode();
        return result;
    } // Idea from effective Java : Item 9

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof NumberPairImpl)) {
            return false;
        }
        NumberPairImpl numberPairImpl = (NumberPairImpl) obj;
        return numberPairImpl.getFirst().equals(firstValue) && numberPairImpl.getSecond().equals(secondValue);
    }

    @Override
    public String toString() {
        return "First: '" + firstValue + "', Second: '" + secondValue + "'";
    }
}

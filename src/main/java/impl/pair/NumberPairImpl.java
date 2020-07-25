package impl.pair;

import api.pair.NumberPair;

public class NumberPairImpl<T extends Number, K extends Number> extends PairImpl<K,T> implements NumberPair<K,T> {

    public NumberPairImpl(K first, T second){
        super(first, second);
    }

    @Override
    public K getFirst() {
        return null;
    }

    @Override
    public T getSecond() {
        return null;
    }
}

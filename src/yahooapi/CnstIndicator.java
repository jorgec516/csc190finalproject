package yahooapi;

import yahooapi.AIndicator;

public class CnstIndicator<T> extends AIndicator<T> {

    private T value;

    public CnstIndicator(T t) {
        super(null);
        this.value = t;
    }

    @Override
    public T getValue(int index) {
        return value;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " Value: " + value;
    }
    
    
}

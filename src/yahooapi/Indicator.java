package yahooapi;

import java.io.Serializable;


public interface Indicator<T> extends Serializable {

    T getValue(int index);

    TimeSeries getTimeSeries();
}

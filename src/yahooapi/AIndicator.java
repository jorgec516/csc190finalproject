package yahooapi;

import yahooapi.Indicator;
import yahooapi.Strategy;
import yahooapi.TimeSeries;

public abstract class AIndicator<T> implements Indicator<T> {


    private TimeSeries series;

    public AIndicator(TimeSeries series) {
        this.series = series;
    }

    @Override
    public TimeSeries getTimeSeries() {
        return series;
    }


}

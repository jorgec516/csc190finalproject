package yahooapi;

import yahooapi.Decimal;
import yahooapi.Strategy;
import yahooapi.TimeSeries;
import yahooapi.CIndicator;

public class CPIndicator extends CIndicator<Decimal> {

    private TimeSeries series;

    public CPIndicator(TimeSeries series) {
        super(series);
        this.series = series;
    }

    @Override
    protected Decimal calculate(int index) {
        return series.getTick(index).getClosePrice();
    }
    
}

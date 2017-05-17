package yahooapi;

import java.util.List;

public interface AnalysisC {

    double calculate(TimeSeries series, Trade trade);

    double calculate(TimeSeries series, TradingRec tradingRecord);

    Strategy chooseBest(TimeSeries series, List<Strategy> strategies);

    boolean betterThan(double criterionValue1, double criterionValue2);
}
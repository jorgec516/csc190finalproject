package yahooapi;

import yahooapi.Strategy;
import yahooapi.TimeSeries;
import java.util.List;
import yahooapi.AnalysisC;

public abstract class Analysis implements AnalysisC {

    @Override
    public Strategy chooseBest(TimeSeries series, List<Strategy> strategies) {
        Strategy bestStrategy = strategies.get(0);
        double bestCriterionValue = calculate(series, series.run(bestStrategy));

        for (int i = 1; i < strategies.size(); i++) {
            Strategy currentStrategy = strategies.get(i);
            double currentCriterionValue = calculate(series, series.run(currentStrategy));

            if (betterThan(currentCriterionValue, bestCriterionValue)) {
                bestStrategy = currentStrategy;
                bestCriterionValue = currentCriterionValue;
            }
        }
        return bestStrategy;
    }


}

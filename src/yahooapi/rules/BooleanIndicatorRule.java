
package yahooapi.rules;

import yahooapi.Indicator;
import yahooapi.TradingRec;

/**
 * A boolean-indicator-based rule.
 * <p>
 * Satisfied when the value of the {@link Indicator indicator} is true.
 */
public class BooleanIndicatorRule extends AbstractRule {

    private Indicator<Boolean> indicator;

    /**
     * Constructor.
     * @param indicator a boolean indicator
     */
    public BooleanIndicatorRule(Indicator<Boolean> indicator) {
        this.indicator = indicator;
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        final boolean satisfied = indicator.getValue(index);
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

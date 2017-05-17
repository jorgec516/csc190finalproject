
package yahooapi.rules;

import yahooapi.Decimal;
import yahooapi.Indicator;
import yahooapi.TradingRec;
import yahooapi.CnstIndicator;

/**
 * Indicator-over-indicator rule.
 * <p>
 * Satisfied when the value of the first {@link Indicator indicator} is strictly greater than the value of the second one.
 */
public class OverIndicatorRule extends AbstractRule {

    /** The first indicator */
    private Indicator<Decimal> first;
    /** The second indicator */
    private Indicator<Decimal> second;

    /**
     * Constructor.
     * @param indicator the indicator
     * @param threshold a threshold
     */
    public OverIndicatorRule(Indicator<Decimal> indicator, Decimal threshold) {
        this(indicator, new CnstIndicator<Decimal>(threshold));
    }
    
    /**
     * Constructor.
     * @param first the first indicator
     * @param second the second indicator
     */
    public OverIndicatorRule(Indicator<Decimal> first, Indicator<Decimal> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        final boolean satisfied = first.getValue(index).isGreaterThan(second.getValue(index));
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

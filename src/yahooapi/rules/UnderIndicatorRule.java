
package yahooapi.rules;

import yahooapi.Decimal;
import yahooapi.Indicator;
import yahooapi.TradingRec;
import yahooapi.CnstIndicator;

/**
 * Indicator-under-indicator rule.
 * <p>
 * Satisfied when the value of the first {@link Indicator indicator} is strictly lesser than the value of the second one.
 */
public class UnderIndicatorRule extends AbstractRule {

    /** The first indicator */
    private Indicator<Decimal> first;
    /** The second indicator */
    private Indicator<Decimal> second;

    /**
     * Constructor.
     * @param indicator the indicator
     * @param threshold a threshold
     */
    public UnderIndicatorRule(Indicator<Decimal> indicator, Decimal threshold) {
        this(indicator, new CnstIndicator<Decimal>(threshold));
    }
    
    /**
     * Constructor.
     * @param first the first indicator
     * @param second the second indicator
     */
    public UnderIndicatorRule(Indicator<Decimal> first, Indicator<Decimal> second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        final boolean satisfied = first.getValue(index).isLessThan(second.getValue(index));
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

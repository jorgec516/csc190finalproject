
package yahooapi.rules;

import yahooapi.Decimal;
import yahooapi.Indicator;
import yahooapi.TradingRec;
import yahooapi.CnstIndicator;

/**
 * Indicator-between-indicators rule.
 * <p>
 * Satisfied when the value of the {@link Indicator indicator} is between the values of the boundary (up/down) indicators.
 */
public class InPipeRule extends AbstractRule {

    /** The upper indicator */
    private Indicator<Decimal> upper;
    /** The lower indicator */
    private Indicator<Decimal> lower;
    /** The evaluated indicator */
    private Indicator<Decimal> ref;

    /**
     * Constructor.
     * @param ref the reference indicator
     * @param upper the upper threshold
     * @param lower the lower threshold
     */
    public InPipeRule(Indicator<Decimal> ref, Decimal upper, Decimal lower) {
        this(ref, new CnstIndicator<Decimal>(upper), new CnstIndicator<Decimal>(lower));
    }

    /**
     * Constructor.
     * @param ref the reference indicator
     * @param upper the upper indicator
     * @param lower the lower indicator
     */
    public InPipeRule(Indicator<Decimal> ref, Indicator<Decimal> upper, Indicator<Decimal> lower) {
        this.upper = upper;
        this.lower = lower;
        this.ref = ref;
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        final boolean satisfied = ref.getValue(index).isLessThanOrEqual(upper.getValue(index))
                && ref.getValue(index).isGreaterThanOrEqual(lower.getValue(index));
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

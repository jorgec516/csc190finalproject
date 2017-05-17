
package yahooapi.rules;

import yahooapi.Rule;
import yahooapi.TradingRec;

/**
 * An opposite (logical operator: NOT) rule.
 * <p>
 * Satisfied when provided rule is not satisfied.<br>
 * Not satisfied when provided rule is satisfied.
 */
public class NotRule extends AbstractRule {

    private Rule rule;

    /**
     * Constructor.
     * @param rule a trading rule
     */
    public NotRule(Rule rule) {
        this.rule = rule;
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        final boolean satisfied = !rule.isSatisfied(index, tradingRecord);
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

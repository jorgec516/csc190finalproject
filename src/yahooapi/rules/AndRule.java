
package yahooapi.rules;

import yahooapi.Rule;
import yahooapi.TradingRec;

/**
 * An AND combination of two {@link Rule rules}.
 * <p>
 * Satisfied when the two provided rules are satisfied as well.<br>
 * Warning: the second rule is not tested if the first rule is not satisfied.
 */
public class AndRule extends AbstractRule {

    private Rule rule1;
    
    private Rule rule2;

    /**
     * Constructor
     * @param rule1 a trading rule
     * @param rule2 another trading rule
     */
    public AndRule(Rule rule1, Rule rule2) {
        this.rule1 = rule1;
        this.rule2 = rule2;
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        final boolean satisfied = rule1.isSatisfied(index, tradingRecord) && rule2.isSatisfied(index, tradingRecord);
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

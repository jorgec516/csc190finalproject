
package yahooapi.rules;

import yahooapi.TradingRec;
import java.util.Arrays;

/**
 * An indexes-based rule.
 * <p>
 * Satisfied for provided indexes.
 */
public class FixedRule extends AbstractRule {

    private final int[] indexes;

    /**
     * Constructor.
     * @param indexes a sequence of indexes
     */
    public FixedRule(int... indexes) {
        this.indexes = Arrays.copyOf(indexes, indexes.length);
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        boolean satisfied = false;
        for (int i = 0; i < indexes.length; i++) {
            if (indexes[i] == index) {
                satisfied = true;
                break;
            }
        }
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

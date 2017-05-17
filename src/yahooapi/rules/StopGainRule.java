
package yahooapi.rules;

import yahooapi.Decimal;
import yahooapi.Trade;
import yahooapi.TradingRec;
import yahooapi.CPIndicator;

/**
 * A stop-gain rule.
 * <p>
 * Satisfied when the close price reaches the gain threshold.
 */
public class StopGainRule extends AbstractRule {

    /** The close price indicator */
    private CPIndicator closePrice;
    
    /** The gain ratio threshold (e.g. 1.03 for 3%) */
    private Decimal gainRatioThreshold;

    /**
     * Constructor.
     * @param closePrice the close price indicator
     * @param gainPercentage the gain percentage
     */
    public StopGainRule(CPIndicator closePrice, Decimal gainPercentage) {
        this.closePrice = closePrice;
        this.gainRatioThreshold = Decimal.HUNDRED.plus(gainPercentage).dividedBy(Decimal.HUNDRED);
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        boolean satisfied = false;
        // No trading history or no trade opened, no gain
        if (tradingRecord != null) {
            Trade currentTrade = tradingRecord.getCurrentTrade();
            if (currentTrade.isOpened()) {
                Decimal entryPrice = currentTrade.getEntry().getPrice();
                Decimal currentPrice = closePrice.getValue(index);
                satisfied = currentPrice.isGreaterThanOrEqual(entryPrice.multipliedBy(gainRatioThreshold));
            }
        }
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

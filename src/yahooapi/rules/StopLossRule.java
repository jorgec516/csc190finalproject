
package yahooapi.rules;

import yahooapi.Decimal;
import yahooapi.Trade;
import yahooapi.TradingRec;
import yahooapi.CPIndicator;

/**
 * A stop-loss rule.
 * <p>
 * Satisfied when the close price reaches the loss threshold.
 */
public class StopLossRule extends AbstractRule {

    /** The close price indicator */
    private CPIndicator closePrice;
    
    /** The loss ratio threshold (e.g. 0.97 for 3%) */
    private Decimal lossRatioThreshold;

    /**
     * Constructor.
     * @param closePrice the close price indicator
     * @param lossPercentage the loss percentage
     */
    public StopLossRule(CPIndicator closePrice, Decimal lossPercentage) {
        this.closePrice = closePrice;
        this.lossRatioThreshold = Decimal.HUNDRED.minus(lossPercentage).dividedBy(Decimal.HUNDRED);
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        boolean satisfied = false;
        // No trading history or no trade opened, no loss
        if (tradingRecord != null) {
            Trade currentTrade = tradingRecord.getCurrentTrade();
            if (currentTrade.isOpened()) {
                Decimal entryPrice = currentTrade.getEntry().getPrice();
                Decimal currentPrice = closePrice.getValue(index);
                satisfied = currentPrice.isLessThanOrEqual(entryPrice.multipliedBy(lossRatioThreshold));
            }
        }
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}


package yahooapi.rules;

import yahooapi.Order;
import static yahooapi.Order.OrderType;
import yahooapi.TradingRec;

/**
 * A {@link Rule rule} which waits for a number of {@link Tick ticks} after an order.
 * <p>
 * Satisfied after a fixed number of ticks since the last order.
 */
public class WaitForRule extends AbstractRule {

    /** The type of the order since we have to wait for */
    private OrderType orderType;
    
    /** The number of ticks to wait for */
    private int numberOfTicks;

    /**
     * Constructor.
     * @param orderType the type of the order since we have to wait for
     * @param numberOfTicks the number of ticks to wait for
     */
    public WaitForRule(OrderType orderType, int numberOfTicks) {
        this.orderType = orderType;
        this.numberOfTicks = numberOfTicks;
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        boolean satisfied = false;
        // No trading history, no need to wait
        if (tradingRecord != null) {
            Order lastOrder = tradingRecord.getLastOrder(orderType);
            if (lastOrder != null) {
                int currentNumberOfTicks = index - lastOrder.getIndex();
                satisfied = currentNumberOfTicks >= numberOfTicks;
            }
        }
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }
}

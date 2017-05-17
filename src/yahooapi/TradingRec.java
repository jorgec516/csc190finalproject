package yahooapi;

import yahooapi.Order.OrderType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TradingRec implements Serializable {

	private static final long serialVersionUID = -4436851731855891220L;

	/** The recorded orders */
    private List<Order> orders = new ArrayList<Order>();
    
    /** The recorded BUY orders */
    private List<Order> buyOrders = new ArrayList<Order>();
    
    /** The recorded SELL orders */
    private List<Order> sellOrders = new ArrayList<Order>();
    
    /** The recorded entry orders */
    private List<Order> entryOrders = new ArrayList<Order>();
    
    /** The recorded exit orders */
    private List<Order> exitOrders = new ArrayList<Order>();
    
    /** The recorded trades */
    private List<Trade> trades = new ArrayList<Trade>();

    /** The entry type (BUY or SELL) in the trading session */
    private OrderType startingType;
    
    /** The current non-closed trade (there's always one) */
    private Trade currentTrade;


    public TradingRec() {
        this(OrderType.BUY);
    }
    

    public TradingRec(OrderType entryOrderType) {
        if (entryOrderType == null) {
            throw new IllegalArgumentException("Starting type must not be null");
        }
        this.startingType = entryOrderType;
        currentTrade = new Trade(entryOrderType);
    }



    

    public Trade getCurrentTrade() {
        return currentTrade;
    }
    

    public final void operate(int index) {
        operate(index, Decimal.NaN, Decimal.NaN);
    }
    

    public final void operate(int index, Decimal price, Decimal amount) {
        if (currentTrade.isClosed()) {
            // Current trade closed, should not occur
            throw new IllegalStateException("Current trade should not be closed");
        }
        boolean newOrderWillBeAnEntry = currentTrade.isNew();
        Order newOrder = currentTrade.operate(index, price, amount);
        recordOrder(newOrder, newOrderWillBeAnEntry);
    }
    

    public final boolean enter(int index) {
        return enter(index, Decimal.NaN, Decimal.NaN);
    }
    
    public final boolean enter(int index, Decimal price, Decimal amount) {
        if (currentTrade.isNew()) {
            operate(index, price, amount);
            return true;
        }
        return false;
    }
    

    public final boolean exit(int index) {
        return exit(index, Decimal.NaN, Decimal.NaN);
    }
    
    public final boolean exit(int index, Decimal price, Decimal amount) {
        if (currentTrade.isOpened()) {
            operate(index, price, amount);
            return true;
        }
        return false;
    }
    

    public boolean isClosed() {
        return !currentTrade.isOpened();
    }
    

    public List<Trade> getTrades() {
        return trades;
    }
    

    public int getTradeCount() {
        return trades.size();
    }
    
    public Trade getLastTrade() {
        if (!trades.isEmpty()) {
            return trades.get(trades.size() - 1);
        }
        return null;
    }
    

    public Order getLastOrder() {
        if (!orders.isEmpty()) {
            return orders.get(orders.size() - 1);
        }
        return null;
    }
    

    public Order getLastOrder(OrderType orderType) {
        if (OrderType.BUY.equals(orderType) && !buyOrders.isEmpty()) {
            return buyOrders.get(buyOrders.size() - 1);
        } else if (OrderType.SELL.equals(orderType) && !sellOrders.isEmpty()) {
            return sellOrders.get(sellOrders.size() - 1);
        }
        return null;
    }
    

    public Order getLastEntry() {
        if (!entryOrders.isEmpty()) {
            return entryOrders.get(entryOrders.size() - 1);
        }
        return null;
    }
    

    public Order getLastExit() {
        if (!exitOrders.isEmpty()) {
            return exitOrders.get(exitOrders.size() - 1);
        }
        return null;
    }


    private void recordOrder(Order order, boolean isEntry) {
        if (order == null) {
            throw new IllegalArgumentException("Order should not be null");
        }
        
        // Storing the new order in entries/exits lists
        if (isEntry) {
            entryOrders.add(order);
        } else {
            exitOrders.add(order);
        }
        
        // Storing the new order in orders list
        orders.add(order);
        if (OrderType.BUY.equals(order.getType())) {
            // Storing the new order in buy orders list
            buyOrders.add(order);
        } else if (OrderType.SELL.equals(order.getType())) {
            // Storing the new order in sell orders list
            sellOrders.add(order);
        }

        // Storing the trade if closed
        if (currentTrade.isClosed()) {
            trades.add(currentTrade);
            currentTrade = new Trade(startingType);
        }
    }
}

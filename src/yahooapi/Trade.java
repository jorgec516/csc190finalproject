package yahooapi;

import java.io.Serializable;

import yahooapi.Order.OrderType;


public class Trade implements Serializable {

	private static final long serialVersionUID = -5484709075767220358L;

	/** The entry order */
    private Order entry;

    /** The exit order */
    private Order exit;

    /** The type of the entry order */
    private OrderType startingType;

    /**
     * Constructor.
     */
    public Trade() {
        this(OrderType.BUY);
    }

    public Trade(OrderType startingType) {
        if (startingType == null) {
            throw new IllegalArgumentException("Starting type must not be null");
        }
        this.startingType = startingType;
    }





    public Order getEntry() {
        return entry;
    }


    public Order getExit() {
        return exit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Trade) {
            Trade t = (Trade) obj;
            return entry.equals(t.getEntry()) && exit.equals(t.getExit());
        }
        return false;
    }




    public Order operate(int index) {
        return operate(index, Decimal.NaN, Decimal.NaN);
    }

    public Order operate(int index, Decimal price, Decimal amount) {
        Order order = null;
        if (isNew()) {
            order = new Order(index, startingType, price, amount);
            entry = order;
        } else if (isOpened()) {
            if (index < entry.getIndex()) {
                throw new IllegalStateException("The index i is less than the entryOrder index");
            }
            order = new Order(index, startingType.complementType(), price, amount);
            exit = order;
        }
        return order;
    }


    public boolean isClosed() {
        return (entry != null) && (exit != null);
    }


    public boolean isOpened() {
        return (entry != null) && (exit == null);
    }

    public boolean isNew() {
        return (entry == null) && (exit == null);
    }

    @Override
    public String toString() {
        return "Entry: " + entry + " exit: " + exit;
    }
}

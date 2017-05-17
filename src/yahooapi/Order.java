package yahooapi;

import java.io.Serializable;


public class Order implements Serializable {

	//private static final long serialVersionUID = -905474949010114150L;


    public enum OrderType {

        BUY {
            @Override
            public OrderType complementType() {
                return SELL;
            }
        },
        SELL {
            @Override
            public OrderType complementType() {
                return BUY;
            }
        };


        public abstract OrderType complementType();
    }
    
    /** Type of the order */
    private OrderType type;

    /** The index the order was executed */
    private int index;

    /** The price for the order */
    private Decimal price = Decimal.NaN;
    
    /** The amount to be (or that was) ordered */
    private Decimal amount = Decimal.NaN;
    

    protected Order(int index, OrderType type) {
        this.type = type;
        this.index = index;
    }


    protected Order(int index, OrderType type, Decimal price, Decimal amount) {
        this(index, type);
        this.price = price;
        this.amount = amount;
    }

    public OrderType getType() {
        return type;
    }

    public boolean isBuy() {
        return type == OrderType.BUY;
    }

    public boolean isSell() {
        return type == OrderType.SELL;
    }

    public int getIndex() {
        return index;
    }

    public Decimal getPrice() {
        return price;
    }


    public Decimal getAmount() {
        return amount;
    }

    
}

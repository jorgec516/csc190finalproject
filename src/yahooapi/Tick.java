package yahooapi;


import java.io.Serializable;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Period;


public class Tick implements Serializable {

	private static final long serialVersionUID = 8038383777467488147L;
	/** Time period (e.g. 1 day, 15 min, etc.) of the tick */
    private Period timePeriod;
    /** End time of the tick */
    private DateTime endTime;
    /** Begin time of the tick */
    private DateTime beginTime;
    /** Open price of the period */
    private Decimal openPrice = null;
    /** Close price of the period */
    private Decimal closePrice = null;
    /** Max price of the period */
    private Decimal maxPrice = null;
    /** Min price of the period */
    private Decimal minPrice = null;
    /** Traded amount during the period */
    private Decimal amount = Decimal.ZERO;
    /** Volume of the period */
    private Decimal volume = Decimal.ZERO;
    /** Trade count */
    private int trades = 0;

    public Tick(Period timePeriod, DateTime endTime) {
        checkTimeArguments(timePeriod, endTime);
        this.timePeriod = timePeriod;
        this.endTime = endTime;
        this.beginTime = endTime.minus(timePeriod);
    }

    public Tick(DateTime endTime, double openPrice, double highPrice, double lowPrice, double closePrice, double volume) {
        this(endTime, Decimal.valueOf(openPrice),
                Decimal.valueOf(highPrice),
                Decimal.valueOf(lowPrice),
                Decimal.valueOf(closePrice),
                Decimal.valueOf(volume));
    }


    public Tick(DateTime endTime, String openPrice, String highPrice, String lowPrice, String closePrice, String volume) {
        this(endTime, Decimal.valueOf(openPrice),
                Decimal.valueOf(highPrice),
                Decimal.valueOf(lowPrice),
                Decimal.valueOf(closePrice),
                Decimal.valueOf(volume));
    }


    public Tick(DateTime endTime, Decimal openPrice, Decimal highPrice, Decimal lowPrice, Decimal closePrice, Decimal volume) {
        this(Days.days(1).toPeriod(), endTime, openPrice, highPrice, lowPrice, closePrice, volume);
    }


    public Tick(Period timePeriod, DateTime endTime, Decimal openPrice, Decimal highPrice, Decimal lowPrice, Decimal closePrice, Decimal volume) {
        checkTimeArguments(timePeriod, endTime);
        this.timePeriod = timePeriod;
        this.endTime = endTime;
        this.beginTime = endTime.minus(timePeriod);
        this.openPrice = openPrice;
        this.maxPrice = highPrice;
        this.minPrice = lowPrice;
        this.closePrice = closePrice;
        this.volume = volume;
    }


    public Decimal getClosePrice() {
        return closePrice;
    }


    public Decimal getOpenPrice() {
        return openPrice;
    }

    public int getTrades() {
        return trades;
    }


    public Decimal getMaxPrice() {
        return maxPrice;
    }

    public Decimal getAmount() {
        return amount;
    }

    public Decimal getVolume() {
        return volume;
    }

    public void addTrade(double tradeAmount, double tradePrice) {
        addTrade(Decimal.valueOf(tradeAmount), Decimal.valueOf(tradePrice));
    }


    public void addTrade(String tradeAmount, String tradePrice) {
        addTrade(Decimal.valueOf(tradeAmount), Decimal.valueOf(tradePrice));
    }

    public void addTrade(Decimal tradeAmount, Decimal tradePrice) {
        if (openPrice == null) {
            openPrice = tradePrice;
        }
        closePrice = tradePrice;

        if (maxPrice == null) {
            maxPrice = tradePrice;
        } else {
            maxPrice = maxPrice.isLessThan(tradePrice) ? tradePrice : maxPrice;
        }
        if (minPrice == null) {
            minPrice = tradePrice;
        } else {
            minPrice = minPrice.isGreaterThan(tradePrice) ? tradePrice : minPrice;
        }
        amount = amount.plus(tradeAmount);
        volume = volume.plus(tradeAmount.multipliedBy(tradePrice));
        trades++;
    }


    public Decimal getMinPrice() {
        return minPrice;
    }


    public Period getTimePeriod() {
        return timePeriod;
    }

    public DateTime getBeginTime() {
        return beginTime;
    }


    public DateTime getEndTime() {
        return endTime;
    }

    

    public boolean inPeriod(DateTime timestamp) {
        return timestamp != null
                && !timestamp.isBefore(beginTime)
                && timestamp.isBefore(endTime);
    }

    
    

    public String getDateName() {
        return endTime.toString("yyyy/MM/dd");
    }


    private void checkTimeArguments(Period timePeriod, DateTime endTime) {
        if (timePeriod == null) {
            throw new IllegalArgumentException("Time period cannot be null");
        }
        if (endTime == null) {
            throw new IllegalArgumentException("End time cannot be null");
        }
    }
}

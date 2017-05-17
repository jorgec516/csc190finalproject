package yahooapi;

import yahooapi.Order.OrderType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.joda.time.DateTime;
import org.joda.time.Interval;
import org.joda.time.Period;


public class TimeSeries implements Serializable {

    /** Name of the series */
    private final String name;
    /** Begin index of the time series */
    private int beginIndex = -1;
    /** End index of the time series */
    private int endIndex = -1;
    /** List of ticks */
    private final List<Tick> ticks;
    /** Maximum number of ticks for the time series */
    private int maximumTickCount = Integer.MAX_VALUE;
    /** Number of removed ticks */
    private int removedTicksCount = 0;
    /** True if the current series is a sub-series, false otherwise */
    private boolean subSeries = false;
    

    public TimeSeries(String name, List<Tick> ticks) {
        this(name, ticks, 0, ticks.size() - 1, false);
    }


    public TimeSeries(List<Tick> ticks) {
        this("unnamed", ticks);
    }

    public TimeSeries(String name) {
        this.name = name;
        this.ticks = new ArrayList<Tick>();
    }


    public TimeSeries() {
        this("unamed");
    }


    private TimeSeries(String name, List<Tick> ticks, int beginIndex, int endIndex, boolean subSeries) {
        // TODO: add null checks and out of bounds checks
        if (endIndex < beginIndex - 1) {
            throw new IllegalArgumentException("end cannot be < than begin - 1");
        }
        this.name = name;
        this.ticks = ticks;
        this.beginIndex = beginIndex;
        this.endIndex = endIndex;
        this.subSeries = subSeries;
    }


    public String getName() {
        return name;
    }


    public Tick getTick(int i) {
        int innerIndex = i - removedTicksCount;
        if (innerIndex < 0) {
            if (i < 0) {
                // Cannot return the i-th tick if i < 0
                throw new IndexOutOfBoundsException(buildOutOfBoundsMessage(this, i));
            }
            //log.trace("Time series `{}` ({} ticks): tick {} already removed, use {}-th instead", name, ticks.size(), i, removedTicksCount);
            if (ticks.isEmpty()) {
                throw new IndexOutOfBoundsException(buildOutOfBoundsMessage(this, removedTicksCount));
            }
            innerIndex = 0;
        } else if (innerIndex >= ticks.size()) {
            // Cannot return the n-th tick if n >= ticks.size()
            throw new IndexOutOfBoundsException(buildOutOfBoundsMessage(this, i));
        }
        return ticks.get(innerIndex);
    }


    public Tick getFirstTick() {
        return getTick(beginIndex);
    }


    public Tick getLastTick() {
        return getTick(endIndex);
    }


    public int getTickCount() {
        if (endIndex < 0) {
            return 0;
        }
        final int startIndex = Math.max(removedTicksCount, beginIndex);
        return endIndex - startIndex + 1;
    }


    public int getBegin() {
        return beginIndex;
    }
  
    public int getEnd() {
        return endIndex;
    }


    public void setMaximumTickCount(int maximumTickCount) {
        if (subSeries) {
            throw new IllegalStateException("Cannot set a maximum tick count on a sub-series");
        }
        if (maximumTickCount <= 0) {
            throw new IllegalArgumentException("Maximum tick count must be strictly positive");
        }
        this.maximumTickCount = maximumTickCount;
        removeExceedingTicks();
    }


    public int getMaximumTickCount() {
        return maximumTickCount;
    }


    public int getRemovedTicksCount() {
        return removedTicksCount;
    }


    public void addTick(Tick tick) {
        if (tick == null) {
            throw new IllegalArgumentException("Cannot add null tick");
        }
        final int lastTickIndex = ticks.size() - 1;
        if (!ticks.isEmpty()) {
            DateTime seriesEndTime = ticks.get(lastTickIndex).getEndTime();
            if (!tick.getEndTime().isAfter(seriesEndTime)) {
                throw new IllegalArgumentException("Cannot add a tick with end time <= to series end time");
            }
        }

        ticks.add(tick);
        if (beginIndex == -1) {
            // Begin index set to 0 only if if wasn't initialized
            beginIndex = 0;
        }
        endIndex++;
        removeExceedingTicks();
    }



    public TradingRec run(Strategy strategy) {
        return run(strategy, OrderType.BUY);
    }

    public TradingRec run(Strategy strategy, OrderType orderType) {
        return run(strategy, orderType, Decimal.NaN);
    }


    public TradingRec run(Strategy strategy, OrderType orderType, Decimal amount) {

        //log.trace("Running strategy: {} (starting with {})", strategy, orderType);
        TradingRec tradingRecord = new TradingRec(orderType);
        for (int i = beginIndex; i <= endIndex; i++) {
            // For each tick in the sub-series...       
            if (strategy.shouldOperate(i, tradingRecord)) {
                tradingRecord.operate(i, ticks.get(i).getClosePrice(), amount);
            }
        }

        if (!tradingRecord.isClosed()) {
            // If the last trade is still opened, we search out of the end index.
            // May works if the current series is a sub-series (but not the last sub-series).
            for (int i = endIndex + 1; i < ticks.size(); i++) {
                // For each tick out of sub-series bound...
                // --> Trying to close the last trade
                if (strategy.shouldOperate(i, tradingRecord)) {
                    tradingRecord.operate(i, ticks.get(i).getClosePrice(), amount);
                    break;
                }
            }
        }
        return tradingRecord;
    }


    private void removeExceedingTicks() {
        int tickCount = ticks.size();
        if (tickCount > maximumTickCount) {
            // Removing old ticks
            int nbTicksToRemove = tickCount - maximumTickCount;
            for (int i = 0; i < nbTicksToRemove; i++) {
                ticks.remove(0);
            }
            // Updating removed ticks count
            removedTicksCount += nbTicksToRemove;
        }
    }



    private static String buildOutOfBoundsMessage(TimeSeries series, int index) {
        return "Size of series: " + series.ticks.size() + " ticks, "
                + series.removedTicksCount + " ticks removed, index = " + index;
    }
}

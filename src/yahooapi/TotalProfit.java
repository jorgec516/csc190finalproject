package yahooapi;
public class TotalProfit extends Analysis {

    @Override
    public double calculate(TimeSeries series, TradingRec tradingRecord) {
        double value = 1d;
        for (Trade trade : tradingRecord.getTrades()) {
            value *= calculateProfit(series, trade);
        }
        return value;
    }

    @Override
    public double calculate(TimeSeries series, Trade trade) {
        return calculateProfit(series, trade);
    }

    @Override
    public boolean betterThan(double criterionValue1, double criterionValue2) {
        return criterionValue1 > criterionValue2;
    }

    private double calculateProfit(TimeSeries series, Trade trade) {
        Decimal profit = Decimal.ONE;
        if (trade.isClosed()) {
            Decimal exitClosePrice = series.getTick(trade.getExit().getIndex()).getClosePrice();
            Decimal entryClosePrice = series.getTick(trade.getEntry().getIndex()).getClosePrice();

            if (trade.getEntry().isBuy()) {
                profit = exitClosePrice.dividedBy(entryClosePrice);
            } else {
                profit = entryClosePrice.dividedBy(exitClosePrice);
            }
        }
        return profit.toDouble();
    }
}

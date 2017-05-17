package yahooapi;


public class Strategy {
    
    /** The entry rule */
    private Rule entryRule;
    
    /** The exit rule */
    private Rule exitRule;

    private int unstablePeriod;

    public Strategy(Rule entryRule, Rule exitRule) {
        if (entryRule == null || exitRule == null) {
            throw new IllegalArgumentException("Rules cannot be null");
        }
        this.entryRule = entryRule;
        this.exitRule = exitRule;
    }
    
    public boolean isUnstableAt(int index) {
        return index < unstablePeriod;
    }

    public void setUnstablePeriod(int unstablePeriod) {
        this.unstablePeriod = unstablePeriod;
    }
    
    public boolean shouldOperate(int index, TradingRec tradingRecord) {
        Trade trade = tradingRecord.getCurrentTrade();
        if (trade.isNew()) {
            return shouldEnter(index, tradingRecord);
        } else if (trade.isOpened()) {
            return shouldExit(index, tradingRecord);
        }
        return false;
    }

    public boolean shouldEnter(int index) {
        return shouldEnter(index, null);
    }

    public boolean shouldEnter(int index, TradingRec tradingRecord) {
        if (isUnstableAt(index)) {
            return false;
        }
        final boolean enter = entryRule.isSatisfied(index, tradingRecord);
        //traceShouldEnter(index, enter);
        return enter;
    }

    public boolean shouldExit(int index) {
        return shouldExit(index, null);
    }

    public boolean shouldExit(int index, TradingRec tradingRecord) {
        if (isUnstableAt(index)) {
            return false;
        }
        final boolean exit = exitRule.isSatisfied(index, tradingRecord);
        //traceShouldExit(index, exit);
        return exit;
    }

}

package yahooapi.rules;

import yahooapi.TradingRec;


public class BooleanRule extends AbstractRule {

    /** An always-true rule */
    public static final BooleanRule TRUE = new BooleanRule(true);
    
    /** An always-false rule */
    public static final BooleanRule FALSE = new BooleanRule(false);
    
    private boolean satisfied;

    /**
     * Constructor.
     * @param satisfied true for the rule to be always satisfied, false to be never satisfied
     */
    public BooleanRule(boolean satisfied) {
        this.satisfied = satisfied;
    }

    @Override
    public boolean isSatisfied(int index, TradingRec tradingRecord) {
        traceIsSatisfied(index, satisfied);
        return satisfied;
    }

    //@Override
    //public boolean isSatisfied(int index, TradingRecord tradingRecord) {
    //    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}
}

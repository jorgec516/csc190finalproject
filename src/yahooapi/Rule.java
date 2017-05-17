package yahooapi;


public interface Rule {

    Rule and(Rule rule);

    Rule or(Rule rule);

    Rule xor(Rule rule);

    Rule negation();

    boolean isSatisfied(int index);

    boolean isSatisfied(int index, TradingRec tradingRecord);
}

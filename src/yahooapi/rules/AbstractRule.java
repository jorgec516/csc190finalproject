
package yahooapi.rules;

import yahooapi.Rule;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

/**
 * An abstract trading {@link Rule rule}.
 */
public abstract class AbstractRule implements Rule {

    /** The logger */
    //protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override
    public Rule and(Rule rule) {
        return new AndRule(this, rule);
    }

    @Override
    public Rule or(Rule rule) {
        return new OrRule(this, rule);
    }

    @Override
    public Rule xor(Rule rule) {
        return new XorRule(this, rule);
    }

    @Override
    public Rule negation() {
        return new NotRule(this);
    }

    @Override
    public boolean isSatisfied(int index) {
        return isSatisfied(index, null);
    }
    
    /**
     * Traces the isSatisfied() method calls.
     * @param index the tick index
     * @param isSatisfied true if the rule is satisfied, false otherwise
     */
    protected void traceIsSatisfied(int index, boolean isSatisfied) {
        //log.trace("{}#isSatisfied({}): {}", getClass().getSimpleName(), index, isSatisfied);
    }
}

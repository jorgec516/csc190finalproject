package yahooapi;

import yahooapi.Indicator;
import yahooapi.TimeSeries;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class CIndicator<T> extends AIndicator<T> {

    
    private final List<T> results = new ArrayList<T>();

    protected int highestResultIndex = -1;
    
    public CIndicator(TimeSeries series) {
        super(series);
    }

    public CIndicator(Indicator indicator) {
        this(indicator.getTimeSeries());
    }

    @Override
    public T getValue(int index) {
        TimeSeries series = getTimeSeries();
        if (series == null) {

            return calculate(index);
        }

        // Series is not null
        
        final int removedTicksCount = series.getRemovedTicksCount();
        final int maximumResultCount = series.getMaximumTickCount();
        
        T result;
        if (index < removedTicksCount) {
            
            increaseLengthTo(removedTicksCount, maximumResultCount);
            highestResultIndex = removedTicksCount;
            result = results.get(0);
            if (result == null) {
                result = calculate(removedTicksCount);
                results.set(0, result);
            }
        } else {
            increaseLengthTo(index, maximumResultCount);
            if (index > highestResultIndex) {
                // Result not calculated yet
                highestResultIndex = index;
                result = calculate(index);
                results.set(results.size()-1, result);
            } else {
                // Result covered by current cache
                int resultInnerIndex = results.size() - 1 - (highestResultIndex - index);
                result = results.get(resultInnerIndex);
                if (result == null) {
                    result = calculate(index);
                }
                results.set(resultInnerIndex, result);
            }
        }
        return result;
    }

    
    protected abstract T calculate(int index);
    
    private void increaseLengthTo(int index, int maxLength) {
        if (highestResultIndex > -1) {
            int newResultsCount = Math.min(index-highestResultIndex, maxLength);
            if (newResultsCount == maxLength) {
                results.clear();
                results.addAll(Collections.<T> nCopies(maxLength, null));
            } else if (newResultsCount > 0) {
                results.addAll(Collections.<T> nCopies(newResultsCount, null));
                removeExceedingResults(maxLength);
            }
        } else {
            // First use of cache
            assert results.isEmpty() : "Cache results list should be empty";
            results.addAll(Collections.<T> nCopies(Math.min(index+1, maxLength), null));
        }
    }


    private void removeExceedingResults(int maximumResultCount) {
        int resultCount = results.size();
        if (resultCount > maximumResultCount) {
            // Removing old results
            final int nbResultsToRemove = resultCount - maximumResultCount;
            for (int i = 0; i < nbResultsToRemove; i++) {
                results.remove(0);
            }
        }
    }
}

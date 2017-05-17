package yahooapi;

import java.util.*;
import yahooapi.Rule;

import yahooapi.Strategy;
import yahooapi.TimeSeries;
import yahooapi.CPIndicator;
import yahooapi.AIndicator;
import yahooapi.rules.OverIndicatorRule;
import yahooapi.rules.UnderIndicatorRule;
import yahooapi.Tick;


public class Vividh implements StrategyExtend {
    
	public Vividh() 
	{
		
	}
	
	public Strategy buildStrategy(TimeSeries series)
        {   
            //int size = 10;
            /*
            Decimal sum = Decimal.ZERO;
            Decimal average = Decimal.ZERO;
            //double[]myArray=new double[size];
            for(int i=series.getTickCount()-10; i<series.getTickCount(); i++){
                    sum = sum.plus(series.getTick(i).getClosePrice());
            }
            average =sum.dividedBy(Decimal.TEN);
            
*/
            CPIndicator id = new CPIndicator(series);
            
            SMAIndicator sma = new SMAIndicator(id, 10);
            
            Rule entryRule; //entry rule for long positions
            Rule exitRule; //exit rule for long positions
            
            entryRule = new OverIndicatorRule(id, sma);
            exitRule = new UnderIndicatorRule(id, sma);
		
            Strategy strategy = new Strategy(entryRule, exitRule);
            strategy.setUnstablePeriod(10);
            return strategy;
  
        }
}

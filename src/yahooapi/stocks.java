package yahooapi;

import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import org.joda.time.*;
import yahooapi.Order.OrderType;
import yahooapi.TotalProfit;
import yahooapi.AIndicator;
import yahooapi.CPIndicator;
import java.lang.reflect.Constructor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;






public class stocks {
      

    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Enter stock ticker: ");
<<<<<<< HEAD
        String stockTicker = args[0];           //ONCE '/home/csc190/Desktop/Vividh.java' 2016-01-10 2017-05-10
        String strategyFile = args[1];
        String startDate = args[2];
        String endDate = args[3];
        
        YahooAPI run = new YahooAPI(stockTicker);//input is the stock ticker in order to download
       //YahooAPI run = new YahooAPI("GOOG");//test
       //YahooAPI run;
       //
       //
    //}	
                
                try{
                String myDriver = "org.gjt.mm.mysql.Driver";
                String myUrl = "jdbc:mysql://localhost/final";
                Class.forName(myDriver);
                Connection conn = DriverManager.getConnection(myUrl, "root", "goodyear123!@#");
                
                
                
                
                
                
                String query = "SELECT * FROM stockHistory where dates>'"+startDate+"'AND dates<'"+endDate+"' order by dates;";
=======
        String stockTicker = args[0];
        YahooAPI run = new YahooAPI(stockTicker);//input is the stock ticker in order to download
       //YahooAPI run = new YahooAPI("GOOG");//test
    }
}//end stocks
>>>>>>> 77ccbd0c3c19ee239da48c2ba36b50ff81d536ee


                Statement st = conn.createStatement();
      

                ResultSet rs = st.executeQuery(query);
                
                ArrayList<Tick> tickArr = new ArrayList<Tick>();
                
                while (rs.next())
                {
                  int id = rs.getInt("id");
                  Date dates = rs.getDate("dates");
                  Double opens = Double.valueOf(rs.getString("opens"));
                  Double highs = Double.valueOf(rs.getString("highs"));
                  Double lows = Double.valueOf(rs.getString("lows"));
                  Double volumes = Double.valueOf(rs.getString("volumes"));
                  Double adjClose = Double.valueOf(rs.getString("adjClose"));
                 
                  
                
                  DateTime dt = new DateTime(dates);
                  Tick temp = new Tick(dt, opens, highs, lows, adjClose, volumes);
                  tickArr.add(temp);

                }
                st.close();
              

		TimeSeries series = new TimeSeries(tickArr);

                //Class cl = Class.forName(strategyFile);
                //Constructor cn = cl.getConstructor();
                //Object strat = cn.newInstance();
                //StrategyExtend ai =  (StrategyExtend)strat;
                
                StrategyExtend ai = new Vividh();
                
		Strategy x = ai.buildStrategy(series);
				
		TradingRec tradingrecord = series.run(x);
		
                
                System.out.println("\n\n\n\n\n");
                
                
		System.out.println("number of trades for the strategy is " + tradingrecord.getTradeCount());
		
		System.out.println("Total profit for the strategy is " + new TotalProfit().calculate(series, tradingrecord));

		
		System.out.println("\n\n\n\n\n");
	                
                TimeSeries ss = new TimeSeries();
		TradingRec records = new TradingRec();
		Strategy k = ai.buildStrategy(ss);
		
                Tick[] ticks = new Tick[series.getTickCount()];
               
                for(int qqq = 0; qqq < series.getTickCount(); qqq++)
                {
                    ticks[qqq] = series.getTick(qqq);
                    System.out.println("Date is " + ticks[qqq].getDateName() + " closing price is " + ticks[qqq].getClosePrice());
                }
                
		for(int i = 0; i < ticks.length; i++)
		{
			ss.addTick(ticks[i]);
			int endIndex = ss.getEnd();
			if(k.shouldEnter(endIndex))
			{
				System.out.println("System should enter a position");
				boolean entered = records.enter(endIndex, ticks[i].getClosePrice(), Decimal.TEN);
				if(entered)
				{
					Order entry = records.getLastEntry();
					System.out.println("Entered on index " + entry.getIndex() + " price = " + entry.getPrice());
				}
			}
			else if(k.shouldExit(endIndex))
			{
				System.out.println("System should exit the position");
				boolean exited = records.exit(endIndex, ticks[i].getClosePrice(), Decimal.TEN);
				if(exited)
				{
					Order exit = records.getLastExit();
					System.out.println("Exited on index " + exit.getIndex() + " price = " + exit.getPrice());
				}
			}
			if(i == ticks.length - 1)
			{
				System.out.println("System should exit the position. Reached end");
				boolean exited = records.exit(endIndex, ticks[i].getClosePrice(), Decimal.TEN);
				if(exited)
				{
					Order exit = records.getLastExit();
					System.out.println("Exited on index " + exit.getIndex() + " price = " + exit.getPrice() + " amount =  " + exit.getAmount().toDouble());
				}
			}
		}
		

                }
                catch (Exception e)
              {
                System.err.println("Got an exception! ");
                System.err.println(e.getMessage());
                e.printStackTrace();
              }
    	
    }
       
}
   

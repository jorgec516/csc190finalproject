package yahooapi;

import java.util.GregorianCalendar;//used to help in formatting dates 
import java.util.ArrayList;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.sql.*;
import java.util.Calendar;
import java.sql.*;
<<<<<<< HEAD


=======
/**
 *
 * @author JorgeContreras
 * @coauthor AlexSpeicher
 */
>>>>>>> 77ccbd0c3c19ee239da48c2ba36b50ff81d536ee
public class YahooAPI {
//these variables diclose will hold information retreived form yahoo api so once downloaded
    //we dont need an internet connection
    public static final int DATE = 0;
    public static final int OPEN = 1;
    public static final int HIGH = 2;
    public static final int LOW = 3;
    public static final int CLOSE = 4;
    public static final int ADJCLOSE = 6;
    public static final int VOLUME = 5;
    
    private ArrayList<String> dates;
    private ArrayList<String> opens;
    private ArrayList<String> highs;
    private ArrayList<String> lows;
    private ArrayList<String> closes;
    private ArrayList<String> volumes;
    private ArrayList<String> adjCloses;
    
    
    protected static void uploadHistory(String d, String o, String h,String l,String c,String v,String a){
        try{
            String myDriver = "org.gjt.mm.mysql.Driver";
            String myUrl = "jdbc:mysql://localhost/final";
            Class.forName(myDriver);
            Connection conn = DriverManager.getConnection(myUrl, "root", "goodyear123!@#");
            
            String query = "insert into stockHistory (dates,opens,highs,lows,closes,volumes,adjClose)"+" values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, d);
            preparedStmt.setString(2, o);
            preparedStmt.setString(3, h);
            preparedStmt.setString(4, l);
            preparedStmt.setString(5, c);
            preparedStmt.setString(6, v);
            preparedStmt.setString(7, a);
            
            preparedStmt.execute();
            conn.close();
        }
        catch (Exception e){
            System.err.println("Got an exception");
            System.err.println(e.getMessage());
        }
        //String qry = "INSERT INTO stockHistory(dates,opens,highs,lows,closes,volumes,adjClose)"+ "VALUES("+d+","+o+","+h+","+l+","+c+","+v+","+a+")";
        //System.out.println(qry);
        //Utils.execNonQuery(qry);
        
        
       
    }
  
    public YahooAPI(String symbol){
        dates = new ArrayList<>();
        opens = new ArrayList<>();
        highs = new ArrayList<>();
        lows = new ArrayList<>();
        closes = new ArrayList<>();
        volumes = new ArrayList<>();
        adjCloses = new ArrayList<>();
        
       
      //url to get all stock history given ticket ID
         String url = "https://ichart.yahoo.com/table.csv?s="+symbol;//this will get all stock data history given stock symbol 
          //System.out.println(url);//prints piced stock url
                try{
                    URL yahoofinance = new URL(url);
                    URLConnection data = yahoofinance.openConnection();
                     InputStreamReader input = new InputStreamReader(data.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(input);
                    //bufferedReader.readline();
                    String line;
                    int i=0;
                    while ((line = bufferedReader.readLine()) != null) {
                        if(i>0){
                            String[] lineparts = line.split(",");
                            dates.add(lineparts[0]);
                            opens.add(lineparts[1]);
                            highs.add(lineparts[2]);
                            lows.add(lineparts[3]);
                            closes.add(lineparts[4]);
                            volumes.add(lineparts[5]);
                            adjCloses.add(lineparts[6]);
                            //System.out.println(Arrays.toString(lineparts));//Uncomment to see lines represnted as array
                        }
                        else{
                            i+=1;
                        }//ALEX job parse file for commas to call my function to input it in database
                    }
                    bufferedReader.close();
                    input.close();
                    for (int x=0; x<dates.size();x++){
                        String date = new String(dates.get(x));
                        String open = new String(opens.get(x));
                        String high = new String(highs.get(x));
                        String low = new String(lows.get(x));
                        String close = new String(closes.get(x));
                        String volume = new String(volumes.get(x));
                        String adj = new String(adjCloses.get(x));
                        uploadHistory(date,open,high,low,close,volume,adj);
                        System.out.println(date+" "+open+" "+high+" "+low+" "+close+" "+volume+" "+adj); //Uncomment to see whats being parsed into upoadhistory 
                    }
                    
                        
                }
                catch(Exception e){
                        System.err.println(e);
                }   
    }//constructor
    
  
    
}//end of class

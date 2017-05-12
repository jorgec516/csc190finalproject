/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahooapi;

import java.util.GregorianCalendar;//used to help in formatting dates 
import java.util.ArrayList;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
/**
 *
 * @author JorgeContreras
 */
public class YahooAPI {
//these variables diclose will hold information retreived form yahoo api so once downloaded
    //we dont need an internet connection
    public static final int DATE = 0;
    public static final int OPEN = 1;
    public static final int HIGH = 2;
    public static final int LOW = 3;
    public static final int CLOSE = 4;
    public static final int ADJCLOSE = 5;
    public static final int VOLUME = 6;
    
    private ArrayList<GregorianCalendar> dates;
    private ArrayList<Double> opens;
    private ArrayList<Double> highs;
    private ArrayList<Double> lows;
    private ArrayList<Double> closes;
    private ArrayList<Integer> volumes;
    private ArrayList<Double> adjCloses;
  
    public YahooAPI(String symbol){
        dates = new ArrayList<GregorianCalendar>();
        opens = new ArrayList<Double>();
        highs = new ArrayList<Double>();
        lows = new ArrayList<Double>();
        closes = new ArrayList<Double>();
        volumes = new ArrayList<Integer>();
        adjCloses = new ArrayList<Double>();
        
       
      //url to get all stock history given ticket ID
         String url = "https://ichart.yahoo.com/table.csv?s="+symbol;//this will get all stock data history given stock symbol 
          System.out.println(url);//testing
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
                            
                            System.out.println(Arrays.toString(lineparts));
                        }
                        else{
                            i+=1;
                        }//ALEX job parse file for commas to call my function to input it in database
                    }
                    bufferedReader.close();
                    input.close();
                   
                        
                }
                catch(Exception e){
                        System.err.println(e);
                }   
    }//constructor
    
  
    
}//end of class

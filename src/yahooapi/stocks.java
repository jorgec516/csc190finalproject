/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yahooapi;

/**
 *
 * @author JorgeContreras
 */
public class stocks {
    
    
     
    /**
     * this function 
     */
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println("Enter stock ticker: ");
        String stockTicker = args[0];
        YahooAPI run = new YahooAPI(stockTicker);//input is the stock ticker in order to download
       //YahooAPI run = new YahooAPI("GOOG");//test
    }
}//end stocks



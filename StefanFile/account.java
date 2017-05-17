public class Account{

        protected int profit=0;
        protected boolean own=false;
        protected double transactionFee=0.01;

        public buy(){
                own=true;
                profit=profit-stock.price();
		profit=profit*transactionFee+profit;
        }

        public sell(){
                if(own=true){
			own=false;   
                	profit=profit+stock.price();
			profit=profit*transactionFee+profit;
		}
	}
        
	public getProfit{
               	return profit; 
        }
	
	public getTotal{
		return total;
	}
               

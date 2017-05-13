import java.util*;

public class TenDayMovingAverage{
	int final arraySize = 10;
	double[] values=new double[10];
	double sum=0.00;
	double average=0.00;

	String startDate; //yyyy-mm-dd
	String endDate;
	
	Calendar start = Calender.getInstance();
	start.setTime(startDate);
	Calendar end = Calender.getInstance();
	end.setTime(endDate);

	int differnce=endDate.getTime()-startDate.getTime();//number of times have to add to array. 
	
	ArrayList<String>allStocks=new ArrayList<String>();
	ResultSet rs=statement.excuteQuery(sqlQry);
	ResultSetMetaData rsmd= rs.getMetaData();
	columnNumber=rsmd.getColumnCount();
	while(rs.next()){
		ArrayList<String>inner=new ArrayList<String>();
		for(int i=1;i<=columnNumber;i++){
	
			System.println()
		}
		outer.add(inner);
	}	
	//put all stock information into an 2d arrayList
		
	}


	average=sum/size;
	return average;	
}

public implementation(average){
	
	if(current<average){
		stock.buy();
	}
		
	if(own=true && average>current){
		stock.sell();			
	}
}

	public shiftArrayLeft(array[],int num){
		for(int i=0;i<array.lenght-2;i++);
			array[i]=array[i+1];	
		}
		if(array.lenght>9)
			array[9]=num;
		else
			array[array.length-1]=num;
		return array;
	}

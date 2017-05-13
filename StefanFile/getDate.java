//TEST


public class getDates{
	//year-month-date;
	//yyyy-mm-dd
	int startDate;
	int endDate;

	
	public static void givenDates(String val){
	String qry = "SELECT dates FROM stockHistory where dates=" + val;
	Utils.execNonQuery(qry);
	}

	
}

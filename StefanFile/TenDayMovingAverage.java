public class TenDayMovingAverage{
	int final size=10;
	double sum=0.0;
	double average=0.0;
	double[]myArray=new double[size];
	for(int i=0; myArray.lenght-1;i++){
		sum=sum+myArray[i];
	}
	average=sum/size;
	System.out.print("average:" + average)
}

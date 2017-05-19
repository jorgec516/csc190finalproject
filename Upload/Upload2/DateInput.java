import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.List;

public class DateInput {
    
    public static void main(String[] args)
    {
        //$ Upload filename "yyyy-mm-dd yyyy-mm-dd"
        String str = args[0];
        System.out.println("arg is: "+args[0]);
        List <String> tokens = Arrays.asList(str.split("\\s+"));
        String stringStartDate = tokens.get(0);
        String stringCloseDate = tokens.get(1);
        
        //System.out.println("String start date:"+stringStartDate);
        //System.out.println("String close date:"+stringCloseDate);
        
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
           Date Startdate = format.parse(stringStartDate);
           Date Closedate = format.parse(stringCloseDate);
           System.out.println(Startdate);
           System.out.println(Closedate);

        } catch (Exception e) {}
    }
}

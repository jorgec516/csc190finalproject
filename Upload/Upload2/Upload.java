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

public class Upload {
    
    private static void printLines(String name, InputStream reader) throws Exception {
        String line = null;
        BufferedReader in = new BufferedReader(new InputStreamReader(reader));
        while ((line = in.readLine()) != null) {
            System.out.println(name + " " + line);
        }
    }
    
    private static void runProgram(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }
    
    
   public static void main(String[] args) throws IOException {
       
        //System.out.print("Upload Your Trading Algorithm: ");
        //System.out.println("Please Enter in file without .java ending");
        String file = args[0];
        //BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String file = reader.readLine();
        System.out.println("file name is:"+file);
        try {
            runProgram("javac "+file+".java");
            runProgram("java "+file);
        } catch (Exception e) {
            e.printStackTrace();
        }

    } 
            
}

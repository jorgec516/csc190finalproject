import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.Arrays;
import java.io.*;
import java.util.*;
import java.util.Map.*;

public class upload3  {
  
  public static void main(String[] args)throws Exception{

    //ClassLoader classLoader = upload3.class.getClassLoader();
   //BufferedReader in = new BufferedReader(new InputStreamReader(reader));
    String file = args[0]; //read class from command line "upload "class"
    try {
        //Dynamically load class
        Method method = upload3.getClass().getMethod(file, null);
        method.invoke(foo,null);
        
        Class aClass = classLoader.loadClass(file);
        System.out.println("aClass.getName() = " + aClass.getName());
        System.out.println("file");
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
     List<String> command = new ArrayList<String>();
      command.add(file);
      ProcessBuilder builder = new ProcessBuilder(command);
      Map<String, String> eviron = builder.environment();
      
      final Process process = builder.start();
      InputStream is = process.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String line;
      while ((line = br.readLine()) != null) {
          System.out.println(line);
      }
      System.out.println("Program Terminated");

      
     /* Method meth = upload3.class.getMethod(args[0], int.class);
      int result = (Integer) meth.invoke(new upload3(), 100);
      System.out.println(result); */
  }
}
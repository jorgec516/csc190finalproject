/**
 *
 * @author StevenPremus
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.*;
import java.util.Arrays;

public class upload2 {
    public class ReflectionExample {
        public int test(int i) {
            return i + 1;
        }
    }
    public static void DynamicLoad(String filename){
        //System.out.print("Enterfile to Upload: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ClassLoader cl = upload2.class.getClassLoader();
        filename = "test";
        try {
            Class c = cl.loadClass(filename);
            System.out.println("c.getName() = " + c.getName());
        } catch (Exception e) {
            System.out.println("Exception: " + e.toString());
        }
    }
    
 /*   public static void invokeMain(){

    }

*/
    public static void main(String[] args) throws IOException {
        //String myFile = args[0];
        DynamicLoad(args[0]);
        /* try {
            Class<?> c = Class.forName("test");
            Class[] argType = new Class[] { String[].class };
            Method main = c.getDeclaredMethod("main", argType);
            String[] mainArg = Arrays.copyOfRange(args, 1, args.length);
            System.out.format("invoking %s.main()%n", c.getName());
            main.invoke(null, (Object) mainArg);
            
            // production code should handle these exceptions more gracefully
        } catch (ClassNotFoundException x) {
            x.printStackTrace();
        } catch (NoSuchMethodException x) {
            x.printStackTrace();
        } catch (IllegalAccessException x) {
            x.printStackTrace();
        } catch (InvocationTargetException x) {
            x.printStackTrace();
        } */
        //Method method = VehicleConfiguration.class.getMethod("main");
        //Class<?> c = Class.forName("test");
        //Method  method = c.getDeclaredMethod ("main", String [] args);
        //method.invoke (objectToInvokeOn, params);
    }
}
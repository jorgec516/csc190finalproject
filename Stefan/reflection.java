import java.lang.reflect.*;
import  stocks.java;


public class Reflection{
	Strategy strategyFile = new Strategy(); // get the strategy file
	Class cls = obj.getClass(); //get the class names in the file
        System.out.println("The name of class is " + cls.getName());

	Constructor constructor = cls.getConstructor(); //get the constructor
        System.out.println("The name of constructor is " + constructor.getName());
 
        System.out.println("The public methods of class are : ");

	Method[] methods = cls.getMethods(); // get the methods

	for (Method fileMethods:methods){
        	System.out.println(fileMethods.getName());
		fileMethods;
	}
	
	Method methodcall = cls.getDeclaredMethod("method2", int.class);

	methodcall1.invoke(obj, 19);

	Field field = cls.getDeclaredField("s");

	field.setAccessible(true);

	int classModifiers = reflectClass.getModifiers();
	Modifier[] publicMods=Modifier.isPublic(classModifiers);
	Modifier[] privateMods=Modifier.isPrivate(classModifiers);
	Modifier[] protectedMods=Modifier.isProtected(classModifiers);
	Modifier[] interfaceMods=Modifier.isInterface(classModifiers);


public static void main(String[] args){
	String stockTicker = args[0];
	String startegyFile = args[1];
	String startDate = args[2];
	String endDate = args[3];
	}
}

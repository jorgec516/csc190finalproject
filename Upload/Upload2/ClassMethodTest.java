import java.lang.reflect.Method;

public class ClassMethodTest {

	public static void main(String args[]) throws Exception {

		Testing t = new Testing("val1", false);
		Class tClass = t.getClass();

		Method gs1Method = tClass.getMethod("getString1", new Class[] {});
		String str1 = (String) gs1Method.invoke(t, new Object[] {});
		System.out.println("getString1 returned: " + str1);

		Method ss1Method = tClass.getMethod("setString1", new Class[] { String.class });
		System.out.println("calling setString1 with 'val2'");
		ss1Method.invoke(t, new Object[] { "val2" });

		str1 = (String) gs1Method.invoke(t, new Object[] {});
		System.out.println("getString1 returned: " + str1);

	}
}

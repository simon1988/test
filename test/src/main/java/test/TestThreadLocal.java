package test;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TestThreadLocal {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ThreadLocal<String> threadLocal = new ThreadLocal<String>();
		threadLocal.set("");
		System.out.println(new SimpleDateFormat("yyyy-mm-dd hh:mm:ss").format(new Date()));
		Field[] fields = Hello.class.getDeclaredFields();
		for(Field field : fields){
			Class<?> fieldType = field.getType();
			fieldType.isPrimitive();
			System.out.println(fieldType.getName());
			if(fieldType.isAssignableFrom(java.util.List.class)){
				Type fc = field.getGenericType();
				System.out.println(fc);
			}
		}
	}

}

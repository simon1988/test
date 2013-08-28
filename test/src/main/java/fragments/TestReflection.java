package fragments;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestReflection {
	private String hello;

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
	
	private static void testReflection(){
		
		TestReflection test = new TestReflection();
		test.setHello("hello!");
		try {
			Field hello = test.getClass().getDeclaredField("hello");
			System.out.println(hello.get(test));
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		
		Field[] fields = TestReflection.class.getDeclaredFields();
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
	
	public static void main(String[] args) {
		testReflection();
	}

}

package fragments;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

public class TestReflection {
	private String hello;

	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
	
	private void testReflection(){

		
		try {
			Method method = this.getClass().getDeclaredMethod("setHello", String.class);
			method.invoke(this, "hello");
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		try {
			Field hello = this.getClass().getDeclaredField("hello");
			System.out.println(hello.get(this));
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
		TestReflection instance = new TestReflection();
		instance.testReflection();
	}

}

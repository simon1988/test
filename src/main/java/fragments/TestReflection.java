package fragments;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class TestReflection<T> {
	
	private String hello;
	private T tvar;
	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}
	
	public T getTvar() {
		return tvar;
	}

	public void setTvar(T tvar) {
		this.tvar = tvar;
	}

	private void testReflection() throws Exception {
		Method method = this.getClass().getDeclaredMethod("setHello", String.class);
		System.out.println("setHello para type is String:"+(method.getParameterTypes()[0]==String.class));
		method.invoke(this, "hello");

		Field hello = this.getClass().getDeclaredField("hello");
		System.out.println("hello field value:"+hello.get(this));
		
		Field[] fields = TestReflection.class.getDeclaredFields();
		for(Field field : fields){
			Class<?> fieldType = field.getType();
			if(fieldType.isPrimitive()){
				System.out.println("Primitive type field:"+fieldType.getName());
			}
			if(java.util.List.class.isAssignableFrom(fieldType)){
				Type fc = field.getGenericType();
				System.out.println("List type field:"+fc);
			}
		}
		for(TypeVariable<?> type : this.getClass().getTypeParameters()){
			System.out.println("generic type name:"+type.getName());
		}
	}
	
	public static void main(String[] args) throws Exception {
		TestReflection<Integer> instance = new TestReflection<Integer>();
		instance.testReflection();
	}

}

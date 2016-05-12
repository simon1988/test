package fragments;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.List;
import java.util.Map;

public class TestReflection<T> {
	
	private Map<String , Integer> map;
	private List<String> list;
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

	public Map<String , Integer> getMap() {
		return map;
	}

	public void setMap(Map<String , Integer> map) {
		this.map = map;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
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
			}else if(java.util.List.class.isAssignableFrom(fieldType)){
				Type type = field.getGenericType();
				System.out.println("List type field "+field.getName()+":"+type);
			}else{
				System.out.println(field.getName()+":"+fieldType.toGenericString());
			}
		}
		for(TypeVariable<?> type : this.getClass().getTypeParameters()){
			System.out.println("generic type name:"+type.getName());
		}

		System.out.println("generic types for map is:");
		Field mapField = this.getClass().getDeclaredField("map");
		ParameterizedType mapType = (ParameterizedType)mapField.getGenericType();
		for(Type type: mapType.getActualTypeArguments()){
			System.out.println(type);
		}
	}
	
	public static void main(String[] args) throws Exception {
		TestReflection<Integer> instance = new TestReflection<Integer>();
		instance.testReflection();
	}

}

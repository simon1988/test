package fragments;

import java.lang.annotation.*;
import java.lang.reflect.Method;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface UseCase{
	public int id() default -1;
	public String description();
}

class Model{
	@UseCase(description = "zhu a zhu")
	public void do1(){
		
	}
	@UseCase(id=0,description = "gou a gou")
	public void do2(){
		
	}
}
public class TestAnnotation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(Method m : Model.class.getDeclaredMethods()){
			System.out.println(m.getAnnotation(UseCase.class));
		}
	}

}

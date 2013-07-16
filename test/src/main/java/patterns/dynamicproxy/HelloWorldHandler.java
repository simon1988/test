package patterns.dynamicproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class HelloWorldHandler implements InvocationHandler {

	private HelloWorld hw;
	public HelloWorldHandler(HelloWorld hw){
		this.hw = hw;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("before hello "+method.getName());
		try{
		method.invoke(hw, args);
		}catch(Exception e){
			e.printStackTrace();
			System.exit(0);
		}
		System.out.println("after hello");
		return null;
	}

}

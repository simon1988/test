package patterns.dynamicproxy;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HelloWorld hw = new HelloWorldImpl();
		HelloWorldHandler handler = new HelloWorldHandler(hw);
		HelloWorld proxy = (HelloWorld)java.lang.reflect.Proxy.newProxyInstance(
				hw.getClass().getClassLoader(), hw.getClass().getInterfaces(), handler);
		proxy.sayHello();
	}

}

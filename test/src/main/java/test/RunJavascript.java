package test;
import javax.script.*;

import sun.org.mozilla.javascript.*;
public class RunJavascript {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ScriptEngineManager factory=new ScriptEngineManager();
		for (ScriptEngineFactory available : factory.getEngineFactories()) {
			System.out.println(available.getEngineName());
		}  
		ScriptEngine engine=factory.getEngineByName("javascript");
		try {
			engine.eval("print('hello world')");
			System.out.println();
			String script = "function say(first,second) { print(first +' '+ second); }";
			engine.eval(script);
			Invocable inv = (Invocable) engine;
			inv.invokeFunction("say", "Hello", "Tony");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

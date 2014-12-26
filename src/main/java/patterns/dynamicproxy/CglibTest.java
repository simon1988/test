package patterns.dynamicproxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibTest {
 
    public abstract static class Foo {
 
        public String doSomething() {
            return "I did something";
        }
    }
 
    private static class MethodInterceptorImpl implements MethodInterceptor {
 
        public MethodInterceptorImpl() {
        }
 
        @Override
        public Object intercept(Object o, Method method, Object[] os, MethodProxy mp) throws Throwable {
            return "I did nothing";
        }
    }
 
    public static void main(String args[]) {
 
        Callback callback = new MethodInterceptorImpl();
 
        Foo created = (Foo) Enhancer.create(Foo.class, callback);
        
        System.out.println(created.getClass());
 
        String doSomething = created.doSomething();
 
        System.out.println(doSomething);
 
    }
}
package proxy;

import java.lang.reflect.Method;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.junit.Test;
import static org.junit.Assert.*;
 
/**
 *
 * @author shaman
 */
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
 
    @Test
    public void testEnhance() {
 
        Enhancer enhancer = new Enhancer();
 
        Callback callback = new MethodInterceptorImpl();
 
        Foo created = (Foo) enhancer.create(Foo.class, callback);
        
        System.out.println(created.getClass());
 
        String doSomething = created.doSomething();
 
        assertEquals("I did nothing", doSomething);
 
 
 
    }
}
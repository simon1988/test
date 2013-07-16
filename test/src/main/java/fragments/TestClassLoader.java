package fragments;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

class tcl extends ClassLoader{
	public Class<?> findClass(String name)
	{
		byte[] data = null;
	    try {
			data = loadClassData(name);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return defineClass("test.Test", data, 0, data.length);
	}
	   private byte[] loadClassData(String name) throws IOException {
	        String filePath = "C:\\Users\\xniu1\\workspace\\test\\test\\Test.class";
	        System.out.println(filePath);
	        if (!(filePath == null || filePath == "")) {
	            FileInputStream inFile = new FileInputStream(filePath);
	            byte[] classData = new byte[inFile.available()];
	            inFile.read(classData);
	            inFile.close();
	            return classData;
	        }
	        System.out.println("1");
	        return null;
	    }
}
public class TestClassLoader {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			tcl tc = new tcl();
			Class<?> c=tc.loadClass("whatever");
			for(Method m : c.getDeclaredMethods()){
				System.out.println(m.getName());
			}
			System.out.println(TestClassLoader.class.getClassLoader());
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println(System.getProperty("java.ext.dirs"));
		System.out.println(Thread.currentThread().getContextClassLoader().getParent());
		System.out.println(Thread.currentThread().getContextClassLoader().getResource(".").getPath());
		System.out.println(TestClassLoader.class.getResource(".").getPath());
	}

}

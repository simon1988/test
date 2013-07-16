package test;
import java.util.*;

public class RuntimeConstantPoolOOM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		int i = 0;
		try {
			Thread.sleep(8000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while(true){
			String s=(""+(new Random().nextInt(100)));
			list.add(s);
		}
	}

}

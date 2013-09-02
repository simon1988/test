package fragments;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

public class TestClone{

	public int a;
	
	@Override
	public TestClone clone(){
		TestClone newOne = new TestClone();
		newOne.a = this.a;
		return newOne;
	}
	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		double d1=11.1;
		double d2=0.2;
		
		BigDecimal bd1=new BigDecimal(0.1);
		BigDecimal bd2=new BigDecimal(0.2);
		
		System.out.printf("%4.2f\n",d1+d2);
		System.out.println(bd1.add(bd2));
		
		ArrayList<TestClone> list =new ArrayList<TestClone>();
		TestClone a = new TestClone();
		a.a=1;
		list.add(a.clone());
		a.a=2;
		list.add(a);
		for(TestClone t : list)
		System.out.println(t.a);
		
		
		ArrayList<String> list2 =new ArrayList<String>();
		String s = new String("a");
		list2.add(s);
		s="b";
		list2.add(s);
		for(String t : list2)
		System.out.println((Object)t);
		
		String[] strings={"a","b"};
		ArrayList<String> strList=new ArrayList<String>(Arrays.asList(strings));
		strList.add("zhu");
		System.out.println(strList);
	}

}

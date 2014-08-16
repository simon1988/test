package fragments;

import java.util.ArrayList;
import java.util.Collections;

public class TestComparable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		TestExtends1 te1 = new TestExtends2(1);
		if(TestExtends2.class.isInstance(te1)){
			System.out.println(te1.value);
		}
		
		ArrayList<TestExtends2> list = new ArrayList<TestExtends2>();
		list.add(new TestExtends3(7));
		list.add(new TestExtends2(9));
		Collections.sort(list);
		for(TestExtends2 te : list){
			System.out.println(te.value);
		}
	}

}
class TestExtends1 implements Comparable<TestExtends1>{
	public int value;
	public TestExtends1(int i){
		value=i;
	}
	@Override
	public int compareTo(TestExtends1 o) {
		return o.value-this.value;
	}
}
class TestExtends2 extends TestExtends1{
	public TestExtends2(int i){
		super(i);
	}
}
class TestExtends3 extends TestExtends2{
	public TestExtends3(int i){
		super(i);
	}
}

package fragments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class TestComparator {
	public static void main(String args[]){
		ArrayList<DoubleString> list=new ArrayList<DoubleString>();
		list.add(new DoubleString("a","b"));
		list.add(new DoubleString("c","a"));
		list.add(new DoubleString("b","c"));
		Comparator<DoubleString> com1 = (DoubleString s1, DoubleString s2) -> s1.s1.compareTo(s2.s1);
		Comparator<DoubleString> com2 = (DoubleString s1, DoubleString s2) -> {return s1.s2.compareTo(s2.s2);};
		Collections.sort(list,com1);
		System.out.println("sort by com1:");
		for(DoubleString ds:list)System.out.printf("%s,%s\n", ds.s1,ds.s2);
		Collections.sort(list,com2);
		System.out.println("sort by com2:");
		for(DoubleString ds:list)System.out.printf("%s,%s\n", ds.s1,ds.s2);
		System.out.printf("index for a,b is %d",
				Collections.binarySearch(list, new DoubleString("a","b"),com2));
	}
}
class DoubleString{
	public DoubleString(String s1,String s2){
		this.s1=s1;
		this.s2=s2;
	}
	public String s1;
	public String s2;
}

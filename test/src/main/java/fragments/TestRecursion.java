package fragments;

import java.util.*;

public class TestRecursion {

	/**
	 * @author Simon.Niu
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer,String> testMap=new HashMap<Integer,String>();
		testMap.put(1, "zfu");
		testMap.put(2, "zvu");
		testMap.put(0, "zcu");
		List<Map.Entry<Integer,String>> list=new ArrayList<Map.Entry<Integer,String>>();
		list.addAll(testMap.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, String>>(){
			public int compare(Map.Entry<Integer, String> s1,Map.Entry<Integer, String> s2){
				if(s1.getValue().charAt(1)<s2.getValue().charAt(1))return -1;
				else if(s1.getValue().charAt(1)==s2.getValue().charAt(1))return 0;
				else return 1;
			}
		});
		for(Map.Entry<Integer,String> s:list)System.out.println(s.getKey()+":"+s.getValue());
	}
	
	public int n1(int i){
		if(i==1)return 1;
		return i*n1(i-1);
	}

	public int n2(int i){
		int result=0;
		while(i!=1){
			result=i*(i-1);
			i--;
		}
		return result;
	}
}

package tek;

import java.util.ArrayList;

public class Amazon2 {
	
	public static void helper(int num[], ArrayList<Integer> item, boolean[] used){
		if(item.size()==num.length){
			System.out.println(item);
			return;
		}
		for(int i=0;i<num.length;i++){
			if(used[i]||(i>0&&num[i]==num[i-1]&&!used[i-1]))continue;
			used[i]=true;
			item.add(num[i]);
			helper(num,item,used);
			item.remove(item.size()-1);
			used[i]=false;
		}
	}
	
	public static void main(String args[]){
		int a[] = {1,2,3};
		helper(a,new ArrayList<Integer>(),new boolean[a.length]);
	}
}

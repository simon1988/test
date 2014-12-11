package leet.np;

import java.util.ArrayList;



/**
 * Given two integers n and k, return all possible combinations of k numbers out
 * of 1 ... n. For example, If n = 4 and k = 2, a solution is:
 * 
 * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 */

public class Combinations {
	private static void helper(int n, int k, int start, ArrayList<Integer> list){
		if(list.size()==k){
			System.out.println(list);
			return;
		}
		for(int i=start;i<=n;i++){
			list.add(i);
			helper(n,k,i+1,list);
			list.remove(list.size()-1);
		}
	}
	public static void main(String args[]){
		helper(4,2,1,new ArrayList<Integer>());
	}
}
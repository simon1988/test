package leet.np;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Given a collection of numbers that might contain duplicates, return all
 * possible unique permutations.
 * 
 * For example, [1,1,2] have the following unique permutations: 
 * [1,1,2], [1,2,1], and [2,1,1].
 */

public class Permutations {
	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
	    if(num==null || num.length==0)  
	        return res;  
	    Arrays.sort(num);  
	    helper(num, new boolean[num.length], new ArrayList<Integer>(), res);  
	    return res;  
	}  
	private void helper(int[] num, boolean[] used, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res){  
	    if(item.size() == num.length)  
	    {  
	        res.add(new ArrayList<Integer>(item));  
	        return;  
	    }  
	    for(int i=0;i<num.length;i++)  
	    {  
	        if(i>0 && !used[i-1] && num[i]==num[i-1]) continue;  
	        if(!used[i])  
	        {  
	            used[i] = true;  
	            item.add(num[i]);  
	            helper(num, used, item, res);  
	            item.remove(item.size()-1);  
	            used[i] = false;  
	        }  
	    }  
	}
}
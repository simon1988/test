package leet.moderate;


import java.util.ArrayList;

/**
 * Given n pairs of parentheses, write a function to generate all combinations
 * of well-formed parentheses.
 * 
 * For example, given n = 3, a solution set is:
 * 
 * "((()))", "(()())", "(())()", "()(())", "()()()"
 */

public class GenerateParentheses {
	public ArrayList<String> generateParenthesis(int n) {  
	    ArrayList<String> res = new ArrayList<String>();  
	    if(n<=0)  
	        return res;  
	    helper(n,n,new String(),res);  
	    return res;  
	}  
	private void helper(int l, int r, String item, ArrayList<String> res)  
	{  
	    if(r<l)  
	        return;  
	    if(l==0 && r==0)  
	    {  
	        res.add(item);  
	    }  
	    if(l>0)  
	        helper(l-1,r,item+"(",res);  
	    if(r>0)  
	        helper(l,r-1,item+")",res);  
	}
	public static void main(String args[]){
		System.out.println(new GenerateParentheses().generateParenthesis(3));
	}
}
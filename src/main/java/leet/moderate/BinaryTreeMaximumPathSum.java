package leet.moderate;

import java.util.ArrayList;

import leet.TreeNode;

/**
 * Given a binary tree, find the maximum path sum.
 * 
 * The path may start and end at any node in the tree.
 * 
 * For example: Given the below binary tree,
 * 
 *   1 
 *  / \ 
 * 2   3
 * 
 * Return 6.
 * 
 */

public class BinaryTreeMaximumPathSum {
	public int maxPathSum(TreeNode root) {  
	    if(root==null)  
	        return 0;  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    res.add(Integer.MIN_VALUE);  
	    helper(root,res);  
	    return res.get(0);  
	}  
	private int helper(TreeNode root, ArrayList<Integer> res)  
	{  
	    if(root == null)  
	        return 0;  
	    int left = helper(root.left, res);  
	    int right = helper(root.right, res);  
	    int cur = root.val + (left>0?left:0)+(right>0?right:0);  
	    if(cur>res.get(0))  
	        res.set(0,cur);  
	    return root.val+Math.max(left, Math.max(right,0));  
	} 
}
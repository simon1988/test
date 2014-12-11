package leet.moderate;

import java.util.HashMap;

import leet.TreeNode;

/**
 * Given preorder and inorder traversal of a tree, construct the binary tree.
 * 
 * Note:
 * 
 * You may assume that duplicates do not exist in the tree.
 */

public class ConstructBinaryTreefromTraversal {
	public TreeNode buildTreeInorderAndPreorder(int[] preorder, int[] inorder) {  
	    if(preorder==null || inorder==null)  
	        return null;  
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();  
	    for(int i=0;i<inorder.length;i++)  
	    {  
	        map.put(inorder[i],i);  
	    }  
	    return helper(preorder,0,preorder.length-1,inorder,0,inorder.length-1, map);  
	}  
	private TreeNode helper(int[] preorder, int preL, int preR, int[] inorder, int inL, int inR, HashMap<Integer, Integer> map)  
	{  
	    if(preL>preR || inL>inR)  
	        return null;  
	    TreeNode root = new TreeNode(preorder[preL]);  
	    int index = map.get(root.val);  
	    root.left = helper(preorder, preL+1, index-inL+preL, inorder, inL, index-1, map);  
	    root.right = helper(preorder, preL+index-inL+1, preR, inorder, index+1, inR,map);  
	    return root;  
	}
	
	public TreeNode buildTreeInorderAndPostorder(int[] inorder, int[] postorder) {  
	    if(inorder==null || postorder==null || inorder.length==0 || postorder.length==0)  
	    {  
	        return null;  
	    }  
	    HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();  
	    for(int i=0;i<inorder.length;i++)  
	    {  
	        map.put(inorder[i],i);  
	    }  
	    return helper(inorder,postorder,0,inorder.length-1, 0, postorder.length-1,map);  
	}  
	private TreeNode helper(int[] inorder, int[] postorder, int inL, int inR, int postL, int postR, HashMap<Integer, Integer> map)  
	{  
	    if(inL>inR || postL>postR)  
	        return null;  
	    TreeNode root = new TreeNode(postorder[postR]);  
	    int index = map.get(root.val);  
	    root.left = helper(inorder,postorder,inL,index-1,postL,postL+index-inL-1,map);  
	    root.right = helper(inorder,postorder,index+1,inR,postR-(inR-index),postR-1,map);  
	    return root;  
	}
}

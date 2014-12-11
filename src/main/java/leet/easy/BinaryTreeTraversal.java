package leet.easy;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import leet.TreeNode;

/**
 * Given a binary tree, return the preorder, inorder and postorder traversal of its nodes' values.
 * 
 */

public class BinaryTreeTraversal {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    if(root == null)  
	        return res;  
	    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();  
	    while(root!=null || !stack.isEmpty())  
	    {  
	        if(root!=null)  
	        {  
	            stack.push(root);  
	            res.add(root.val);  
	            root = root.left;  
	        }  
	        else  
	        {  
	            root = stack.pop();  
	            root = root.right;  
	        }  
	    }  
	    return res;  
	}
	public ArrayList<Integer> inorderTraversal(TreeNode root) {  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();  
	    while(root!=null || !stack.isEmpty())  
	    {  
	        if(root!=null)  
	        {  
	            stack.push(root);  
	            root = root.left;  
	        }  
	        else  
	        {  
	            root = stack.pop();  
	            res.add(root.val);  
	            root = root.right;  
	        }  
	    }  
	    return res;  
	}
	public List<Integer> postorderTraversal(TreeNode root) {  
	    List<Integer> res = new ArrayList<Integer>();  
	    if(root == null)  
	    {  
	        return res;  
	    }  
	    LinkedList<TreeNode> stack = new LinkedList<TreeNode>();  
	    TreeNode pre = null;  
	    while(root != null || !stack.isEmpty())  
	    {  
	        if(root!=null)  
	        {  
	            stack.push(root);  
	            root = root.left;  
	        }  
	        else  
	        {  
	            TreeNode peekNode = stack.peek();  
	            if(peekNode.right!=null && pre != peekNode.right)  
	            {  
	                root = peekNode.right;  
	            }  
	            else  
	            {  
	                stack.pop();  
	                res.add(peekNode.val);  
	                pre = peekNode;  
	            }  
	        }  
	    }  
	    return res;  
	}
}

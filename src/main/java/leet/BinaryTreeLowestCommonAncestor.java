package leet;

public class BinaryTreeLowestCommonAncestor {
	
	TreeNode getLCA(TreeNode root, TreeNode node1, TreeNode node2)  
	{  
	    if(root == null)  
	        return null;  
	    if(root== node1 || root==node2)  
	        return root;  
	  
	    TreeNode left = getLCA(root.left, node1, node2);  
	    TreeNode right = getLCA(root.right, node1, node2);  
	  
	    if(left != null && right != null)  
	        return root;  
	    else if(left != null)  
	        return left;  
	    else if (right != null)  
	        return right;  
	    else   
	        return null;  
	}  
}

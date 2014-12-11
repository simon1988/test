package leet.moderate;

import leet.TreeNode;

/**
 * Given a binary tree, find the lowest common ancestor of two given nodes in the tree.
 *
 *
 *       _______3______
 *      /              \
 *   ___5__          ___1__
 *  /      \        /      \
 *  6      _2       0       8
 *        /  \
 *        7   4
 * If you are not so sure about the definition of lowest common ancestor (LCA), please refer to my previous 
 * post: Lowest Common Ancestor of a Binary Search Tree (BST) or the definition of LCA here. Using the tree 
 * above as an example, the LCA of nodes 5 and 1 is 3. Please note that LCA for nodes 5 and 4 is 5.
 *
 * Hint:
 * Top-down or bottom-up? Consider both approaches and see which one is more efficient.
 */
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

package leet.easy;
import java.util.Stack;

import leet.TreeNode;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 *
 * For example, this binary tree is symmetric:
 *
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * But the following is not:
 *     1
 *    / \
 *   2   2
 *   \   \
 *   3    3
 * Note:
 * 
 * Bonus points if you could solve it both recursively and iteratively.
 */

public class SymmetricTree {
	public boolean isSymmetricNonRecursive(TreeNode root) {
		if (root == null)
			return true;
		Stack<TreeNode> s1 = new Stack<TreeNode>();
		Stack<TreeNode> s2 = new Stack<TreeNode>();
		s1.push(root.left);
		s2.push(root.right);
		while (!s1.isEmpty() && !s2.isEmpty()) {
			TreeNode n1 = s1.pop();
			TreeNode n2 = s2.pop();
			if (n1 == null && n2 == null) {
				continue;
			} else if (n1 == null || n2 == null) {
				return false;
			} else if (n1.val != n2.val) {
				return false;
			} else {
				s1.push(n1.left);
				s1.push(n1.right);
				s2.push(n2.right);
				s2.push(n2.left);
			}
		}
		return true;
	}
	public boolean isSymmetric(TreeNode root) {  
	    if(root == null)  
	        return true;  
	    return helper(root.left, root.right);  
	}  
	public boolean helper(TreeNode root1, TreeNode root2)  
	{  
	    if(root1 == null && root2 == null)  
	        return true;  
	    if(root1 == null || root2 == null)  
	        return false;  
	    if(root1.val != root2.val)  
	        return false;  
	    return helper(root1.left,root2.right) && helper(root1.right,root2.left);  
	}  
}
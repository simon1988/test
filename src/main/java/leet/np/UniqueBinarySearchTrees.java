package leet.np;

import java.util.ArrayList;

import leet.TreeNode;


/** 
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 *
 * For example,
 * 
 * Given n = 3, there are a total of 5 unique BST's.
 *
 *  1         3     3      2      1
 *   \       /     /      / \      \
 *    3     2     1      1   3      2
 *   /     /       \                 \
 *  2     1         2                 3
 */

public class UniqueBinarySearchTrees {
	public int numTrees(int n) {  
	    if(n<=0)  
	        return 0;  
	    int[] res = new int[n+1];  
	    res[0] = 1;  
	    res[1] = 1;  
	    for(int i=2;i<=n;i++)  
	    {  
	        for(int j=0;j<i;j++)  
	        {  
	            res[i] += res[j]*res[i-j-1];  
	        }  
	    }  
	    return res[n];  
	}
	/**
	 * Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
	 *
	 * For example,
	 * Given n = 3, your program should return all 5 unique BST's shown below.
	 *
	 *  1         3     3      2      1
	 *   \       /     /      / \      \
	 *    3     2     1      1   3      2
	 *   /     /       \                 \
	 *  2     1         2                 3
	 */
	public ArrayList<TreeNode> generateTrees(int n) {
		return buildBST(1, n);
	}

	private ArrayList<TreeNode> buildBST(int min, int max) {
		ArrayList<TreeNode> ret = new ArrayList<TreeNode>();
		if (min > max) {
			ret.add(null);
			return ret;
		}
		if (min == max) {
			ret.add(new TreeNode(min));
			return ret;
		}
		for (int i = min; i <= max; i++) {
			ArrayList<TreeNode> leftTrees = buildBST(min, i - 1);
			ArrayList<TreeNode> rightTrees = buildBST(i + 1, max);
			for (TreeNode l : leftTrees) {
				for (TreeNode r : rightTrees) {
					TreeNode root = new TreeNode(i);
					root.left = l;
					root.right = r;
					ret.add(root);
				}
			}
		}
		return ret;
	}
}
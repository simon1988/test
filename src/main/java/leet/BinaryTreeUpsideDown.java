package leet;
/**
 * 
 * Given a binary tree where all the right TreeNodes are leaf TreeNodes, flip it upside down and turn it into a tree with left leaf TreeNodes. 

Keep in mind: ALL right TreeNodeS IN ORIGINAL TREE ARE LEAF TreeNode.


/* for example, turn these:
 *
 *        1                 1
 *       / \                 / \
 *      2   3            2   3
 *     / \
 *    4   5
 *   / \
 *  6   7
 *
 * into these:
 *
 *        1               1
 *       /               /
 *      2---3         2---3
 *     /
 *    4---5
 *   /
 *  6---7
 *
 * where 6 is the new root TreeNode for the left tree, and 2 for the right tree.
 * oriented correctly:
 *
 *     6                   2
 *    / \                   / \
 *   7   4              3   1
 *        / \
 *       5   2
 *            / \
 *          3   1
 *
 *
 */
public class BinaryTreeUpsideDown {
	/*
	 * 1. Recursively traverse to the leftmost node. 
	 * 2. This becomes the NewRoot, and keep returning this value, up the chain. 
	 * 3. Make the following changes 
	 * CurrentRoot. Left.Left = CurrentRoot.Right 
	 * CurrentRoot.Left.Right = CurrentRoot 
	 * CurrentRoot.Left = CurrentRoot.Right = NULL
	 */
	TreeNode FlipTree ( TreeNode root )
	{
	    if (root == null)
	        return null;
	    
	    // Working base condition
	    if( root.left == null && root.right == null) 
	    {
	        return root;
	    }
	    
	    TreeNode newRoot = FlipTree(root.left);
	    
	    root.left.left = root.right;
	    root.left.right = root;
	    root.left = null;
	    root.right = null;
	    
	    return newRoot;
	}
}

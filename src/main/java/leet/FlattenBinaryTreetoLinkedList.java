package leet;


/**
 * Given a binary tree, flatten it to a linked list in-place.
 * 
 * For example,
 * Given
 *
 *        1
 *       / \
 *      2   5
 *     / \   \
 *    3   4   6
 * The flattened tree should look like:
 *  1
 *   \
 *    2
 *     \
 *      3
 *       \
 *        4
 *         \
 *          5
 *           \
 *            6
 */

public class FlattenBinaryTreetoLinkedList {
	private TreeNode pre;
	public void flatten(TreeNode root) {
	    helper(root);
	}
	private void helper(TreeNode root)
	{
	    if(root == null)
	        return;
	    if(pre!=null)
	    {
	        pre.left = null;
	        pre.right = root;
	    }
	    pre = root;
	    helper(root.left);
	    helper(root.right);
	}
}
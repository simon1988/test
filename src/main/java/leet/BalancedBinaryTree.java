package leet;

/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 */

public class BalancedBinaryTree {
	private int getBalancedTreeHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int l = getBalancedTreeHeight(root.left);
        int r = getBalancedTreeHeight(root.right);
        if (l >= 0 && r >= 0) {
            if (Math.abs(l - r) <= 1) {
                return Math.max(l, r) + 1;
            }
        }
        return -1;
    }

    public boolean isBalanced(TreeNode root) {
        return getBalancedTreeHeight(root) >= 0;
    }
}
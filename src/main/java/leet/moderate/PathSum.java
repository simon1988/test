package leet.moderate;


import java.util.ArrayList;

import leet.TreeNode;

/** 
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
 *
 * For example:
 * Given the below binary tree and sum = 22,
 *             5
 *            / \
 *           4   8
 *          /   / \
 *         11  13  4
 *        /  \    / \
 *       7    2  5   1
 * return
 * [
 *  [5,4,11,2],
 *  [5,8,4,5]
 * ]
 */

public class PathSum {
    public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
        if (root != null) {
            pathSum(root, sum, new ArrayList<Integer>(), ans);
        }
        return ans;
    }

    private void pathSum(TreeNode root, int sum, ArrayList<Integer> nodes,
            ArrayList<ArrayList<Integer>> ans) {
        if (root.left == null && root.right == null) {
            if (root.val == sum) {
                ArrayList<Integer> temp = new ArrayList<Integer>(nodes);
                temp.add(root.val);
                ans.add(temp);
            }
            return;
        }
        nodes.add(root.val);
        if (root.left != null) {
            pathSum(root.left, sum - root.val, nodes, ans);
        }
        if (root.right != null) {
            pathSum(root.right, sum - root.val, nodes, ans);
        }
        nodes.remove(nodes.size() - 1);
    }
    public boolean hasPathSum(TreeNode root, int sum) {  
        if(root == null)  
            return false;  
        if(root.left == null && root.right==null && root.val==sum)  
            return true;  
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);  
    }
    public static void main(String args[]){
    	PathSum p = new PathSum();
    	TreeNode root = new TreeNode(1);
    	root.left=new TreeNode(1);
    	root.right=new TreeNode(1);
//    	root.right.right=new TreeNode(1);
    	p.pathSum(root, 1);
    }
}
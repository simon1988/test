package leet.easy;

import java.util.ArrayList;

import leet.TreeNode;

/**
 * Given a binary tree, determine if it is height-balanced.
 * 
 * For this problem, a height-balanced binary tree is defined as a binary tree
 * in which the depth of the two subtrees of every node never differ by more
 * than 1.
 * 
 */

public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
        if(root==null)return true;
        return helper(root)>=0;
    }
    private int helper(TreeNode root){
        if(root==null)return 0;
        int hl=helper(root.left);
        int hr=helper(root.right);
        if(hl<0||hr<0||Math.abs(hl-hr)>1)return -1;
        return 1+Math.max(helper(root.left),helper(root.right));
    }
    //Given a binary tree, determine if it is a valid binary search tree (BST).
    public boolean isValidBST(TreeNode root) {  
        ArrayList<Integer> pre = new ArrayList<Integer>();  
        pre.add(null);  
        return helper(root, pre);  
    }  
    private boolean helper(TreeNode root, ArrayList<Integer> pre)  
    {  
        if(root == null)  
            return true;  
        boolean left = helper(root.left,pre);  
        if(pre.get(0)!=null && root.val<=pre.get(0))  
            return false;  
        pre.set(0,root.val);  
        return left && helper(root.right,pre);  
    }
    /**
     * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
     */
    public TreeNode sortedArrayToBST(int[] num) {  
        if(num==null || num.length==0)  
            return null;  
        return helper(num,0,num.length-1);  
    }  
    private TreeNode helper(int[] num, int l, int r)  
    {  
        if(l>r)  
            return null;  
        int m = (l+r)/2;  
        TreeNode root = new TreeNode(num[m]);  
        root.left = helper(num,l,m-1);  
        root.right = helper(num,m+1,r);  
        return root;  
    }
    /**
     * Two elements of a binary search tree (BST) are swapped by mistake.
     *
     * Recover the tree without changing its structure.
     *
     * Note:
     * 
     * A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?
     */
    public void recoverTree(TreeNode root) {  
        if(root == null)  
            return;  
        ArrayList<TreeNode> pre = new ArrayList<TreeNode>();  
        pre.add(null);  
        ArrayList<TreeNode> res = new ArrayList<TreeNode>();  
        helper(root,pre, res);  
        if(res.size()>0)  
        {  
            int temp = res.get(0).val;  
            res.get(0).val = res.get(1).val;  
            res.get(1).val = temp;  
        }  
    }  
    private void helper(TreeNode root, ArrayList<TreeNode> pre, ArrayList<TreeNode> res)  
    {  
        if(root == null)  
        {  
            return;  
        }  
        helper(root.left, pre, res);  
        if(pre.get(0)!=null && pre.get(0).val>root.val)  
        {  
            if(res.size()==0)  
            {  
                res.add(pre.get(0));  
                res.add(root);  
            }  
            else  
            {  
                res.set(1,root);  
            }  
        }  
        pre.set(0,root);  
        helper(root.right,pre,res);  
    } 
}
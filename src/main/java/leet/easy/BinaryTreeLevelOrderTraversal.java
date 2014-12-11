package leet.easy;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import leet.TreeNode;

/** 
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).
 *
 * For example:
 * Given binary tree {3,9,20,#,#,15,7},
 *    3
 *   / \
 *  9  20
 *  /   \
 * 15    7
 * return its bottom-up level order traversal as:
 * [
 *  [15,7]
 *  [9,20],
 *  [3],
 * ]
 * confused what "{1,#,2,3}" means? > read more on how binary tree is serialized on OJ.
 * 
 */

public class BinaryTreeLevelOrderTraversal {
	public class Solution {
        private void search(TreeNode root, int level, ArrayList<List<Integer>> ans) {
            if (root == null) {
                return;
            }
            if (level >= ans.size()) {
                ans.add(new ArrayList<Integer>());
            }
            ans.get(level).add(root.val);
            search(root.left, level + 1, ans);
            search(root.right, level + 1, ans);
        }
        public List<List<Integer>> levelOrder(TreeNode root) {
            ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
            search(root, 0, ans);
            return ans;
        }
        public List<List<Integer>> levelOrderNonRecursive(TreeNode root) {
        	ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
            if(root==null)return res;
            LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
            queue.offer(root);
            int curnum=1;
            while(!queue.isEmpty()){
                int lastnum=curnum;
                curnum=0;
                ArrayList<Integer> cur = new ArrayList<Integer>();
                for(int i=0;i<lastnum;i++){
                    TreeNode node = queue.poll();
                    if(node.left!=null){
                        queue.offer(node.left);
                        curnum++;
                    }
                    if(node.right!=null){
                        queue.offer(node.right);
                        curnum++;
                    }
                    cur.add(node.val);
                }
                res.add(cur);
            }
            return res;
        }
    }
}
package leet;


import java.util.ArrayList;
import java.util.Collections;

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

public class BinaryTreeLevelOrderTraversalII {
	public class Solution {
        private void search(TreeNode root, int level,
                ArrayList<ArrayList<Integer>> ans) {
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

        public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root) {
            ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
            search(root, 0, ans);
            Collections.reverse(ans);
            return ans;
        }
    }
}
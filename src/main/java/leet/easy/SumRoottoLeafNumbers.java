package leet.easy;

import java.util.HashMap;
import java.util.LinkedList;

import leet.TreeNode;


/** 
 * Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
 *
 * An example is the root-to-leaf path 1->2->3 which represents the number 123.
 *
 * Find the total sum of all root-to-leaf numbers.
 *
 * For example,
 *
 *   1
 *  / \
 * 2   3
 * The root-to-leaf path 1->2 represents the number 12.
 * The root-to-leaf path 1->3 represents the number 13.
 *
 * Return the sum = 12 + 13 = 25.
 */

public class SumRoottoLeafNumbers {
    public int sumNumbers(TreeNode root) {
        return sumNumbers(root,0);
    }
    private int sumNumbers(TreeNode root,int sum){
        if(root==null)return 0;
        sum = 10*sum+root.val;
        if(root.left==null&&root.right==null){
            return sum;
        }
        return sumNumbers(root.left,sum)+sumNumbers(root.right,sum);
    }

    public int sumNumbersNonRecursive(TreeNode root) {
    	int result = 0;
        int cur = 0;
        LinkedList<TreeNode> stack=new LinkedList<TreeNode>();
        HashMap<TreeNode,Integer> map = new HashMap<TreeNode,Integer>();
        while(root!=null||!stack.isEmpty()){
            if(root!=null){
                cur=10*cur+root.val;
                stack.push(root);
                map.put(root,cur);
                if(root.left==null&&root.right==null){
                    result+=cur;
                }
                root=root.left;
            }else{
                root=stack.pop();
                cur=map.get(root);
                root=root.right;
            }
        }
        return result;
    }
}
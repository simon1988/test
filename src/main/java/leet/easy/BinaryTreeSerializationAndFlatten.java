package leet.easy;

import java.util.ArrayList;

import leet.TreeNode;


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

public class BinaryTreeSerializationAndFlatten {
   public void flatten(TreeNode root) {  
	    TreeNode pre = null;  
	    helper(root, pre);  
	}  
	private void helper(TreeNode root, TreeNode pre)  
	{  
	    if(root == null)  
	        return;  
	    TreeNode right = root.right;  
	    if(pre!=null)  
	    {  
	        pre.left = null;  
	        pre.right = root;  
	    }  
	    pre=root;  
	    helper(root.left, pre);  
	    helper(right, pre);  
	}
	/**
	 * 
	 * OJ's Binary Tree Serialization:
	The serialization of a binary tree follows a level order traversal, where '#' signifies a path terminator where no node exists below.

	Here's an example:
	   1
	  / \
	 2   3
	    /
	   4
	    \
	     5
	The above binary tree is serialized as "{1,2,3,#,#,4,#,#,5}".
	 *
	 */
	private void serialize(TreeNode root, StringBuilder sb){
		if(root==null){
			sb.append('#');
			return;
		}
		sb.append(root.val);
		serialize(root.left,sb);
		serialize(root.right,sb);
	}
	public String serialize(TreeNode root){
		StringBuilder sb = new StringBuilder();
		serialize(root, sb);
		return sb.toString();
	}
	private TreeNode deserialize(String s, ArrayList<Integer> index){
		if(s.charAt(index.get(0))=='#')return null;
		TreeNode root = new TreeNode(s.charAt(index.get(0))-'0');
		index.set(0, index.get(0)+1);
		root.left=deserialize(s,index);
		index.set(0, index.get(0)+1);
		root.right=deserialize(s,index);
		return root;
	}
	public TreeNode deserialize(String s){
		if(s==null||s.length()==0)return null;
		ArrayList<Integer> index = new ArrayList<Integer>(1);
		index.add(0);
		return deserialize(s,index);
	}
	
	public static void main(String args[]){
		TreeNode root=new TreeNode(1);
		root.left=new TreeNode(2);
		root.right=new TreeNode(3);
		System.out.println(new BinaryTreeSerializationAndFlatten().serialize(new BinaryTreeSerializationAndFlatten().deserialize("12##3##")));
	}
}
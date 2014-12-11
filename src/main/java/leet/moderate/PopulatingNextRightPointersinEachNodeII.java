package leet.moderate;

/** 
 * Populating Next Right Pointers in Each Node IIOct 28 '12
 * Follow up for problem "Populating Next Right Pointers in Each Node".
 *
 * What if the given tree could be any binary tree? Would your previous solution still work?
 *
 * Note:
 *
 * You may only use constant extra space.
 * 1. For example,
 * Given the following perfect binary tree,
 *        1
 *       /  \
 *      2    3
 *     / \  / \
 *    4  5  6  7
 * After calling your function, the tree should look like:
 *        1 -> NULL
 *       /  \
 *      2 -> 3 -> NULL
 *     / \  / \
 *    4->5->6->7 -> NULL
 * 2. For example,
 * Given the following binary tree,
 *        1
 *       /  \
 *      2    3
 *     / \    \
 *    4   5    7
 * After calling your function, the tree should look like:
 *        1 -> NULL
 *       /  \
 *      2 -> 3 -> NULL
 *     / \    \
 *    4-> 5 -> 7 -> NULL
 */
	
public class PopulatingNextRightPointersinEachNodeII {
	public void connect(TreeLinkNode root) {
        TreeLinkNode leftMost = root;
        while (leftMost != null) {
            TreeLinkNode p = leftMost;
            while (p.left != null) {
                p.left.next = p.right;
                if (p.next != null) {
                    p.right.next = p.next.left;
                    p = p.next;
                } else {
                    break;
                }
            }
            leftMost = leftMost.left;
        }
    }
	public void connect2(TreeLinkNode root) {
        TreeLinkNode leftMost = root;
        while (leftMost != null) {
            TreeLinkNode p = leftMost;
            TreeLinkNode pre = null;
            leftMost = null;
            while (p != null) {
                if (p.left != null) {
                    if (leftMost == null) {
                        leftMost = p.left;
                    }
                    if (pre != null) {
                        pre.next = p.left;
                    }
                    pre = p.left;
                }
                if (p.right != null) {
                    if (leftMost == null) {
                        leftMost = p.right;
                    }
                    if (pre != null) {
                        pre.next = p.right;
                    }
                    pre = p.right;
                }
                p = p.next;
            }
        }
    }
	class TreeLinkNode {
		int val;
		TreeLinkNode left, right, next;

		TreeLinkNode(int x) {
			val = x;
		}
	}
}
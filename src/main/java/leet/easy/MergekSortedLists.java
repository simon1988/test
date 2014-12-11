package leet.easy;


import java.util.ArrayList;

/** 
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity. 
 *
 */

public class MergekSortedLists {
	public ListNode mergeKLists(ArrayList<ListNode> lists) {  
	    if(lists==null || lists.size()==0)  
	        return null;  
	    return helper(lists,0,lists.size()-1);  
	}  
	private ListNode helper(ArrayList<ListNode> lists, int l, int r)  
	{  
	    if(l<r)  
	    {  
	        int m = (l+r)/2;  
	        return mergeTwoLists(helper(lists,l,m),helper(lists,m+1,r));  
	    }  
	    return lists.get(l);  
	}  
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {  
	    ListNode helper = new ListNode(0);  
	    ListNode pre = helper;  
	    helper.next = l1;  
	    while(l1!=null && l2 != null)  
	    {  
	        if(l1.val>l2.val)  
	        {  
	            ListNode next = l2.next;  
	            l2.next = pre.next;  
	            pre.next = l2;  
	            l2 = next;  
	        }  
	        else  
	        {  
	            l1 = l1.next;  
	        }  
	        pre = pre.next;  
	  
	    }  
	    if(l2!=null)  
	    {  
	        pre.next = l2;  
	    }  
	    return helper.next;  
	}
	class ListNode {
		public int val;
		public ListNode next;

		public ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
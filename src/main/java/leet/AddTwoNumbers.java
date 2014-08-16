package leet;


/**
 * You are given two linked lists representing two non-negative numbers. 
 * 
 * The digits are stored in reverse order and each of their nodes contain a single digit.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 
 * Output: 7 -> 0 -> 8
 */

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode prev = null;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int i1 = l1 == null ? 0 : l1.val;
            int i2 = l2 == null ? 0 : l2.val;
            int sum = i1 + i2 + carry;
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            if (prev == null) {
                head = prev = node;
            } else {
                prev.next = node;
                prev = node;
            }
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry > 0) {
            prev.next = new ListNode(carry);
        }
        return head;
    }
}
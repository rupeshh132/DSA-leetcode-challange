/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if (head == null || left == right) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // Advance prev to the node just before 'left'
        for (int i = 0; i < left - 1; i++) {
            prev = prev.next;
        }

        // Pointer to the first node of the sublist to reverse
        ListNode curr = prev.next;

        // Perform in-place node swapping
        for (int i = 0; i < right - left; i++) {
            ListNode then = curr.next;
            curr.next = then.next;
            then.next = prev.next;
            prev.next = then;
        }

        return dummy.next;
    }
}
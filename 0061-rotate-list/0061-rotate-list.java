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
    public ListNode rotateRight(ListNode head, int k) {
        // Base case: empty list, single node, or no rotation needed
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        
        // Step 1: Compute the length of the list and find the tail node
        ListNode oldTail = head;
        int length = 1;
        while (oldTail.next != null) {
            oldTail = oldTail.next;
            length++;
        }
        
        // Step 2: Optimize k to handle cases where k >= length
        k = k % length;
        if (k == 0) {
            return head; // No rotation needed
        }
        
        // Step 3: Connect the tail to the head to form a circular ring
        oldTail.next = head;
        
        // Step 4: Find the new tail node, which is at position (length - k)
        ListNode newTail = head;
        for (int i = 1; i < length - k; i++) {
            newTail = newTail.next;
        }
        
        // Step 5: The node after the new tail becomes the new head, then break the ring
        ListNode newHead = newTail.next;
        newTail.next = null;
        
        return newHead;
    }
}
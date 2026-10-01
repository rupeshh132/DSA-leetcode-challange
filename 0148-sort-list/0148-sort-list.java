class Solution {
    public ListNode sortList(ListNode head) {
        // Base case: 0 or 1 element is already sorted
        if (head == null || head.next == null) {
            return head;
        }

        // 1. Split the list into two halves using slow & fast pointers
        ListNode mid = getMidAndSplit(head);

        // 2. Recursively sort each half
        ListNode left = sortList(head);
        ListNode right = sortList(mid);

        // 3. Merge the two sorted halves
        return merge(left, right);
    }

    private ListNode getMidAndSplit(ListNode head) {
        ListNode prev = null;
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        // Cut the link to divide into two separate lists
        if (prev != null) {
            prev.next = null;
        }

        return slow;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Attach any remaining nodes
        if (l1 != null) {
            current.next = l1;
        } else if (l2 != null) {
            current.next = l2;
        }

        return dummy.next;
    }
}
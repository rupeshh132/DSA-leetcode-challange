class Solution {
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        int firstCriticalIndex = -1;
        int prevCriticalIndex = -1;
        int minDistance = Integer.MAX_VALUE;

        ListNode prev = head;
        ListNode curr = head.next;
        int currentIndex = 1;

        while (curr.next != null) {
            ListNode next = curr.next;

            // Check if current node is a local maxima or local minima
            boolean isCritical = (curr.val > prev.val && curr.val > next.val) ||
                                 (curr.val < prev.val && curr.val < next.val);

            if (isCritical) {
                if (firstCriticalIndex == -1) {
                    firstCriticalIndex = currentIndex;
                } else {
                    minDistance = Math.min(minDistance, currentIndex - prevCriticalIndex);
                }
                prevCriticalIndex = currentIndex;
            }

            prev = curr;
            curr = next;
            currentIndex++;
        }

        // If fewer than two critical points were found
        if (firstCriticalIndex == -1 || firstCriticalIndex == prevCriticalIndex) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCriticalIndex - firstCriticalIndex;
        return new int[]{minDistance, maxDistance};
    }
}
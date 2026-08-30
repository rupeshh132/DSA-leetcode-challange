class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0;
        int maxIdx = 0;

        // Find indices of minimum and maximum elements
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
        }

        // Ensure i is the smaller index and j is the larger index
        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        // 1. Remove both from the front
        int removeBothFront = j + 1;

        // 2. Remove both from the back
        int removeBothBack = n - i;

        // 3. Remove one from the front and one from the back
        int removeBothEnds = (i + 1) + (n - j);

        return Math.min(removeBothFront, Math.min(removeBothBack, removeBothEnds));
    }
}
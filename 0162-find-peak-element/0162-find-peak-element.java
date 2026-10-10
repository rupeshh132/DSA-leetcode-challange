class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1]) {
                // Peak lies to the right
                left = mid + 1;
            } else {
                // Peak lies at mid or to the left
                right = mid;
            }
        }

        // When left == right, we have converged on a peak
        return left;
    }
}
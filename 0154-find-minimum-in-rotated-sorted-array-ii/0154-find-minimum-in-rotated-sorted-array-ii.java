class Solution {
    public int findMin(int[] nums) {
        int left = 0;
        int right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[right]) {
                // The minimum element is in the left half, including mid
                right = mid;
            } else if (nums[mid] > nums[right]) {
                // The inflection point is in the right half, strictly after mid
                left = mid + 1;
            } else {
                // nums[mid] == nums[right]: Cannot determine which half contains the min,
                // but nums[right] can be safely eliminated because nums[mid] preserves the value.
                right--;
            }
        }

        return nums[left];
    }
}
class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            totalXor ^= num;
            if (num > 0) {
                hasNonZero = true;
            }
        }

        // If total XOR is already non-zero, take the whole array
        if (totalXor != 0) {
            return nums.length;
        }

        // If total XOR is 0 and there is at least one positive number,
        // removing one non-zero element gives a non-zero XOR of length n - 1
        if (hasNonZero) {
            return nums.length - 1;
        }

        // All elements are 0, so no valid non-zero XOR subsequence exists
        return 0;
    }
}
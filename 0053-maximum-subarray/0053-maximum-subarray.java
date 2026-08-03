class Solution {
    public int maxSubArray(int[] nums) {
        int maxSoFar = nums[0];
        int currentMax = nums[0];
        
        for (int i = 1; i < nums.length; i++) {
            // Decide whether to add the current number to the existing subarray 
            // or start a new subarray from the current number
            currentMax = Math.max(nums[i], currentMax + nums[i]);
            // Track the global maximum seen so far
            maxSoFar = Math.max(maxSoFar, currentMax);
        }
        
        return maxSoFar;
    }
}
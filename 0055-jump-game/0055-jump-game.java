class Solution {
    public boolean canJump(int[] nums) {
        int maxReachable = 0;
        
        for (int i = 0; i < nums.length; i++) {
            // If the current index is greater than the maximum index we can reach,
            // it means we are stuck and cannot proceed further.
            if (i > maxReachable) {
                return false;
            }
            
            // Update the furthest index we can reach from the current position
            maxReachable = Math.max(maxReachable, i + nums[i]);
            
            // Optimization: If we can already reach or surpass the last index, return true immediately
            if (maxReachable >= nums.length - 1) {
                return true;
            }
        }
        
        return true;
    }
}
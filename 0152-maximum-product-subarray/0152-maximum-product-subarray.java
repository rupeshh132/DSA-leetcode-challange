class Solution {
    public int maxProduct(int[] nums) {
        int globalMax = nums[0];
        int currMax = nums[0];
        int currMin = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];

            // A negative number flips the min and max
            if (num < 0) {
                int temp = currMax;
                currMax = currMin;
                currMin = temp;
            }

            // Decide whether to extend the previous subarray or start fresh from num
            currMax = Math.max(num, currMax * num);
            currMin = Math.min(num, currMin * num);

            // Update the overall maximum found so far
            globalMax = Math.max(globalMax, currMax);
        }

        return globalMax;
    }
}
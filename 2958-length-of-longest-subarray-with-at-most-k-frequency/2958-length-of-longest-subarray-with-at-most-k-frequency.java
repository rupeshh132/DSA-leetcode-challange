import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> freq = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < nums.length; right++) {
            int currentNum = nums[right];
            freq.put(currentNum, freq.getOrDefault(currentNum, 0) + 1);

            // Shrink the window until the frequency of currentNum is <= k
            while (freq.get(currentNum) > k) {
                int leftNum = nums[left];
                freq.put(leftNum, freq.get(leftNum) - 1);
                left++;
            }

            // Update the maximum length of a valid subarray
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
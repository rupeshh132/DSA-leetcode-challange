class Solution {
    public int singleNumber(int[] nums) {
        int ones = 0;
        int twos = 0;

        for (int num : nums) {
            // Add num to 'ones' only if it's not already in 'twos'
            ones = (ones ^ num) & ~twos;
            // Add num to 'twos' only if it's not already in 'ones'
            twos = (twos ^ num) & ~ones;
        }

        return ones;
    }
}
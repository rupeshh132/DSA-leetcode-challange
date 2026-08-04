class Solution {
    public int removeDuplicates(int[] nums) {
        int k = 0;
        
        for (int num : nums) {
            // Allow element if we haven't picked 2 elements yet,
            // or if current num is different from element at index k - 2
            if (k < 2 || num != nums[k - 2]) {
                nums[k] = num;
                k++;
            }
        }
        
        return k;
    }
}
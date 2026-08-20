class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        
        // Use fixed-size arrays or ArrayLists to simulate the process
        int[] arr1 = new int[n];
        int[] arr2 = new int[n];
        
        int p1 = 0;
        int p2 = 0;
        
        // Initial two operations
        arr1[p1++] = nums[0];
        arr2[p2++] = nums[1];
        
        // Distribute remaining elements
        for (int i = 2; i < n; i++) {
            if (arr1[p1 - 1] > arr2[p2 - 1]) {
                arr1[p1++] = nums[i];
            } else {
                arr2[p2++] = nums[i];
            }
        }
        
        // Concatenate arr1 and arr2 into result
        int[] result = new int[n];
        int idx = 0;
        
        for (int i = 0; i < p1; i++) {
            result[idx++] = arr1[i];
        }
        for (int i = 0; i < p2; i++) {
            result[idx++] = arr2[i];
        }
        
        return result;
    }
}
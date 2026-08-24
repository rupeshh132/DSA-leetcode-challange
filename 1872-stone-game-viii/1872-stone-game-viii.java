class Solution {
    public int stoneGameVIII(int[] stones) {
        int n = stones.length;
        
        // Compute prefix sums in-place
        for (int i = 1; i < n; i++) {
            stones[i] += stones[i - 1];
        }
        
        // Base case: if forced or choosing the last index n - 1
        int maxDiff = stones[n - 1];
        
        // Iterate backwards from index n - 2 down to index 1
        for (int i = n - 2; i >= 1; i--) {
            maxDiff = Math.max(maxDiff, stones[i] - maxDiff);
        }
        
        return maxDiff;
    }
}
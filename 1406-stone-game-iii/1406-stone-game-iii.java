class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        
        // dp[i % 4] will store the maximum relative score from index i.
        // We use size 4 because we only ever look back up to 3 steps ahead.
        int[] dp = new int[4];
        
        // Start from the end of the stone row and move backwards
        for (int i = n - 1; i >= 0; i--) {
            int takeOne = stoneValue[i] - dp[(i + 1) % 4];
            
            int takeTwo = Integer.MIN_VALUE;
            if (i + 1 < n) {
                takeTwo = stoneValue[i] + stoneValue[i + 1] - dp[(i + 2) % 4];
            }
            
            int takeThree = Integer.MIN_VALUE;
            if (i + 2 < n) {
                takeThree = stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - dp[(i + 3) % 4];
            }
            
            // The current player chooses the optimal path maximizing their relative score
            dp[i % 4] = Math.max(takeOne, Math.max(takeTwo, takeThree));
        }
        
        // dp[0] holds the total maximum relative score Alice can get starting first
        int finalRelativeScore = dp[0];
        
        if (finalRelativeScore > 0) {
            return "Alice";
        } else if (finalRelativeScore < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}
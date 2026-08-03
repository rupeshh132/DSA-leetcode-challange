class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
            return 0;
        }
        
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        
        // If the starting cell or destination cell contains an obstacle, no paths exist
        if (obstacleGrid[0][0] == 1 || obstacleGrid[m - 1][n - 1] == 1) {
            return 0;
        }
        
        // 1D DP array to store path counts for the current row
        int[] dp = new int[n];
        dp[0] = 1; // Base case: There is 1 way to be at the starting point
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If the current cell is an obstacle, reset paths through it to 0
                if (obstacleGrid[i][j] == 1) {
                    dp[j] = 0;
                } 
                // Otherwise, add the paths coming from the left cell (dp[j - 1]) 
                // to the paths already coming from the top cell (dp[j])
                else if (j > 0) {
                    dp[j] += dp[j - 1];
                }
            }
        }
        
        // The last element contains the number of unique paths to the bottom-right corner
        return dp[n - 1];
    }
}
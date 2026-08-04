class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        
        // Base cases: 1 way to form an empty tree or a tree with 1 node
        dp[0] = 1;
        dp[1] = 1;

        // Build DP array for tree sizes from 2 to n
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }

        return dp[n];
    }
}
import java.util.Arrays;

public class Solution {
    private int[][] memo;
    private int[] suffixSum;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        // memo[i][M] stores the max stones a player can get starting at index i with parameter M
        memo = new int[n][n + 1];
        
        // Calculate suffix sums to quickly get total remaining stones from index i
        suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        return solve(0, 1, piles);
    }

    private int solve(int i, int M, int[] piles) {
        int n = piles.length;
        
        // Base Case: If remaining piles are less than or equal to 2 * M, take all remaining piles
        if (i + 2 * M >= n) {
            return suffixSum[i];
        }

        if (memo[i][M] != 0) {
            return memo[i][M];
        }

        int maxStones = 0;
        
        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            // Current player gets total remaining minus what opponent can get in next turn
            int currentStones = suffixSum[i] - solve(i + X, nextM, piles);
            maxStones = Math.max(maxStones, currentStones);
        }

        memo[i][M] = maxStones;
        return maxStones;
    }
}
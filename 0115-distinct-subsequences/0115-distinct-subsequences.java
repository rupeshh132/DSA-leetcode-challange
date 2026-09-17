class Solution {
    public int numDistinct(String s, String t) {
        int m = s.length();
        int n = t.length();
        
        // If s is shorter than t, it cannot contain t as a subsequence
        if (m < n) {
            return 0;
        }

        // dp[j] stores the number of subsequences of s formed so far that match t[0...j-1]
        // Using double or long internally avoids 32-bit integer overflow during intermediate transitions
        int[] dp = new int[n + 1];
        
        // An empty string t can always be formed by an empty subsequence exactly 1 way
        dp[0] = 1;

        for (int i = 1; i <= m; i++) {
            char sChar = s.charAt(i - 1);
            // Iterate backwards to update dp in-place using values from the previous row
            for (int j = n; j >= 1; j--) {
                if (sChar == t.charAt(j - 1)) {
                    dp[j] = dp[j] + dp[j - 1];
                }
            }
        }

        return dp[n];
    }
}
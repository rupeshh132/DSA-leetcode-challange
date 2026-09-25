class Solution {
    public int minCut(String s) {
        int n = s.length();
        if (n <= 1) {
            return 0;
        }

        // isPal[i][j] is true if s[i...j] is a palindrome
        boolean[][] isPal = new boolean[n][n];

        // dp[i] is the minimum cuts needed for s[0...i]
        int[] dp = new int[n];

        for (int i = 0; i < n; i++) {
            // Maximum cuts needed for s[0...i] is i (cut after every single character)
            int minCuts = i;

            for (int j = 0; j <= i; j++) {
                // s[j...i] is a palindrome if outer characters match
                // and inner substring s[j+1...i-1] is also a palindrome (or length <= 2)
                if (s.charAt(j) == s.charAt(i) && (i - j <= 2 || isPal[j + 1][i - 1])) {
                    isPal[j][i] = true;

                    // If the entire prefix s[0...i] is a palindrome, no cuts are needed
                    if (j == 0) {
                        minCuts = 0;
                    } else {
                        minCuts = Math.min(minCuts, dp[j - 1] + 1);
                    }
                }
            }
            dp[i] = minCuts;
        }

        return dp[n - 1];
    }
}
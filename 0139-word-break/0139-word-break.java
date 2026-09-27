import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        int n = s.length();
        
        // dp[i] is true if s[0...i-1] can be segmented into dictionary words
        boolean[] dp = new boolean[n + 1];
        dp[0] = true; // Base case: empty prefix is valid

        // Track the maximum word length to prune unnecessary substring checks
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        for (int i = 1; i <= n; i++) {
            // Check substrings s[j...i-1]
            for (int j = i - 1; j >= Math.max(0, i - maxLen); j--) {
                if (dp[j] && wordSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break; // No need to check earlier split points for this i
                }
            }
        }

        return dp[n];
    }
}
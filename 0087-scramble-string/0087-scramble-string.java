import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<String, Boolean> memo = new HashMap<>();

    public boolean isScramble(String s1, String s2) {
        // Base Case: Exact equality
        if (s1.equals(s2)) {
            return true;
        }

        // Base Case: Length mismatch or single mismatched character
        if (s1.length() != s2.length()) {
            return false;
        }

        String key = s1 + "_" + s2;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        int n = s1.length();

        // Pruning: Fast character count check
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s1.charAt(i) - 'a']++;
            count[s2.charAt(i) - 'a']--;
        }
        for (int c : count) {
            if (c != 0) {
                memo.put(key, false);
                return false;
            }
        }

        // Try every possible split point i
        for (int i = 1; i < n; i++) {
            // Case 1: Without Swap
            boolean noSwap = isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                             isScramble(s1.substring(i), s2.substring(i));

            if (noSwap) {
                memo.put(key, true);
                return true;
            }

            // Case 2: With Swap
            boolean swap = isScramble(s1.substring(0, i), s2.substring(n - i)) &&
                           isScramble(s1.substring(i), s2.substring(0, n - i));

            if (swap) {
                memo.put(key, true);
                return true;
            }
        }

        memo.put(key, false);
        return false;
    }
}
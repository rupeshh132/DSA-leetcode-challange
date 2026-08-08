class Solution {
    public int[] validSequence(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        
        // last[j] stores the maximum index in word1 that can match 
        // the suffix word2[j...n-1] exactly (with 0 mismatches).
        int[] last = new int[n];
        int p = m - 1;
        
        for (int j = n - 1; j >= 0; j--) {
            while (p >= 0 && word1.charAt(p) != word2.charAt(j)) {
                p--;
            }
            last[j] = p;
            if (p >= 0) {
                p--;
            }
        }
        
        int[] ans = new int[n];
        int i = 0;
        boolean changed = false; // Tracks if the 1 character mismatch has been used
        
        for (int j = 0; j < n; j++) {
            boolean found = false;
            
            while (i < m) {
                if (word1.charAt(i) == word2.charAt(j)) {
                    // Exact match
                    if (changed) {
                        // If we already used our change, the remaining suffix must match exactly
                        if (j == n - 1 || last[j + 1] > i) {
                            ans[j] = i++;
                            found = true;
                            break;
                        }
                    } else {
                        // Keep 'changed' false and take the smallest valid index
                        ans[j] = i++;
                        found = true;
                        break;
                    }
                } else {
                    // Mismatch: Use our 1 allowed change if not yet used
                    // and the remaining suffix word2[j+1...n-1] can be matched exactly
                    if (!changed && (j == n - 1 || last[j + 1] > i)) {
                        ans[j] = i++;
                        changed = true;
                        found = true;
                        break;
                    }
                }
                i++;
            }
            
            if (!found) {
                return new int[0];
            }
        }
        
        return ans;
    }
}
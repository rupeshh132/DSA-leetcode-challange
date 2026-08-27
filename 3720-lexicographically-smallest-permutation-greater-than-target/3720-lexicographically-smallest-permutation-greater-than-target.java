class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] totalCounts = new int[26];
        for (char ch : s.toCharArray()) {
            totalCounts[ch - 'a']++;
        }

        // Check each index i from n - 1 down to 0
        for (int i = n - 1; i >= 0; i--) {
            int[] remaining = totalCounts.clone();
            boolean prefixValid = true;

            // Consume characters for target[0 ... i-1]
            for (int j = 0; j < i; j++) {
                int charIdx = target.charAt(j) - 'a';
                if (--remaining[charIdx] < 0) {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) {
                continue;
            }

            // Look for the smallest available character strictly greater than target[i]
            int targetChar = target.charAt(i) - 'a';
            int bestChar = -1;
            for (int c = targetChar + 1; c < 26; c++) {
                if (remaining[c] > 0) {
                    bestChar = c;
                    break;
                }
            }

            // If a valid character is found, construct the result
            if (bestChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + bestChar));
                remaining[bestChar]--;

                // Append the rest of the available characters in ascending order
                for (int c = 0; c < 26; c++) {
                    while (remaining[c] > 0) {
                        sb.append((char) ('a' + c));
                        remaining[c]--;
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}
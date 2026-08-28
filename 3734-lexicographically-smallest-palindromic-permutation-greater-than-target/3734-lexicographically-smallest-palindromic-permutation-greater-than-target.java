class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Validate if a palindrome can be formed
        int oddCount = 0;
        char midChar = 0;
        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                midChar = (char) ('a' + i);
            }
        }

        if ((n % 2 == 0 && oddCount > 0) || (n % 2 != 0 && oddCount != 1)) {
            return "";
        }

        int m = n / 2;
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = count[i] / 2;
        }

        // Case 1: First half matches target[0...m-1] exactly
        int[] tempCount = halfCount.clone();
        boolean canMatchExact = true;
        for (int i = 0; i < m; i++) {
            int charIdx = target.charAt(i) - 'a';
            if (--tempCount[charIdx] < 0) {
                canMatchExact = false;
                break;
            }
        }

        if (canMatchExact) {
            String candidate = buildPalindrome(target.substring(0, m), midChar, n % 2 != 0);
            if (candidate.compareTo(target) > 0) {
                return candidate;
            }
        }

        // Case 2: Match prefix up to i - 1, and make target[i] strictly greater
        for (int i = m - 1; i >= 0; i--) {
            // Check if prefix target[0...i-1] is valid
            int[] currentCount = halfCount.clone();
            boolean prefixValid = true;
            for (int j = 0; j < i; j++) {
                int charIdx = target.charAt(j) - 'a';
                if (--currentCount[charIdx] < 0) {
                    prefixValid = false;
                    break;
                }
            }

            if (!prefixValid) continue;

            // Try the smallest char greater than target[i]
            for (int c = target.charAt(i) - 'a' + 1; c < 26; c++) {
                if (currentCount[c] > 0) {
                    currentCount[c]--;

                    // Construct first half
                    StringBuilder firstHalf = new StringBuilder();
                    firstHalf.append(target, 0, i);
                    firstHalf.append((char) ('a' + c));

                    for (int k = 0; k < 26; k++) {
                        while (currentCount[k] > 0) {
                            firstHalf.append((char) ('a' + k));
                            currentCount[k]--;
                        }
                    }

                    return buildPalindrome(firstHalf.toString(), midChar, n % 2 != 0);
                }
            }
        }

        return "";
    }

    private String buildPalindrome(String firstHalf, char midChar, boolean hasMid) {
        StringBuilder sb = new StringBuilder(firstHalf);
        if (hasMid) {
            sb.append(midChar);
        }
        for (int i = firstHalf.length() - 1; i >= 0; i--) {
            sb.append(firstHalf.charAt(i));
        }
        return sb.toString();
    }
}
class Solution {
    public int maximumLengthSubstring(String s) {
        int[] freq = new int[26];
        int left = 0;
        int maxLength = 0;
        
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            freq[c - 'a']++;
            
            // If any character exceeds 2 occurrences, shrink the window from the left
            while (freq[c - 'a'] > 2) {
                freq[s.charAt(left) - 'a']--;
                left++;
            }
            
            // Update the maximum length found
            maxLength = Math.max(maxLength, right - left + 1);
        }
        
        return maxLength;
    }
}
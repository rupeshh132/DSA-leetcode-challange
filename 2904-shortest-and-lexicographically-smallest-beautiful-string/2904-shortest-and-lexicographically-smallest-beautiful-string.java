class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        int left = 0;
        int count1 = 0;
        String result = "";

        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count1++;
            }

            // Shrink window while maintaining k ones
            while (count1 == k) {
                // Strip leading zeros
                while (s.charAt(left) == '0') {
                    left++;
                }

                String current = s.substring(left, right + 1);

                // Update result if shorter or lexicographically smaller
                if (result.isEmpty() || current.length() < result.length() || 
                   (current.length() == result.length() && current.compareTo(result) < 0)) {
                    result = current;
                }

                // Move left pointer to search for next window
                left++;
                count1--;
            }
        }

        return result;
    }
}
import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(String s, int start, List<String> currentPath, List<List<String>> result) {
        // Base case: if we have reached the end of the string, add current partition
        if (start == s.length()) {
            result.add(new ArrayList<>(currentPath));
            return;
        }

        for (int end = start; end < s.length(); end++) {
            // Check if the current substring s[start...end] is a palindrome
            if (isPalindrome(s, start, end)) {
                // Choose
                currentPath.add(s.substring(start, end + 1));
                // Explore
                backtrack(s, end + 1, currentPath, result);
                // Backtrack
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}
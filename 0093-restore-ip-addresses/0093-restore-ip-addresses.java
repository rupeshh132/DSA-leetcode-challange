import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        
        // A valid IPv4 string length must be between 4 and 12 characters
        if (s == null || s.length() < 4 || s.length() > 12) {
            return result;
        }

        backtrack(s, 0, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String s, int index, int dots, StringBuilder current, List<String> result) {
        // Base Case: 4 segments formed
        if (dots == 4) {
            if (index == s.length()) {
                // Remove trailing dot and add to answer
                result.add(current.substring(0, current.length() - 1));
            }
            return;
        }

        int len = s.length();
        // Early pruning if remaining characters cannot fit into remaining segments
        if (len - index > (4 - dots) * 3) {
            return;
        }

        // Try segment lengths of 1, 2, and 3 digits
        for (int size = 1; size <= 3 && index + size <= len; size++) {
            String segment = s.substring(index, index + size);

            // Leading zero check
            if (size > 1 && segment.charAt(0) == '0') {
                break;
            }

            // Value range check (<= 255)
            int val = Integer.parseInt(segment);
            if (val > 255) {
                break;
            }

            int prevLen = current.length();
            current.append(segment).append(".");

            backtrack(s, index + size, dots + 1, current, result);

            // Backtrack: restore StringBuilder state
            current.setLength(prevLen);
        }
    }
}
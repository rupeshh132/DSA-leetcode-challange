class Solution {
    public boolean isNumber(String s) {
        boolean seenDigit = false;
        boolean seenDot = false;
        boolean seenE = false;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (Character.isDigit(c)) {
                seenDigit = true;
            } 
            else if (c == '+' || c == '-') {
                // A sign is only valid at the start or immediately after an 'e' or 'E'
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } 
            else if (c == '.') {
                // A dot is only valid if we haven't seen a dot or an 'e'/'E' yet
                if (seenDot || seenE) {
                    return false;
                }
                seenDot = true;
            } 
            else if (c == 'e' || c == 'E') {
                // An exponent is only valid if we haven't seen one yet AND we have seen a digit
                if (seenE || !seenDigit) {
                    return false;
                }
                seenE = true;
                seenDigit = false; // Exponent needs to be followed by another integer sequence
            } 
            else {
                // Any other character (like alphabets besides e/E, special characters) is invalid
                return false;
            }
        }
        
        // The entire string is valid only if it ends with a completed digit sequence
        return seenDigit;
    }
}
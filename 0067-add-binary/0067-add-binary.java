class Solution {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        
        // Pointers starting at the end of both strings
        int i = a.length() - 1;
        int j = b.length() - 1;
        int carry = 0;
        
        // Loop as long as there are digits to process or a carry remains
        while (i >= 0 || j >= 0 || carry > 0) {
            int sum = carry;
            
            // Add digit from string a if available
            if (i >= 0) {
                sum += a.charAt(i) - '0';
                i--;
            }
            
            // Add digit from string b if available
            if (j >= 0) {
                sum += b.charAt(j) - '0';
                j--;
            }
            
            // Append the binary digit (sum % 2) to our builder
            sb.append(sum % 2);
            
            // Calculate the new carry (sum / 2)
            carry = sum / 2;
        }
        
        // Since we processed from right to left, the result is reversed
        return sb.reverse().toString();
    }
}
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        
        // Traverse the array from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (digits[i] < 9) {
                digits[i]++;
                return digits; // No carry-over further, we are done!
            }
            
            // If the digit was 9, it becomes 0 due to the carry-over
            digits[i] = 0;
        }
        
        // If we reach here, all digits were 9 (e.g., [9, 9, 9] -> [1, 0, 0, 0])
        int[] newDigits = new int[n + 1];
        newDigits[0] = 1; // Remaining elements default to 0 in Java arrays
        
        return newDigits;
    }
}
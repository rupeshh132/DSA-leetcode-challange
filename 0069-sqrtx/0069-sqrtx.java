class Solution {
    public int mySqrt(int x) {
        // Base case: The square root of 0 is 0
        if (x == 0) {
            return 0;
        }
        
        int left = 1;
        int right = x;
        int ans = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // Using division (mid <= x / mid) instead of multiplication (mid * mid <= x)
            // to completely prevent integer overflow vulnerabilities.
            if (mid <= x / mid) {
                ans = mid;     // mid could be the potential answer, save it
                left = mid + 1; // Try to search for a larger valid integer on the right
            } else {
                right = mid - 1; // mid * mid > x, look for a smaller value on the left
            }
        }
        
        return ans;
    }
}
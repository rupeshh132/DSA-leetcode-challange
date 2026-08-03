class Solution {
    public int climbStairs(int n) {
        // Base cases: 1 way for 1 step, 2 ways for 2 steps
        if (n <= 2) {
            return n;
        }
        
        // Pointers to track ways(n-2) and ways(n-1)
        int stepMinusTwo = 1; // ways to reach 1st step
        int stepMinusOne = 2; // ways to reach 2nd step
        int currentWays = 0;
        
        // Compute transitions iteratively up to n
        for (int i = 3; i <= n; i++) {
            currentWays = stepMinusOne + stepMinusTwo;
            
            // Shift the states forward for the next iteration
            stepMinusTwo = stepMinusOne;
            stepMinusOne = currentWays;
        }
        
        return currentWays;
    }
}
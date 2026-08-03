import java.util.ArrayList;
import java.util.List;

class Solution {
    public String getPermutation(int n, int k) {
        // Step 1: Precompute factorials up to n and create a list of available digits
        int[] factorial = new int[n + 1];
        List<Integer> numbers = new ArrayList<>();
        
        factorial[0] = 1;
        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
            numbers.add(i); // numbers list: [1, 2, 3, ..., n]
        }
        
        // Convert k to 0-indexed system
        k--;
        
        StringBuilder sb = new StringBuilder();
        
        // Step 2: Determine each digit one by one
        for (int i = 1; i <= n; i++) {
            // Number of permutations possible for the remaining (n - i) positions
            int currentGroupSize = factorial[n - i];
            
            // Find the index of the number to place at the current position
            int index = k / currentGroupSize;
            
            // Append the number and remove it from the available list
            sb.append(numbers.get(index));
            numbers.remove(index);
            
            // Update k for the next iteration
            k %= currentGroupSize;
        }
        
        return sb.toString();
    }
}
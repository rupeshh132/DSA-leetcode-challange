class Solution {
    private int count = 0;

    public int totalNQueens(int n) {
        count = 0; // Reset for multiple test cases if called repeatedly
        
        // Track columns and diagonals under attack
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // For row - col
        boolean[] diag2 = new boolean[2 * n]; // For row + col
        
        backtrack(0, n, cols, diag1, diag2);
        return count;
    }
    
    private void backtrack(int row, int n, boolean[] cols, boolean[] diag1, boolean[] diag2) {
        // Base case: All queens successfully placed
        if (row == n) {
            count++;
            return;
        }
        
        for (int col = 0; col < n; col++) {
            int d1 = row - col + n;
            int d2 = row + col;
            
            // Skip if the position is under attack
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }
            
            // Place queen (mark as under attack)
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            
            // Move to the next row
            backtrack(row + 1, n, cols, diag1, diag2);
            
            // Backtrack (unmark the position)
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
}
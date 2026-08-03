import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        // Create an empty chessboard represented by character array
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '.';
            }
        }
        
        // Lookup arrays to check safety in O(1) time
        boolean[] cols = new boolean[n];
        boolean[] diag1 = new boolean[2 * n]; // For row - col
        boolean[] diag2 = new boolean[2 * n]; // For row + col
        
        backtrack(0, n, board, result, cols, diag1, diag2);
        return result;
    }
    
    private void backtrack(int row, int n, char[][] board, List<List<String>> result, 
                           boolean[] cols, boolean[] diag1, boolean[] diag2) {
        // Base case: If all rows are filled, we found a valid configuration
        if (row == n) {
            result.add(constructBoard(board));
            return;
        }
        
        for (int col = 0; col < n; col++) {
            // Index formulas to shift negative values into positive array bounds
            int d1 = row - col + n;
            int d2 = row + col;
            
            // If the current column or diagonals are already under attack, skip
            if (cols[col] || diag1[d1] || diag2[d2]) {
                continue;
            }
            
            // Place the queen
            board[row][col] = 'Q';
            cols[col] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            
            // Move to the next row
            backtrack(row + 1, n, board, result, cols, diag1, diag2);
            
            // Backtrack: Remove the queen and reset the lookup flags
            board[row][col] = '.';
            cols[col] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
    
    // Helper method to convert the 2D char array board into the required List<String> format
    private List<String> constructBoard(char[][] board) {
        List<String> path = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            path.add(new String(board[i]));
        }
        return path;
    }
}
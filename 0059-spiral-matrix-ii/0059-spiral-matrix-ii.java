class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        
        // Define initial boundaries
        int top = 0;
        int bottom = n - 1;
        int left = 0;
        int right = n - 1;
        
        int num = 1; // Counter to fill the matrix
        
        while (top <= bottom && left <= right) {
            // 1. Traverse from Left to Right along the Top boundary
            for (int j = left; j <= right; j++) {
                matrix[top][j] = num++;
            }
            top++; // Move the top boundary down
            
            // 2. Traverse from Top to Bottom along the Right boundary
            for (int i = top; i <= bottom; i++) {
                matrix[i][right] = num++;
            }
            right--; // Move the right boundary left
            
            // 3. Traverse from Right to Left along the Bottom boundary
            if (top <= bottom) {
                for (int j = right; j >= left; j--) {
                    matrix[bottom][j] = num++;
                }
                bottom--; // Move the bottom boundary up
            }
            
            // 4. Traverse from Bottom to Top along the Left boundary
            if (left <= right) {
                for (int i = bottom; i >= top; i--) {
                    matrix[i][left] = num++;
                }
                left++; // Move the left boundary right
            }
        }
        
        return matrix;
    }
}
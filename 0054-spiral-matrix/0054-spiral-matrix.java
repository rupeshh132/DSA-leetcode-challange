import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        
        if (matrix == null || matrix.length == 0) {
            return result;
        }
        
        // Define initial boundaries
        int top = 0;
        int bottom = matrix.length - 1;
        int left = 0;
        int right = matrix[0].length - 1;
        
        while (top <= bottom && left <= right) {
            // 1. Traverse from Left to Right along the Top boundary
            for (int j = left; j <= right; j++) {
                result.add(matrix[top][j]);
            }
            top++; // Move the top boundary down
            
            // 2. Traverse from Top to Bottom along the Right boundary
            for (int i = top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            right--; // Move the right boundary left
            
            // Check if boundaries have crossed before moving backwards
            if (top <= bottom) {
                // 3. Traverse from Right to Left along the Bottom boundary
                for (int j = right; j >= left; j--) {
                    result.add(matrix[bottom][j]);
                }
                bottom--; // Move the bottom boundary up
            }
            
            if (left <= right) {
                // 4. Traverse from Bottom to Top along the Left boundary
                for (int i = bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++; // Move the left boundary right
            }
        }
        
        return result;
    }
}
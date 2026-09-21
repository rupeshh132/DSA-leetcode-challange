import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<>();
        
        // Start with the first element
        row.add(1);
        
        for (int i = 1; i <= rowIndex; i++) {
            // Update the list from right to left so earlier values aren't overwritten
            for (int j = i - 1; j > 0; j--) {
                row.set(j, row.get(j) + row.get(j - 1));
            }
            // Each row ends with 1
            row.add(1);
        }
        
        return row;
    }
}
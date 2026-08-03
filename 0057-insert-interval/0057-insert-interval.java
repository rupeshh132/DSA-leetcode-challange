import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> result = new ArrayList<>();
        int i = 0;
        int n = intervals.length;
        
        // Phase 1: Add all intervals that come strictly before newInterval
        while (i < n && intervals[i][1] < newInterval[0]) {
            result.add(intervals[i]);
            i++;
        }
        
        // Phase 2: Merge all overlapping intervals with newInterval
        while (i < n && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(newInterval[0], intervals[i][0]);
            newInterval[1] = Math.max(newInterval[1], intervals[i][1]);
            i++;
        }
        // Add the fully merged newInterval
        result.add(newInterval);
        
        // Phase 3: Add all remaining intervals that come strictly after newInterval
        while (i < n) {
            result.add(intervals[i]);
            i++;
        }
        
        // Convert the list back to a 2D array
        return result.toArray(new int[result.size()][]);
    }
}
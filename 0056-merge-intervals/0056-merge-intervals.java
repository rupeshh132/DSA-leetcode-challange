import java.util.Arrays;
import java.util.LinkedList;

class Solution {
    public int[][] merge(int[][] intervals) {
        // Base case: if there's only 1 or 0 intervals, no merging is needed
        if (intervals.length <= 1) {
            return intervals;
        }

        // 1. Sort the intervals by their starting values
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        // Use a linked list to easily access and update the last added interval
        LinkedList<int[]> merged = new LinkedList<>();

        for (int[] interval : intervals) {
            // If the list is empty or the current interval does not overlap with the previous one,
            // simply append it to the list.
            if (merged.isEmpty() || merged.getLast()[1] < interval[0]) {
                merged.add(interval);
            } 
            // Otherwise, there is an overlap, so we merge the current interval 
            // into the previous one by updating its end time.
            else {
                merged.getLast()[1] = Math.max(merged.getLast()[1], interval[1]);
            }
        }

        // Convert the list back to a 2D array
        return merged.toArray(new int[merged.size()][]);
    }
}
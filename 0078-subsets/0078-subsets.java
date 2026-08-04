import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> current, List<List<Integer>> result) {
        // Add a copy of the current state to the answer
        result.add(new ArrayList<>(current));

        for (int i = start; i < nums.length; i++) {
            current.add(nums[i]);                         // Include element
            backtrack(i + 1, nums, current, result);      // Recurse
            current.remove(current.size() - 1);            // Backtrack
        }
    }
}
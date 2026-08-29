import java.util.*;

class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        
        // Step 1: Create a sorted copy of nums
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        // Step 2: Group elements into connected components
        Map<Integer, Integer> numToGroup = new HashMap<>();
        Map<Integer, Deque<Integer>> groupToDeque = new HashMap<>();
        
        int group = 0;
        numToGroup.put(sortedNums[0], group);
        groupToDeque.put(group, new ArrayDeque<>());
        groupToDeque.get(group).offer(sortedNums[0]);
        
        for (int i = 1; i < n; i++) {
            if (sortedNums[i] - sortedNums[i - 1] > limit) {
                group++;
            }
            numToGroup.put(sortedNums[i], group);
            groupToDeque.computeIfAbsent(group, k -> new ArrayDeque<>()).offer(sortedNums[i]);
        }
        
        // Step 3: Reconstruct the answer by placing the smallest available element in each group
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            int groupId = numToGroup.get(nums[i]);
            result[i] = groupToDeque.get(groupId).pollFirst();
        }
        
        return result;
    }
}
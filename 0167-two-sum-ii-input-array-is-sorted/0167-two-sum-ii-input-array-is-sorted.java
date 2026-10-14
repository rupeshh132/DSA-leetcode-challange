class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int left = 0;
        int right = numbers.length - 1;

        while (left < right) {
            int sum = numbers[left] + numbers[right];

            if (sum == target) {
                // Problem requires 1-based indexing
                return new int[] { left + 1, right + 1 };
            } else if (sum < target) {
                // Sum is too small; move left pointer right to increase sum
                left++;
            } else {
                // Sum is too large; move right pointer left to decrease sum
                right--;
            }
        }

        return new int[] {}; // Fallback, guaranteed solution per constraints
    }
}
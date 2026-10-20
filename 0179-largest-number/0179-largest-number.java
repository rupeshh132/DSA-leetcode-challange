import java.util.Arrays;

class Solution {
    public String largestNumber(int[] nums) {
        // Convert integer array to String array
        String[] strNums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            strNums[i] = String.valueOf(nums[i]);
        }

        // Custom sort: compare (b + a) with (a + b) for descending order
        Arrays.sort(strNums, (a, b) -> (b + a).compareTo(a + b));

        // Edge case: if the highest value is "0" (e.g., [0, 0]), return "0"
        if (strNums[0].equals("0")) {
            return "0";
        }

        // Build the final result string
        StringBuilder sb = new StringBuilder();
        for (String s : strNums) {
            sb.append(s);
        }

        return sb.toString();
    }
}
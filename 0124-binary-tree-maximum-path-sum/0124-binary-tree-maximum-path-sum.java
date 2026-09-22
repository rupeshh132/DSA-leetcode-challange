class Solution {
    private int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        calculateMaxGain(root);
        return maxSum;
    }

    private int calculateMaxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        // Ignore paths that contribute negatively
        int leftGain = Math.max(0, calculateMaxGain(node.left));
        int rightGain = Math.max(0, calculateMaxGain(node.right));

        // Price of the path where the current node is the highest point (turning point)
        int currentPathSum = node.val + leftGain + rightGain;

        // Update the global maximum if the current full path is larger
        maxSum = Math.max(maxSum, currentPathSum);

        // Return the maximum branch sum that can be extended to the parent
        return node.val + Math.max(leftGain, rightGain);
    }
}
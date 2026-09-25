/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int currentSum) {
        if (node == null) {
            return 0;
        }

        // Shift existing digits by one decimal place and add current node's value
        currentSum = currentSum * 10 + node.val;

        // If it's a leaf node, return the accumulated path number
        if (node.left == null && node.right == null) {
            return currentSum;
        }

        // Otherwise, sum the results from left and right subtrees
        return dfs(node.left, currentSum) + dfs(node.right, currentSum);
    }
}
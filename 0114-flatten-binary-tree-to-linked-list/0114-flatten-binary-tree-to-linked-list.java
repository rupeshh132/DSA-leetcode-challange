class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        
        while (curr != null) {
            if (curr.left != null) {
                // Find the rightmost node of the left subtree
                TreeNode prev = curr.left;
                while (prev.right != null) {
                    prev = prev.right;
                }
                
                // Connect the original right subtree to the rightmost node
                prev.right = curr.right;
                
                // Move the entire left subtree to the right
                curr.right = curr.left;
                curr.left = null;
            }
            // Move to the next node down the right branch
            curr = curr.right;
        }
    }
}
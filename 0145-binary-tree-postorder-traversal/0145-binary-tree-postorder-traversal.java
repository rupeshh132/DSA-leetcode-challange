import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traverse(root, result);
        return result;
    }

    private void traverse(TreeNode node, List<Integer> result) {
        if (node == null) {
            return;
        }
        
        // 1. Traverse the left subtree
        traverse(node.left, result);
        
        // 2. Traverse the right subtree
        traverse(node.right, result);
        
        // 3. Visit the root (current) node
        result.add(node.val);
    }
}
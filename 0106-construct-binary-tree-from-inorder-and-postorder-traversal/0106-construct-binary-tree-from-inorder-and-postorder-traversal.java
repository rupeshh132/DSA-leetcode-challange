import java.util.HashMap;
import java.util.Map;

class Solution {
    private int postIdx;
    private Map<Integer, Integer> inorderMap;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIdx = postorder.length - 1;
        inorderMap = new HashMap<>();

        // Store inorder value indices for O(1) lookup
        for (int i = 0; i < inorder.length; i++) {
            inorderMap.put(inorder[i], i);
        }

        return helper(postorder, 0, inorder.length - 1);
    }

    private TreeNode helper(int[] postorder, int inStart, int inEnd) {
        if (inStart > inEnd) {
            return null;
        }

        // Pick current root from postorder traversal
        int rootVal = postorder[postIdx--];
        TreeNode root = new TreeNode(rootVal);

        // Find position of current root in inorder traversal
        int rootIdx = inorderMap.get(rootVal);

        // Build right subtree before left subtree because postIdx decrements from the end
        root.right = helper(postorder, rootIdx + 1, inEnd);
        root.left = helper(postorder, inStart, rootIdx - 1);

        return root;
    }
}
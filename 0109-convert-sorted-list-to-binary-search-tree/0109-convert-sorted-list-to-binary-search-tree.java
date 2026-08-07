class Solution {
    private ListNode headPtr;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) {
            return null;
        }

        // 1. Calculate the total size of the linked list
        int size = getLength(head);
        this.headPtr = head;

        // 2. Build the BST using inorder traversal
        return buildBST(0, size - 1);
    }

    private int getLength(ListNode head) {
        int length = 0;
        ListNode curr = head;
        while (curr != null) {
            length++;
            curr = curr.next;
        }
        return length;
    }

    private TreeNode buildBST(int left, int right) {
        if (left > right) {
            return null;
        }

        int mid = left + (right - left) / 2;

        // Recursively build the left subtree
        TreeNode leftChild = buildBST(left, mid - 1);

        // Process current node (equivalent to root of this subtree)
        TreeNode root = new TreeNode(headPtr.val);
        root.left = leftChild;

        // Move head pointer to next list node
        headPtr = headPtr.next;

        // Recursively build the right subtree
        root.right = buildBST(mid + 1, right);

        return root;
    }
}
class Solution {
    public Node connect(Node root) {
        if (root == null) {
            return null;
        }

        // Start from the root; leftmost tracks the start of each level
        Node leftmost = root;

        // Since it's a perfect binary tree, if leftmost.left is null, we reached the leaf level
        while (leftmost.left != null) {
            Node curr = leftmost;

            // Traverse the current level like a linked list
            while (curr != null) {
                // Connection 1: Connect children of the same parent
                curr.left.next = curr.right;

                // Connection 2: Connect right child to the left child of the next parent
                if (curr.next != null) {
                    curr.right.next = curr.next.left;
                }

                // Move to the next node on the current level
                curr = curr.next;
            }

            // Move down to the leftmost node of the next level
            leftmost = leftmost.left;
        }

        return root;
    }
}
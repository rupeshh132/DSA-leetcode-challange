class Solution {
    public Node connect(Node root) {
        Node curr = root;

        while (curr != null) {
            // Dummy head to build the linked list for the next level
            Node dummyHead = new Node(0);
            Node prev = dummyHead;

            // Traverse the current level using 'next' pointers
            while (curr != null) {
                if (curr.left != null) {
                    prev.next = curr.left;
                    prev = prev.next;
                }
                if (curr.right != null) {
                    prev.next = curr.right;
                    prev = prev.next;
                }
                curr = curr.next;
            }

            // Move down to the first node of the next level
            curr = dummyHead.next;
        }

        return root;
    }
}
class Solution {
    static class Node {
        int maxLen;
        int prefLen;
        int suffLen;
        char leftChar;
        char rightChar;

        Node() {}

        Node(char c) {
            this.maxLen = 1;
            this.prefLen = 1;
            this.suffLen = 1;
            this.leftChar = c;
            this.rightChar = c;
        }
    }

    private Node[] tree;
    private char[] sChars;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        int k = queryIndices.length;
        sChars = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            int idx = queryIndices[i];
            char c = queryCharacters.charAt(i);

            update(1, 0, n - 1, idx, c);
            result[i] = tree[1].maxLen;
        }

        return result;
    }

    private Node merge(Node left, Node right, int leftLen, int rightLen) {
        Node res = new Node();
        res.leftChar = left.leftChar;
        res.rightChar = right.rightChar;

        res.maxLen = Math.max(left.maxLen, right.maxLen);
        if (left.rightChar == right.leftChar) {
            res.maxLen = Math.max(res.maxLen, left.suffLen + right.prefLen);
        }

        // Prefix length calculation
        if (left.prefLen == leftLen && left.rightChar == right.leftChar) {
            res.prefLen = leftLen + right.prefLen;
        } else {
            res.prefLen = left.prefLen;
        }

        // Suffix length calculation
        if (right.suffLen == rightLen && left.rightChar == right.leftChar) {
            res.suffLen = rightLen + left.suffLen;
        } else {
            res.suffLen = right.suffLen;
        }

        return res;
    }

    private void build(int node, int start, int end) {
        if (start == end) {
            tree[node] = new Node(sChars[start]);
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        build(leftChild, start, mid);
        build(rightChild, mid + 1, end);

        tree[node] = merge(tree[leftChild], tree[rightChild], mid - start + 1, end - mid);
    }

    private void update(int node, int start, int end, int idx, char c) {
        if (start == end) {
            sChars[idx] = c;
            tree[node] = new Node(c);
            return;
        }

        int mid = start + (end - start) / 2;
        int leftChild = 2 * node;
        int rightChild = 2 * node + 1;

        if (idx <= mid) {
            update(leftChild, start, mid, idx, c);
        } else {
            update(rightChild, mid + 1, end, idx, c);
        }

        tree[node] = merge(tree[leftChild], tree[rightChild], mid - start + 1, end - mid);
    }
}
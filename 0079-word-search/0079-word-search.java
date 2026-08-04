class Solution {
    public boolean exist(char[][] board, String word) {
        int rows = board.length;
        int cols = board[0].length;
        
        // Quick prune: length constraint
        if (word.length() > rows * cols) {
            return false;
        }

        // Frequency Pruning: check if board has enough counts of each char
        int[] boardCounts = new int[128];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                boardCounts[board[r][c]]++;
            }
        }
        
        int[] wordCounts = new int[128];
        for (char ch : word.toCharArray()) {
            wordCounts[ch]++;
            if (wordCounts[ch] > boardCounts[ch]) {
                return false; // Not enough characters in board
            }
        }

        // Direction optimization: reverse word if last char occurs less frequently than first char
        if (boardCounts[word.charAt(0)] > boardCounts[word.charAt(word.length() - 1)]) {
            word = new StringBuilder(word).reverse().toString();
        }

        // Search starting from every matching starting cell
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == word.charAt(0)) {
                    if (dfs(board, word, r, c, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int r, int c, int index) {
        // Base case: matched full word
        if (index == word.length()) {
            return true;
        }

        // Boundary checks and character match check
        if (r < 0 || r >= board.length || c < 0 || c >= board[0].length || board[r][c] != word.charAt(index)) {
            return false;
        }

        // Mark cell as visited
        char temp = board[r][c];
        board[r][c] = '#';

        // Explore all 4 adjacent cells
        boolean found = dfs(board, word, r + 1, c, index + 1) ||
                        dfs(board, word, r - 1, c, index + 1) ||
                        dfs(board, word, r, c + 1, index + 1) ||
                        dfs(board, word, r, c - 1, index + 1);

        // Backtrack: restore original character
        board[r][c] = temp;

        return found;
    }
}
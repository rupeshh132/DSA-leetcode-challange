import java.util.*;

class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> result = new ArrayList<>();

        if (!dict.contains(endWord)) {
            return result;
        }

        // Map each word to a list of its predecessors in the shortest path(s)
        Map<String, List<String>> predecessors = new HashMap<>();
        
        // BFS to find the shortest distance and build the predecessor graph
        Set<String> currentLevel = new HashSet<>();
        currentLevel.add(beginWord);
        dict.remove(beginWord);

        boolean found = false;

        while (!currentLevel.isEmpty() && !found) {
            // Remove all words in the current level from the dictionary
            // to avoid visiting them at later levels
            dict.removeAll(currentLevel);
            Set<String> nextLevel = new HashSet<>();

            for (String word : currentLevel) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char originalChar = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        chars[i] = c;
                        String nextWord = new String(chars);

                        if (dict.contains(nextWord)) {
                            if (nextWord.equals(endWord)) {
                                found = true;
                            }
                            nextLevel.add(nextWord);
                            predecessors.computeIfAbsent(nextWord, k -> new ArrayList<>()).add(word);
                        }
                    }
                    chars[i] = originalChar;
                }
            }
            currentLevel = nextLevel;
        }

        // Backtrack using DFS from endWord back to beginWord to reconstruct paths
        if (found) {
            List<String> path = new ArrayList<>();
            path.add(endWord);
            backtrack(endWord, beginWord, predecessors, path, result);
        }

        return result;
    }

    private void backtrack(String word, String beginWord, Map<String, List<String>> predecessors, 
                           List<String> path, List<List<String>> result) {
        if (word.equals(beginWord)) {
            List<String> validPath = new ArrayList<>(path);
            Collections.reverse(validPath);
            result.add(validPath);
            return;
        }

        List<String> parents = predecessors.get(word);
        if (parents == null) return;

        for (String parent : parents) {
            path.add(parent);
            backtrack(parent, beginWord, predecessors, path, result);
            path.remove(path.size() - 1);
        }
    }
}
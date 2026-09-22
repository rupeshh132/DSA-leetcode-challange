import java.util.*;

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) {
            return 0;
        }

        // Two-ended (bidirectional) BFS sets
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();
        Set<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);
        visited.add(beginWord);
        visited.add(endWord);

        int level = 1;

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            // Always expand the smaller frontier to minimize search space
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> nextLevel = new HashSet<>();

            for (String word : beginSet) {
                char[] chars = word.toCharArray();

                for (int i = 0; i < chars.length; i++) {
                    char originalChar = chars[i];

                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == originalChar) continue;
                        chars[i] = c;
                        String nextWord = new String(chars);

                        // If the frontiers meet, the shortest path is found
                        if (endSet.contains(nextWord)) {
                            return level + 1;
                        }

                        if (dict.contains(nextWord) && !visited.contains(nextWord)) {
                            nextLevel.add(nextWord);
                            visited.add(nextWord);
                        }
                    }
                    chars[i] = originalChar;
                }
            }

            beginSet = nextLevel;
            level++;
        }

        return 0;
    }
}
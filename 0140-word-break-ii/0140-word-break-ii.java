import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<String, List<String>> memo = new HashMap<>();
        return dfs(s, wordSet, memo);
    }

    private List<String> dfs(String s, Set<String> wordSet, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        List<String> result = new ArrayList<>();

        // Base case: empty suffix
        if (s.isEmpty()) {
            result.add("");
            return result;
        }

        // Try every prefix of the current string
        for (int i = 1; i <= s.length(); i++) {
            String prefix = s.substring(0, i);
            
            if (wordSet.contains(prefix)) {
                String suffix = s.substring(i);
                List<String> suffixSentences = dfs(suffix, wordSet, memo);

                for (String sentence : suffixSentences) {
                    if (sentence.isEmpty()) {
                        result.add(prefix);
                    } else {
                        result.add(prefix + " " + sentence);
                    }
                }
            }
        }

        memo.put(s, result);
        return result;
    }
}
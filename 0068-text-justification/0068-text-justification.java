import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int i = 0;
        int n = words.length;
        
        while (i < n) {
            int j = i + 1;
            int lineLength = words[i].length();
            
            // Greedily find how many words fit into the current line
            while (j < n && lineLength + 1 + words[j].length() <= maxWidth) {
                lineLength += 1 + words[j].length();
                j++;
            }
            
            StringBuilder sb = new StringBuilder();
            int numWords = j - i;
            
            // Scenario 1 & 2: It's the last line OR the line contains only 1 word
            if (j == n || numWords == 1) {
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    if (k < j - 1) {
                        sb.append(" ");
                    }
                }
                // Pad trailing spaces until the line hits maxWidth
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
            } 
            // Scenario 3: Standard middle line with multiple words (Fully-Justified)
            else {
                int totalWordsLength = 0;
                for (int k = i; k < j; k++) {
                    totalWordsLength += words[k].length();
                }
                
                int totalSpaces = maxWidth - totalWordsLength;
                int spaceSlots = numWords - 1;
                
                int baseSpaces = totalSpaces / spaceSlots;
                int extraSpaces = totalSpaces % spaceSlots;
                
                for (int k = i; k < j; k++) {
                    sb.append(words[k]);
                    
                    // Don't append spaces after the last word of the line
                    if (k < j - 1) {
                        // Add the base even spacing
                        for (int s = 0; s < baseSpaces; s++) {
                            sb.append(" ");
                        }
                        // Distribute remainder extra spaces to the leftmost slots
                        if (k - i < extraSpaces) {
                            sb.append(" ");
                        }
                    }
                }
            }
            
            result.add(sb.toString());
            i = j; // Move to the next line chunk
        }
        
        return result;
    }
}
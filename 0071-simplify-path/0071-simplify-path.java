import java.util.Deque;
import java.util.LinkedList;

class Solution {
    public String simplifyPath(String path) {
        // Use a Deque as a stack to track the canonical directory structure
        Deque<String> stack = new LinkedList<>();
        
        // Split the path by slashes
        String[] components = path.split("/");
        
        for (String dir : components) {
            // Case 1: Empty string (from consecutive slashes) or current directory "." -> Skip
            if (dir.isEmpty() || dir.equals(".")) {
                continue;
            }
            
            // Case 2: Parent directory ".." -> Go up one level by popping from stack
            if (dir.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } 
            // Case 3: Valid directory name (e.g., "home", "...") -> Push onto stack
            else {
                stack.push(dir);
            }
        }
        
        // If the stack is empty, we are at the root directory "/"
        if (stack.isEmpty()) {
            return "/";
        }
        
        // Rebuild the final canonical path from the bottom of the stack up
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append("/").append(stack.pollLast());
        }
        
        return sb.toString();
    }
}
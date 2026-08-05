import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        // Build adjacency list for graph traversal
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] inv : invocations) {
            adj.get(inv[0]).add(inv[1]);
        }

        // Identify all suspicious methods reachable from k
        boolean[] isSuspicious = new boolean[n];
        dfs(k, adj, isSuspicious);

        // Check if any non-suspicious method invokes a suspicious method
        boolean externalInvocationFound = false;
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!isSuspicious[u] && isSuspicious[v]) {
                externalInvocationFound = true;
                break;
            }
        }

        // Return results based on validity of removal
        List<Integer> result = new ArrayList<>();
        if (externalInvocationFound) {
            for (int i = 0; i < n; i++) {
                result.add(i);
            }
        } else {
            for (int i = 0; i < n; i++) {
                if (!isSuspicious[i]) {
                    result.add(i);
                }
            }
        }

        return result;
    }

    private void dfs(int u, List<List<Integer>> adj, boolean[] isSuspicious) {
        isSuspicious[u] = true;
        for (int v : adj.get(u)) {
            if (!isSuspicious[v]) {
                dfs(v, adj, isSuspicious);
            }
        }
    }
}
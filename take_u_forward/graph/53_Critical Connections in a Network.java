import java.util.*;

class Solution {

    private int timer = 1;

    private void dfs(int node, int parent,
                     boolean[] vis,
                     List<List<Integer>> adj,
                     int[] tin,
                     int[] low,
                     List<List<Integer>> bridges) {

        vis[node] = true;

        tin[node] = low[node] = timer++;

        for (int neighbor : adj.get(node)) {

            // Skip the edge to parent
            if (neighbor == parent) continue;

            // If not visited
            if (!vis[neighbor]) {

                dfs(neighbor, node, vis, adj, tin, low, bridges);

                // Update low value
                low[node] = Math.min(low[node], low[neighbor]);

                // Bridge condition
                if (low[neighbor] > tin[node]) {
                    bridges.add(Arrays.asList(node, neighbor));
                }

            } else {

                // Back edge
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }
    }

    public List<List<Integer>> criticalConnections(int n,
                                                   List<List<Integer>> connections) {

        // Adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph
        for (List<Integer> edge : connections) {

            int u = edge.get(0);
            int v = edge.get(1);

            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        boolean[] vis = new boolean[n];
        int[] tin = new int[n];
        int[] low = new int[n];

        List<List<Integer>> bridges = new ArrayList<>();

        // DFS for all components
        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, -1, vis, adj, tin, low, bridges);
            }
        }

        return bridges;
    }
}
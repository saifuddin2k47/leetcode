import java.util.*;

class Solution {

    private boolean dfs(int node, int parent, int[] vis,
                        ArrayList<ArrayList<Integer>> adj) {

        vis[node] = 1;

        for (int adjacentNode : adj.get(node)) {
            if (vis[adjacentNode] == 0) {
                if (dfs(adjacentNode, node, vis, adj)) {
                    return true;
                }
            }
            else if (adjacentNode != parent) {
                return true; // cycle detected
            }
        }
        return false;
    }

    public boolean isCycle(int V, int[][] edges) {

        // 1️⃣ Create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // 2️⃣ Fill adjacency list
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // 3️⃣ Visited array
        int[] vis = new int[V];

        // 4️⃣ Check all components
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                if (dfs(i, -1, vis, adj)) {
                    return true;
                }
            }
        }
        return false;
    }
}

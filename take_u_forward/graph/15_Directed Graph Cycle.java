import java.util.*;

class Solution {

    private boolean dfsCheck(int node,
                             ArrayList<ArrayList<Integer>> adj,
                             int[] vis,
                             int[] pathVis) {

        vis[node] = 1;
        pathVis[node] = 1;

        // Traverse adjacent nodes
        for (int it : adj.get(node)) {

            // If node is not visited
            if (vis[it] == 0) {
                if (dfsCheck(it, adj, vis, pathVis)) {
                    return true;
                }
            }
            // If node is visited and in current path
            else if (pathVis[it] == 1) {
                return true;
            }
        }

        pathVis[node] = 0; // backtrack
        return false;
    }

    public boolean isCyclic(int V, int[][] edges) {

        // Create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph
        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
        }

        int[] vis = new int[V];
        int[] pathVis = new int[V];

        // Run DFS for each component
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                if (dfsCheck(i, adj, vis, pathVis)) {
                    return true;
                }
            }
        }

        return false;
    }
}

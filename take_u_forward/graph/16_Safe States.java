import java.util.*;

class Solution {

    private boolean dfsCheck(int node,
                             List<List<Integer>> adj,
                             int[] vis,
                             int[] pathVis,
                             int[] check) {

        vis[node] = 1;
        pathVis[node] = 1;
        check[node] = 0;

        for (int it : adj.get(node)) {
            // if not visited
            if (vis[it] == 0) {
                if (dfsCheck(it, adj, vis, pathVis, check)) {
                    check[node] = 0;
                    return true;
                }
            }
            // cycle detected
            else if (pathVis[it] == 1) {
                check[node] = 0;
                return true;
            }
        }

        check[node] = 1;      // safe node
        pathVis[node] = 0;    // backtrack
        return false;
    }

    public ArrayList<Integer> safeNodes(int V, int[][] edges) {

        // build adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
        }

        int[] vis = new int[V];
        int[] pathVis = new int[V];
        int[] check = new int[V];

        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfsCheck(i, adj, vis, pathVis, check);
            }
        }

        ArrayList<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (check[i] == 1) {
                safeNodes.add(i);
            }
        }

        return safeNodes;
    }
}
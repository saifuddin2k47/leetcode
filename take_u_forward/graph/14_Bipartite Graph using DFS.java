import java.util.*;

class Solution {

    private boolean dfs(int node, int col, int[] color, ArrayList<ArrayList<Integer>> adj) {
        color[node] = col;

        for (int it : adj.get(node)) {
            if (color[it] == -1) {
                if (!dfs(it, 1 - col, color, adj)) {
                    return false;
                }
            }
            else if (color[it] == col) {
                return false;
            }
        }
        return true;
    }

    public boolean isBipartite(int V, int[][] edges) {

        // Build adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        int[] color = new int[V];
        Arrays.fill(color, -1);

        // Handle disconnected components
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!dfs(i, 0, color, adj)) {
                    return false;
                }
            }
        }
        return true;
    }
}
import java.util.*;

class Solution {

    private boolean check(int start, ArrayList<ArrayList<Integer>> adj, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        color[start] = 0;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int it : adj.get(node)) {
                // if adjacent node is not colored
                if (color[it] == -1) {
                    color[it] = 1 - color[node];
                    q.offer(it);
                }
                // if adjacent node has same color
                else if (color[it] == color[node]) {
                    return false;
                }
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
                if (!check(i, adj, color)) {
                    return false;
                }
            }
        }

        return true;
    }
}
import java.util.*;

class Solution {

    // DFS for ordering
    private void dfs(int node, int[] vis, List<List<Integer>> adj, Stack<Integer> st) {

        vis[node] = 1;

        for (int it : adj.get(node)) {

            if (vis[it] == 0) {
                dfs(it, vis, adj, st);
            }
        }

        st.push(node);
    }

    // DFS on transpose graph
    private void dfs3(int node, int[] vis, List<List<Integer>> adjT) {

        vis[node] = 1;

        for (int it : adjT.get(node)) {

            if (vis[it] == 0) {
                dfs3(it, vis, adjT);
            }
        }
    }

    // Function to find SCC count
    public int kosaraju(int V, int[][] edges) {

        // Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build graph
        for (int[] e : edges) {

            int u = e[0];
            int v = e[1];

            adj.get(u).add(v);
        }

        int[] vis = new int[V];

        Stack<Integer> st = new Stack<>();

        // Step 1: DFS ordering
        for (int i = 0; i < V; i++) {

            if (vis[i] == 0) {
                dfs(i, vis, adj, st);
            }
        }

        // Step 2: Create transpose graph
        List<List<Integer>> adjT = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adjT.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {

            vis[i] = 0;

            for (int it : adj.get(i)) {

                adjT.get(it).add(i);
            }
        }

        // Step 3: Count SCCs
        int scc = 0;

        while (!st.isEmpty()) {

            int node = st.pop();

            if (vis[node] == 0) {

                scc++;

                dfs3(node, vis, adjT);
            }
        }

        return scc;
    }
}
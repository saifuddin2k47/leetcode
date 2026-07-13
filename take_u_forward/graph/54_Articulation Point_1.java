class Solution {
    
    private int timer = 1;

    // DFS function to find articulation points
    private void dfs(int node, int parent, int[] vis, int[] tin,
                     int[] low, int[] mark,
                     ArrayList<ArrayList<Integer>> adj) {

        vis[node] = 1;
        tin[node] = low[node] = timer++;

        int child = 0;

        for (int neighbor : adj.get(node)) {

            // Ignore the parent node
            if (neighbor == parent) continue;

            // If neighbor is not visited
            if (vis[neighbor] == 0) {

                dfs(neighbor, node, vis, tin, low, mark, adj);

                // Update low value
                low[node] = Math.min(low[node], low[neighbor]);

                // Articulation point condition
                if (low[neighbor] >= tin[node] && parent != -1) {
                    mark[node] = 1;
                }

                child++;
            }
            else {
                // Back edge
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }

        // Special case for root node
        if (parent == -1 && child > 1) {
            mark[node] = 1;
        }
    }

    // Function to return articulation points in the graph.
    public ArrayList<Integer> articulationPoints(int V,
                                                 ArrayList<ArrayList<Integer>> adj) {

        int[] vis = new int[V];
        int[] tin = new int[V];
        int[] low = new int[V];
        int[] mark = new int[V];

        ArrayList<Integer> ans = new ArrayList<>();

        // Run DFS for all components
        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                dfs(i, -1, vis, tin, low, mark, adj);
            }
        }

        // Store articulation points
        for (int i = 0; i < V; i++) {
            if (mark[i] == 1) {
                ans.add(i);
            }
        }

        // If no articulation point exists
        if (ans.size() == 0) {
            ans.add(-1);
        }

        return ans;
    }
}
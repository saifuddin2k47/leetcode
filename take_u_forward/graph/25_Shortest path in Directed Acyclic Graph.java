class Solution {

    // DFS for Topological Sort
    private void topoSort(int node, boolean[] visited,
                          List<List<int[]>> adj, Deque<Integer> stack) {

        visited[node] = true;

        for (int[] edge : adj.get(node)) {
            int v = edge[0];
            if (!visited[v]) {
                topoSort(v, visited, adj, stack);
            }
        }

        stack.push(node); // add after visiting all neighbors
    }

    public int[] shortestPath(int V, int E, int[][] edges) {

        // Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            int wt = edges[i][2];
            adj.get(u).add(new int[]{v, wt});
        }

        // Topological Sort
        boolean[] visited = new boolean[V];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                topoSort(i, visited, adj, stack);
            }
        }

        // Distance array
        int INF = (int) 1e9;
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[0] = 0; // source node

        // Relax edges in topological order
        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (dist[node] != INF) {
                for (int[] edge : adj.get(node)) {
                    int v = edge[0];
                    int wt = edge[1];
                    if (dist[node] + wt < dist[v]) {
                        dist[v] = dist[node] + wt;
                    }
                }
            }
        }

        // Replace unreachable nodes with -1
        for (int i = 0; i < V; i++) {
            if (dist[i] == INF) {
                dist[i] = -1;
            }
        }

        return dist;
    }
}
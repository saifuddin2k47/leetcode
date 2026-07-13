class Solution {
    public int[] bellmanFord(int V, int[][] edges, int src) {
        int[] dist = new int[V];

        // Initialize distances
        for (int i = 0; i < V; i++) {
            dist[i] = (int) 1e8;
        }
        dist[src] = 0;

        // Relax all edges V-1 times
        for (int i = 0; i < V - 1; i++) {
            for (int j = 0; j < edges.length; j++) {
                int u = edges[j][0];
                int v = edges[j][1];
                int wt = edges[j][2];

                if (dist[u] != (int)1e8 && dist[u] + wt < dist[v]) {
                    dist[v] = dist[u] + wt;
                }
            }
        }

        // Check for negative weight cycle
        for (int j = 0; j < edges.length; j++) {
            int u = edges[j][0];
            int v = edges[j][1];
            int wt = edges[j][2];

            if (dist[u] != (int)1e8 && dist[u] + wt < dist[v]) {
                return new int[]{-1};
            }
        }

        return dist;
    }
}

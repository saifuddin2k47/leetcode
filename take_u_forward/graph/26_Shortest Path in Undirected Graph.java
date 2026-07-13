import java.util.*;

class Solution {
    public int[] shortestPath(int V, int[][] edges, int src) {
        
        // Create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Build the undirected graph
        for (int i = 0; i < edges.length; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // Distance array
        int INF = (int) 1e9;
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        // BFS queue
        Queue<Integer> q = new LinkedList<>();
        q.add(src);

        // BFS traversal
        while (!q.isEmpty()) {
            int node = q.poll();

            for (int it : adj.get(node)) {
                if (dist[node] + 1 < dist[it]) {
                    dist[it] = dist[node] + 1;
                    q.add(it);
                }
            }
        }

        // Mark unreachable nodes
        for (int i = 0; i < V; i++) {
            if (dist[i] == INF) {
                dist[i] = -1;
            }
        }

        return dist;
    }
}

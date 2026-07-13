import java.util.*;

class Pair {
    int distance;
    int node;

    Pair(int distance, int node) {
        this.distance = distance;
        this.node = node;
    }
}

class Solution {
    public int[] dijkstra(int V, int[][] edges, int src) {

        // Step 1: Create adjacency list
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Build graph (undirected)
        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adj.get(u).add(new Pair(w, v));
            adj.get(v).add(new Pair(w, u));
        }

        // Step 3: Distance array
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;

        // Step 4: Min-heap priority queue
        PriorityQueue<Pair> pq =
            new PriorityQueue<>((a, b) -> Integer.compare(a.distance, b.distance));

        pq.add(new Pair(0, src));

        // Step 5: Dijkstra
        while (!pq.isEmpty()) {
            Pair cur = pq.poll();
            int dis = cur.distance;
            int node = cur.node;

            if (dis > dist[node]) continue;

            for (Pair it : adj.get(node)) {
                int edgeWeight = it.distance;
                int adjNode = it.node;

                if (dis + edgeWeight < dist[adjNode]) {
                    dist[adjNode] = dis + edgeWeight;
                    pq.add(new Pair(dist[adjNode], adjNode));
                }
            }
        }

        return dist;
    }
}

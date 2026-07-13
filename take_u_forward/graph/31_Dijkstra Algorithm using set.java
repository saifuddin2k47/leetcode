import java.util.*;

class Solution {

    static class Pair {
        int dist;
        int node;

        Pair(int dist, int node) {
            this.dist = dist;
            this.node = node;
        }
    }

    public int[] dijkstra(int V, int[][] edges, int src) {

        // Step 1: Build adjacency list
        List<List<Pair>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            adj.get(u).add(new Pair(w, v));
            adj.get(v).add(new Pair(w, u)); // Undirected graph
        }

        // Step 2: Distance array
        int[] dist = new int[V];
        Arrays.fill(dist, (int) 1e9);
        dist[src] = 0;

        // Step 3: TreeSet (distance, node)
        TreeSet<Pair> set = new TreeSet<>(
            (a, b) -> a.dist != b.dist ? a.dist - b.dist : a.node - b.node
        );

        set.add(new Pair(0, src));

        // Step 4: Dijkstra
        while (!set.isEmpty()) {

            Pair cur = set.pollFirst();
            int node = cur.node;
            int currDist = cur.dist;

            for (Pair p : adj.get(node)) {
                int adjNode = p.node;
                int edgeW = p.dist;

                if (currDist + edgeW < dist[adjNode]) {

                    // Remove old pair if exists
                    if (dist[adjNode] != (int) 1e9) {
                        set.remove(new Pair(dist[adjNode], adjNode));
                    }

                    dist[adjNode] = currDist + edgeW;
                    set.add(new Pair(dist[adjNode], adjNode));
                }
            }
        }

        return dist;
    }
}

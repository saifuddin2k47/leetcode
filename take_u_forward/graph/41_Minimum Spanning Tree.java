import java.util.*;

class Pair {
    int node;
    int distance;

    Pair(int distance, int node) {
        this.node = node;
        this.distance = distance;
    }
}

class Solution {
    public int spanningTree(int V, int[][] edges) {

        // Step 1: Build adjacency list
        ArrayList<ArrayList<ArrayList<Integer>>> adj = new ArrayList<>();

        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];

            adj.get(u).add(new ArrayList<>(Arrays.asList(v, w)));
            adj.get(v).add(new ArrayList<>(Arrays.asList(u, w)));
        }

        // Step 2: Prim's Algorithm
        PriorityQueue<Pair> pq =
            new PriorityQueue<>((a, b) -> a.distance - b.distance);

        int[] vis = new int[V];
        pq.add(new Pair(0, 0)); // {weight, node}
        int sum = 0;

        while (!pq.isEmpty()) {
            Pair p = pq.poll();
            int node = p.node;
            int wt = p.distance;

            if (vis[node] == 1) continue;

            vis[node] = 1;
            sum += wt;

            for (ArrayList<Integer> it : adj.get(node)) {
                int adjNode = it.get(0);
                int edW = it.get(1);

                if (vis[adjNode] == 0) {
                    pq.add(new Pair(edW, adjNode));
                }
            }
        }

        return sum;
    }
}

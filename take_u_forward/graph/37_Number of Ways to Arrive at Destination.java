class Solution {
    public int countPaths(int V, int[][] edges) {

        // Adjacency list: node -> {neighbor, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < V; i++) adj.add(new ArrayList<>());

        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }

        // Min-heap: {distance, node}
        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        long[] dist = new long[V];
        Arrays.fill(dist, Long.MAX_VALUE);

        int[] ways = new int[V];
        int mod = 1_000_000_007;

        dist[0] = 0;
        ways[0] = 1;

        pq.add(new long[]{0, 0});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long dis = cur[0];
            int node = (int) cur[1];

            // IMPORTANT optimization
            if (dis > dist[node]) continue;

            for (int[] nb : adj.get(node)) {
                int adjNode = nb[0];
                long edW = nb[1];

                if (dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    ways[adjNode] = ways[node];
                    pq.add(new long[]{dist[adjNode], adjNode});
                }
                else if (dis + edW == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        return ways[V - 1];
    }
}
import java.util.*;

class Solution {

    public List<Integer> shortestPath(int n, int m, int[][] edges) {

        // Adjacency list: node -> {adjNode, weight}
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>());
        }

        for (int[] e : edges) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }

        // Min heap {distance, node}
        PriorityQueue<long[]> pq =
                new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        long[] dist = new long[n + 1];
        Arrays.fill(dist, Long.MAX_VALUE);

        int[] parent = new int[n + 1];
        for (int i = 1; i <= n; i++) parent[i] = i;

        dist[1] = 0;
        pq.add(new long[]{0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long dis = cur[0];
            int node = (int) cur[1];

            // 🔥 IMPORTANT optimization
            if (dis > dist[node]) continue;

            for (int[] it : adj.get(node)) {
                int adjNode = it[0];
                int wt = it[1];

                if (dis + wt < dist[adjNode]) {
                    dist[adjNode] = dis + wt;
                    parent[adjNode] = node;
                    pq.add(new long[]{dist[adjNode], adjNode});
                }
            }
        }

        // No path
        if (dist[n] == Long.MAX_VALUE)
            return List.of(-1);

        // Reconstruct path
        List<Integer> path = new ArrayList<>();
        int node = n;
        while (parent[node] != node) {
            path.add(node);
            node = parent[node];
        }
        path.add(1);
        Collections.reverse(path);

        // Required format
        List<Integer> ans = new ArrayList<>();
        ans.add((int) dist[n]); // total weight
        ans.addAll(path);

        return ans;
    }
}

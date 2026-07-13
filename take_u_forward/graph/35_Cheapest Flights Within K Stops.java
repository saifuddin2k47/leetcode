import java.util.*;

class Solution {

    public int CheapestFLight(int n, int[][] flights, int src, int dst, int k) {

        // Build adjacency list
        List<List<int[]>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] f : flights) {
            adj.get(f[0]).add(new int[]{f[1], f[2]});
        }

        // Queue: {stops, node, cost}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, src, 0});

        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int stops = cur[0];
            int node  = cur[1];
            int cost  = cur[2];

            if (stops > k) continue;

            for (int[] edge : adj.get(node)) {
                int nextNode = edge[0];
                int price    = edge[1];

                if (cost + price < dist[nextNode]) {
                    dist[nextNode] = cost + price;
                    q.offer(new int[]{stops + 1, nextNode, cost + price});
                }
            }
        }

        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }
}
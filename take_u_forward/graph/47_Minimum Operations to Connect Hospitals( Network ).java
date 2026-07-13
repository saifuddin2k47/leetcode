import java.util.*;

class Solution {

    // Find parent with path compression
    private int find(int[] parent, int x) {
        if (parent[x] != x)
            parent[x] = find(parent, parent[x]);
        return parent[x];
    }

    // Union by rank
    private void union(int[] parent, int[] rank, int x, int y) {
        int px = find(parent, x);
        int py = find(parent, y);
        if (px == py) return;

        if (rank[px] < rank[py]) parent[px] = py;
        else if (rank[px] > rank[py]) parent[py] = px;
        else {
            parent[py] = px;
            rank[px]++;
        }
    }

    // Required function signature
    public int minConnect(int V, int[][] edges) {

        // If not enough edges, impossible
        if (edges.length < V - 1) return -1;

        int[] parent = new int[V];
        int[] rank = new int[V];

        // Initialize DSU
        for (int i = 0; i < V; i++) parent[i] = i;

        // Union all edges
        for (int[] edge : edges) {
            union(parent, rank, edge[0], edge[1]);
        }

        // Count connected components
        Set<Integer> components = new HashSet<>();
        for (int i = 0; i < V; i++) {
            components.add(find(parent, i));
        }

        // Minimum operations needed
        return components.size() - 1;
    }
}

// User function Template for Java
class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int findUPar(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = findUPar(parent[node]); // Path compression
    }

    void unionByRank(int u, int v) {
        int pu = findUPar(u);
        int pv = findUPar(v);

        if (pu == pv) return;

        if (rank[pu] < rank[pv]) {
            parent[pu] = pv;
        } 
        else if (rank[pv] < rank[pu]) {
            parent[pv] = pu;
        } 
        else {
            parent[pv] = pu;
            rank[pu]++;
        }
    }
}
class Solution {
    static int kruskalsMST(int V, int[][] edges) {
        // code here
        
        // Sort edges by weight
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);

        DisjointSet ds = new DisjointSet(V);
        int sum = 0;

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int wt = e[2];

            if (ds.findUPar(u) != ds.findUPar(v)) {
                sum += wt;
                ds.unionByRank(u, v);
            }
        }

        return sum;
    }
}
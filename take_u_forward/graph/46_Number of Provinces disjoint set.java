// User function Template for Java

import java.util.*;

class DisjointSet {
    int[] parent, rank;

    DisjointSet(int n) {
        parent = new int[n];
        rank = new int[n];

        for(int i = 0; i < n; i++)
            parent[i] = i;
    }

    int find(int node) {
        if(parent[node] == node)
            return node;

        return parent[node] = find(parent[node]);
    }

    void unionByRank(int u, int v) {
        int pu = find(u);
        int pv = find(v);

        if(pu == pv) return;

        if(rank[pu] < rank[pv])
            parent[pu] = pv;
        else if(rank[pu] > rank[pv])
            parent[pv] = pu;
        else {
            parent[pv] = pu;
            rank[pu]++;
        }
    }
}

class Solution {
    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {

        DisjointSet ds = new DisjointSet(V);

        // Traverse adjacency matrix
        for(int i = 0; i < V; i++) {
            for(int j = i + 1; j < V; j++) {   // optimize for undirected graph
                if(adj.get(i).get(j) == 1) {
                    ds.unionByRank(i, j);
                }
            }
        }

        // Count unique parents
        int count = 0;
        for(int i = 0; i < V; i++) {
            if(ds.find(i) == i)
                count++;
        }

        return count;
    }
}
#include <bits/stdc++.h>
using namespace std;

class DisjointSet {
    vector<int> rank, parent;

public:
    DisjointSet(int n) {
        rank.resize(n, 0);
        parent.resize(n);
        for (int i = 0; i < n; i++)
            parent[i] = i;
    }

    int findUPar(int node) {
        if (node == parent[node])
            return node;
        return parent[node] = findUPar(parent[node]);
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
};

class Solution {
public:
    int kruskalsMST(int V, vector<vector<int>> &edges) {

        // Sort edges by weight
        sort(edges.begin(), edges.end(), [](vector<int> &a, vector<int> &b) {
            return a[2] < b[2];
        });

        DisjointSet ds(V);
        int sum = 0;

        for (auto &e : edges) {
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
};
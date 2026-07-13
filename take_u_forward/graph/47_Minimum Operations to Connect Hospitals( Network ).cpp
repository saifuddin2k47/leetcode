class DSU {
public:
    vector<int> parent, rank;

    DSU(int n) {
        parent.resize(n);
        rank.resize(n, 0);
        for(int i = 0; i < n; i++) parent[i] = i;
    }

    int find(int x) {
        if(parent[x] != x)
            parent[x] = find(parent[x]);
        return parent[x];
    }

    void unite(int x, int y) {
        int px = find(x);
        int py = find(y);

        if(px == py) return;

        if(rank[px] < rank[py])
            parent[px] = py;
        else if(rank[px] > rank[py])
            parent[py] = px;
        else {
            parent[py] = px;
            rank[px]++;
        }
    }
};

class Solution {
public:
    int minConnect(int V, vector<vector<int>>& edges) {
        // If not enough edges, impossible
        if(edges.size() < V - 1) return -1;

        DSU dsu(V);

        // Connect all edges
        for(auto &edge : edges) {
            dsu.unite(edge[0], edge[1]);
        }

        // Count connected components
        unordered_set<int> components;
        for(int i = 0; i < V; i++) {
            components.insert(dsu.find(i));
        }

        // Minimum operations needed
        return components.size() - 1;
    }
};

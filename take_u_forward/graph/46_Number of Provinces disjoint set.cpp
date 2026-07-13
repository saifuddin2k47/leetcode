// User function Template for C++

// Disjoint Set Union class
class DisjointSet {
public:
    vector<int> parent, rank;

    // Constructor to initialize parent and rank arrays
    DisjointSet(int n) {
        parent.resize(n);
        rank.resize(n, 0);

        // Initialize parent of each node to itself
        for(int i = 0; i < n; i++)
            parent[i] = i;
    }

    // Function to find parent of a node with path compression
    int find(int node) {
        // If parent is the node itself, return node
        if(parent[node] == node)
            return node;

        // Recursively find parent and compress path
        return parent[node] = find(parent[node]);
    }

    // Function to union two nodes by rank
    void unionByRank(int u, int v) {
        // Find parents of both nodes
        int pu = find(u);
        int pv = find(v);

        // If both nodes have same parent, already connected
        if(pu == pv) return;

        // Attach lower rank tree under higher rank tree
        if(rank[pu] < rank[pv])
            parent[pu] = pv;
        else if(rank[pu] > rank[pv])
            parent[pv] = pu;
        else {
            // If ranks are equal, choose one and increase its rank
            parent[pv] = pu;
            rank[pu]++;
        }
    }
};

class Solution {
  public:
    int numProvinces(vector<vector<int>> adj, int V) {
        // code here
                // Initialize DSU with V nodes
        DisjointSet ds(V);

        // Traverse the adjacency matrix
        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++) {
                // If nodes i and j are connected and not same
                if(adj[i][j] == 1 && i != j)
                    ds.unionByRank(i, j); // Union them
            }
        }

        // Count number of unique parents
        int count = 0;
        for(int i = 0; i < V; i++) {
            // If node is its own parent, it's a province representative
            if(ds.find(i) == i)
                count++;
        }

        // Return total number of provinces
        return count;
    }
};
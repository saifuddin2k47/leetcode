class Solution {
private:
    bool dfs(int node, int col, vector<int>& color, vector<vector<int>>& adj) {
        color[node] = col;

        for (int it : adj[node]) {
            if (color[it] == -1) {
                if (!dfs(it, 1 - col, color, adj))
                    return false;
            }
            else if (color[it] == col) {
                return false;
            }
        }
        return true;
    }

public:
    bool isBipartite(int V, vector<vector<int>>& edges) {

        // Build adjacency list
        vector<vector<int>> adj(V);
        for (auto& e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        vector<int> color(V, -1);

        // Handle disconnected components
        for (int i = 0; i < V; i++) {
            if (color[i] == -1) {
                if (!dfs(i, 0, color, adj))
                    return false;
            }
        }

        return true;
    }
};

class Solution {
private:
    bool dfsCheck(int node, vector<vector<int>> &adj,
                  vector<int> &vis, vector<int> &pathVis) {

        vis[node] = 1;
        pathVis[node] = 1;

        for (int it : adj[node]) {
            // If node is not visited
            if (!vis[it]) {
                if (dfsCheck(it, adj, vis, pathVis))
                    return true;
            }
            // If node is visited and present in current DFS path
            else if (pathVis[it]) {
                return true;
            }
        }

        pathVis[node] = 0;  // backtrack
        return false;
    }

public:
    bool isCyclic(int V, vector<vector<int>> &edges) {
        // Create adjacency list
        vector<vector<int>> adj(V);
        for (auto &e : edges) {
            adj[e[0]].push_back(e[1]);
        }

        vector<int> vis(V, 0);
        vector<int> pathVis(V, 0);

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (dfsCheck(i, adj, vis, pathVis))
                    return true;
            }
        }
        return false;
    }
};

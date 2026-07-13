class Solution {
private:
    bool dfsCheck(int node, vector<vector<int>>& adj,
                  vector<int>& vis,
                  vector<int>& pathVis,
                  vector<int>& check) {
        
        vis[node] = 1;
        pathVis[node] = 1;
        check[node] = 0;

        for (auto it : adj[node]) {
            // if not visited
            if (!vis[it]) {
                if (dfsCheck(it, adj, vis, pathVis, check)) {
                    check[node] = 0;
                    return true;
                }
            }
            // cycle detected
            else if (pathVis[it]) {
                check[node] = 0;
                return true;
            }
        }

        check[node] = 1;     // safe node
        pathVis[node] = 0;   // backtrack
        return false;
    }

public:
    vector<int> safeNodes(int V, vector<vector<int>>& edges) {
        vector<vector<int>> adj(V);
        
        // build adjacency list
        for (auto& e : edges) {
            adj[e[0]].push_back(e[1]);
        }

        vector<int> vis(V, 0), pathVis(V, 0), check(V, 0);
        vector<int> safeNodes;

        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfsCheck(i, adj, vis, pathVis, check);
            }
        }

        for (int i = 0; i < V; i++) {
            if (check[i] == 1)
                safeNodes.push_back(i);
        }

        return safeNodes;
    }
};
#include <bits/stdc++.h>
using namespace std;

class Solution {
private:
    bool dfs(int node, int parent, vector<int>& vis, vector<vector<int>>& adj) {
        vis[node] = 1;

        for (int adjacentNode : adj[node]) {
            if (!vis[adjacentNode]) {
                if (dfs(adjacentNode, node, vis, adj)) {
                    return true;
                }
            }
            else if (adjacentNode != parent) {
                return true; // cycle detected
            }
        }
        return false;
    }

public:
    bool isCycle(int V, vector<vector<int>>& edges) {
        // create adjacency list
        vector<vector<int>> adj(V);
        for (auto &e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }

        vector<int> vis(V, 0);

        // check all components
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                if (dfs(i, -1, vis, adj)) return true;
            }
        }
        return false;
    }
};
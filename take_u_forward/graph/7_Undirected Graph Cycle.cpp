#include <bits/stdc++.h>
using namespace std;

class Pair {
public:
    int first, second;
    Pair(int f, int s) {
        first = f;
        second = s;
    }
};

class Solution {
private:
    bool checkForCycle(int src, vector<vector<int>>& adj, vector<bool>& vis) {
        vis[src] = true;

        queue<Pair> q;
        q.push(Pair(src, -1));  // (node, parent)

        while(!q.empty()) {
            int node = q.front().first;
            int parent = q.front().second;
            q.pop();

            for(int adjacentNode : adj[node]) {
                if(!vis[adjacentNode]) {
                    vis[adjacentNode] = true;
                    q.push(Pair(adjacentNode, node));
                }
                else if(adjacentNode != parent) {
                    return true; // Cycle found
                }
            }
        }
        return false;
    }

public:
    bool isCycle(int V, vector<vector<int>>& edges) {
        // Create adjacency list
        vector<vector<int>> adj(V);

        for(int i = 0; i < edges.size(); i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            adj[u].push_back(v);
            adj[v].push_back(u);
        }

        vector<bool> vis(V, false);

        // Check in all components
        for(int i = 0; i < V; i++) {
            if(!vis[i]) {
                if(checkForCycle(i, adj, vis)) return true;
            }
        }

        return false;
    }
};
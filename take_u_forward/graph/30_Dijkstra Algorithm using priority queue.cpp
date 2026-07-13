#include <bits/stdc++.h>
using namespace std;

class Solution {
  public:
    vector<int> dijkstra(int V, vector<vector<int>> &edges, int src) {
        // Adjacency list: node -> {neighbor, weight}
        vector<vector<pair<int, int>>> adj(V);
        
        // Build the graph
        for (auto &e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adj[u].push_back({v, w});
            adj[v].push_back({u, w}); // undirected graph
        }
        
        // Distance array
        vector<int> dist(V, INT_MAX);
        dist[src] = 0;
        
        // Min-heap: {distance, node}
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<pair<int, int>>> pq;
        pq.push({0, src});
        
        while (!pq.empty()) {
            auto [d, node] = pq.top();
            pq.pop();
            
            // If we already found a better path, skip
            if (d > dist[node]) continue;
            
            for (auto &it : adj[node]) {
                int nextNode = it.first;
                int weight = it.second;
                
                if (dist[node] + weight < dist[nextNode]) {
                    dist[nextNode] = dist[node] + weight;
                    pq.push({dist[nextNode], nextNode});
                }
            }
        }
        
        return dist;
    }
};
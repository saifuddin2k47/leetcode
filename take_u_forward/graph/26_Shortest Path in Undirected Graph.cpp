class Solution {
  public:
    vector<int> shortestPath(int V, vector<vector<int>> &edges, int src) {
        // Adjacency list
        vector<vector<int>> adj(V);
        
        // Build the graph
        for (auto &e : edges) {
            adj[e[0]].push_back(e[1]);
            adj[e[1]].push_back(e[0]);
        }
        
        // Distance array
        vector<int> dist(V, INT_MAX);
        dist[src] = 0;
        
        // BFS queue
        queue<int> q;
        q.push(src);
        
        // BFS traversal
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            
            for (int neigh : adj[node]) {
                if (dist[node] + 1 < dist[neigh]) {
                    dist[neigh] = dist[node] + 1;
                    q.push(neigh);
                }
            }
        }
        
        // Replace unreachable distances with -1
        for (int i = 0; i < V; i++) {
            if (dist[i] == INT_MAX)
                dist[i] = -1;
        }
        
        return dist;
    }
};
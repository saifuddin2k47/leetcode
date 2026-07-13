class Solution {
public:
    vector<int> dijkstra(int V, vector<vector<int>> &edges, int src) {
        
        // Step 1: Create adjacency list
        vector<vector<pair<int,int>>> adj(V);
        for (auto &e : edges) {
            int u = e[0];
            int v = e[1];
            int w = e[2];
            adj[u].push_back({v, w});
            adj[v].push_back({u, w});  // undirected graph
        }
        
        // Step 2: Distance array
        vector<int> dist(V, 1e9);
        dist[src] = 0;
        
        // Step 3: Set to store {distance, node}
        set<pair<int,int>> st;
        st.insert({0, src});
        
        // Step 4: Dijkstra
        while (!st.empty()) {
            auto it = *st.begin();
            st.erase(st.begin());
            
            int node = it.second;
            int dis  = it.first;
            
            for (auto &p : adj[node]) {
                int adjNode = p.first;
                int edgeW   = p.second;
                
                if (dis + edgeW < dist[adjNode]) {
                    
                    // Remove old entry if exists
                    if (dist[adjNode] != 1e9) {
                        st.erase({dist[adjNode], adjNode});
                    }
                    
                    dist[adjNode] = dis + edgeW;
                    st.insert({dist[adjNode], adjNode});
                }
            }
        }
        
        return dist;
    }
};

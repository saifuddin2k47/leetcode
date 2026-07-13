class Solution {
  public:
    bool isCyclic(int V, vector<vector<int>> &edges) {
        // code here
         // Build adjacency list
        vector<vector<int>> adj(V);
        for (auto &e : edges) {
            adj[e[0]].push_back(e[1]);
        }

        // Indegree array
        vector<int> indegree(V, 0);
        for (int i = 0; i < V; i++) {
            for (int it : adj[i]) {
                indegree[it]++;
            }
        }

        // Queue for nodes with indegree 0
        queue<int> q;
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.push(i);
            }
        }

        int cnt=0;

        // Kahn's Algorithm
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            cnt++;

            for (int it : adj[node]) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.push(it);
                }
            }
        }
        
        if(cnt==V) return false;
        return true;
    }
};
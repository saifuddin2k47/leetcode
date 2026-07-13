class Solution {
    private:
    
    // Step 1: DFS to store nodes according to finishing time
    void dfs(int node, vector<int> &vis, vector<int> adj[], stack<int> &st) {
        vis[node] = 1;

        for (auto it : adj[node]) {
            if (!vis[it]) {
                dfs(it, vis, adj, st);
            }
        }

        st.push(node);
    }

    // Step 2: DFS on transpose graph
    void dfs3(int node, vector<int> &vis, vector<int> adjT[]) {
        vis[node] = 1;

        for (auto it : adjT[node]) {
            if (!vis[it]) {
                dfs3(it, vis, adjT);
            }
        }
    }

  public:
    int kosaraju(int V, vector<vector<int>> &edges) {

        // Create adjacency list
        vector<int> adj[V];

        for (auto &e : edges) {
            int u = e[0];
            int v = e[1];

            adj[u].push_back(v);
        }

        vector<int> vis(V, 0);
        stack<int> st;

        // Step 1: Fill stack according to finishing time
        for (int i = 0; i < V; i++) {
            if (!vis[i]) {
                dfs(i, vis, adj, st);
            }
        }

        // Step 2: Create transpose graph
        vector<int> adjT[V];

        for (int i = 0; i < V; i++) {
            vis[i] = 0;

            for (auto it : adj[i]) {
                adjT[it].push_back(i);
            }
        }

        // Step 3: Count SCCs
        int scc = 0;

        while (!st.empty()) {
            int node = st.top();
            st.pop();

            if (!vis[node]) {
                scc++;
                dfs3(node, vis, adjT);
            }
        }

        return scc;
    }
};
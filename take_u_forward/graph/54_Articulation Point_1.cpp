// User function Template for C++

class Solution {
    private:
        int timer = 1;
    
        // DFS to find articulation points
        void dfs(int node, int parent, vector<int> &vis, int tin[], int low[],
                 vector<int> &mark, vector<int> adj[]) {
    
            vis[node] = 1;
            tin[node] = low[node] = timer++; // set discovery and low time
            int child = 0;
    
            for (auto it : adj[node]) {
                if (it == parent) continue; // skip the edge to parent
    
                if (!vis[it]) {
                    dfs(it, node, vis, tin, low, mark, adj); // recursive DFS
                    low[node] = min(low[node], low[it]);     // update low time
    
                    // Check articulation condition (excluding root)
                    if (low[it] >= tin[node] && parent != -1) {
                        mark[node] = 1;
                    }
                    child++;
                }
                else {
                    // back edge case
                    low[node] = min(low[node], tin[it]);
                }
            }
    
            // If root node has more than one child
            if (parent == -1 && child > 1) {
                mark[node] = 1;
            }
        }
  public:
    vector<int> articulationPoints(int n, vector<int> adj[]) {
        // Code here
        vector<int> vis(n, 0), mark(n, 0);
        int tin[n], low[n];

        for (int i = 0; i < n; i++) {
            if (!vis[i]) {
                dfs(i, -1, vis, tin, low, mark, adj);
            }
        }

        vector<int> ans;
        for (int i = 0; i < n; i++) {
            if (mark[i]) ans.push_back(i);
        }
        return ans.empty() ? vector<int>{-1} : ans;
    }
};
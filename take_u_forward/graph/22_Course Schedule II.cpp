class Solution {
  public:
    vector<int> findOrder(int n, vector<vector<int>> &prerequisites) {
        // code here
        // Step 1: Create adjacency list
        vector<vector<int>> adj(n);
        for (auto &p : prerequisites) {
            int x = p[0];
            int y = p[1];
            adj[y].push_back(x);   // y -> x
        }

        // Step 2: Compute indegree
        vector<int> indegree(n, 0);
        for (int i = 0; i < n; i++) {
            for (int it : adj[i]) {
                indegree[it]++;
            }
        }

        // Step 3: Push nodes with indegree 0
        queue<int> q;
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.push(i);
            }
        }

        // Step 4: Kahn's Algorithm
        vector<int> order;
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            order.push_back(node);

            for (int it : adj[node]) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.push(it);
                }
            }
        }

        // Step 5: Check if valid ordering exists
        if (order.size() != n) {
            return {};   // cycle detected
        }

        return order;
    }
};
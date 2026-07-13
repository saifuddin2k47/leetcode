class Solution {
  public:
    bool isPossible(int N, int P, vector<pair<int, int> >& prerequisites) {
        // Code here
        // Step 1: Create adjacency list
        vector<vector<int>> adj(N);
        for (auto &p : prerequisites) {
            int u = p.first;
            int v = p.second;
            adj[v].push_back(u);   // v -> u
        }

        // Step 2: Calculate indegree
        vector<int> indegree(N, 0);
        for (int i = 0; i < N; i++) {
            for (int it : adj[i]) {
                indegree[it]++;
            }
        }

        // Step 3: Push nodes with indegree 0
        queue<int> q;
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.push(i);
            }
        }

        // Step 4: Kahn’s Algorithm
        int cnt = 0;
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

        // Step 5: Check cycle
        return cnt == N;   // true = possible, false = cycle
    }
};
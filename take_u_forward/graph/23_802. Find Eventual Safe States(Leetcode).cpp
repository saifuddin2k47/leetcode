class Solution {
public:
    vector<int> eventualSafeNodes(vector<vector<int>>& graph) {
        int V = graph.size();

        vector<vector<int>> adjRev(V);
        vector<int> indegree(V, 0);

        // Build reverse graph and indegree
        for (int i = 0; i < V; i++) {
            for (int it : graph[i]) {
                adjRev[it].push_back(i);
                indegree[i]++;
            }
        }

        queue<int> q;
        vector<int> safeNodes;

        // Nodes with zero outgoing edges (terminal nodes)
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0) {
                q.push(i);
            }
        }

        // Kahn's Algorithm
        while (!q.empty()) {
            int node = q.front();
            q.pop();
            safeNodes.push_back(node);

            for (int it : adjRev[node]) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.push(it);
                }
            }
        }

        sort(safeNodes.begin(), safeNodes.end());
        return safeNodes;
    }
};

class Solution {
public:
    vector<int> shortestPath(int n, int m, vector<vector<int>>& edges) {

        // Adjacency list
        vector<vector<pair<int,int>>> adj(n + 1);
        for (auto &e : edges) {
            adj[e[0]].push_back({e[1], e[2]});
            adj[e[1]].push_back({e[0], e[2]});
        }

        // Min heap {distance, node}
        priority_queue<pair<long long,int>, 
            vector<pair<long long,int>>, 
            greater<pair<long long,int>>> pq;

        vector<long long> dist(n + 1, 1e18);
        vector<int> parent(n + 1);

        for (int i = 1; i <= n; i++)
            parent[i] = i;

        dist[1] = 0;
        pq.push({0, 1});

        while (!pq.empty()) {
            auto [d, node] = pq.top();
            pq.pop();

            if (d > dist[node]) continue;

            for (auto &it : adj[node]) {
                int adjNode = it.first;
                int wt = it.second;

                if (d + wt < dist[adjNode]) {
                    dist[adjNode] = d + wt;
                    parent[adjNode] = node;
                    pq.push({dist[adjNode], adjNode});
                }
            }
        }

        // No path
        if (dist[n] == 1e18)
            return {-1};

        // Reconstruct path
        vector<int> path;
        int node = n;
        while (parent[node] != node) {
            path.push_back(node);
            node = parent[node];
        }
        path.push_back(1);
        reverse(path.begin(), path.end());

        // Required output format
        vector<int> ans;
        ans.push_back(dist[n]);      // total weight
        for (int x : path) ans.push_back(x);

        return ans;
    }
};

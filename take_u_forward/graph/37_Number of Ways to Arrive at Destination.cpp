class Solution {
public:
    int countPaths(int V, vector<vector<int>>& edges) {

        vector<pair<int,int>> adj[V];
        for (auto &e : edges) {
            adj[e[0]].push_back({e[1], e[2]});
            adj[e[1]].push_back({e[0], e[2]});
        }

        priority_queue<pair<long long,int>,
                       vector<pair<long long,int>>,
                       greater<pair<long long,int>>> pq;

        vector<long long> dist(V, LLONG_MAX);
        vector<int> ways(V, 0);

        dist[0] = 0;
        ways[0] = 1;

        pq.push({0, 0});

        int mod = 1e9 + 7;

        while (!pq.empty()) {
            long long dis = pq.top().first;
            int node = pq.top().second;
            pq.pop();

            if (dis > dist[node]) continue;

            for (auto &it : adj[node]) {
                int adjNode = it.first;
                long long edW = it.second;

                if (dis + edW < dist[adjNode]) {
                    dist[adjNode] = dis + edW;
                    ways[adjNode] = ways[node];
                    pq.push({dist[adjNode], adjNode});
                }
                else if (dis + edW == dist[adjNode]) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod;
                }
            }
        }

        return ways[V - 1];
    }
};

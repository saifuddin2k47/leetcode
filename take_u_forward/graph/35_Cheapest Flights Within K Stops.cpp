class Solution {
public:
    int CheapestFLight(int n, vector<vector<int>>& flights,
                       int src, int dst, int K) {

        // Adjacency list: node -> {neighbor, cost}
        vector<vector<pair<int,int>>> adj(n);
        for (auto &f : flights) {
            adj[f[0]].push_back({f[1], f[2]});
        }

        // Queue format: {stops, {node, cost}}
        queue<pair<int, pair<int,int>>> q;
        q.push({0, {src, 0}});

        vector<int> dist(n, 1e9);
        dist[src] = 0;

        while (!q.empty()) {
            auto cur = q.front();
            q.pop();

            int stops = cur.first;
            int node  = cur.second.first;
            int cost  = cur.second.second;

            if (stops > K) continue;

            for (auto &it : adj[node]) {
                int nextNode = it.first;
                int price    = it.second;

                // Only relax cost if cheaper AND within stop limit
                if (cost + price < dist[nextNode]) {
                    dist[nextNode] = cost + price;
                    q.push({stops + 1, {nextNode, cost + price}});
                }
            }
        }

        return dist[dst] == 1e9 ? -1 : dist[dst];
    }
};
class Solution {
  public:
    int minCostPath(vector<vector<int>>& mat) {
        // code here
         int n = mat.size();
        int m = mat[0].size();

        // Min-heap: {effort, {row, col}}
        priority_queue<
            pair<int, pair<int, int>>,
            vector<pair<int, pair<int, int>>>,
            greater<pair<int, pair<int, int>>>
        > pq;

        vector<vector<int>> dist(n, vector<int>(m, 1e9));
        dist[0][0] = 0;

        pq.push({0, {0, 0}});

        int dr[4] = {-1, 0, 1, 0};
        int dc[4] = {0, 1, 0, -1};

        while (!pq.empty()) {
            auto [effort, cell] = pq.top();
            pq.pop();

            int r = cell.first;
            int c = cell.second;

            // Destination reached with minimum effort
            if (r == n - 1 && c == m - 1)
                return effort;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < n && nc < m) {
                    int newEffort = max(
                        effort,
                        abs(mat[r][c] - mat[nr][nc])
                    );

                    if (newEffort < dist[nr][nc]) {
                        dist[nr][nc] = newEffort;
                        pq.push({newEffort, {nr, nc}});
                    }
                }
            }
        }
        return 0;
    }
};

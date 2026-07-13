class Solution {
  public:
    int shortestPath(vector<vector<int>> &grid, pair<int, int> source,
                     pair<int, int> destination) {

        int n = grid.size();
        int m = grid[0].size();

        // ✅ SOURCE == DESTINATION EDGE CASE
        if (source == destination) {
            return grid[source.first][source.second] == 1 ? 0 : -1;
        }

        // If source or destination is blocked
        if (grid[source.first][source.second] == 0 ||
            grid[destination.first][destination.second] == 0)
            return -1;

        vector<vector<int>> dist(n, vector<int>(m, 1e9));
        queue<pair<int,int>> q;

        dist[source.first][source.second] = 0;
        q.push({source.first, source.second});

        int dr[] = {-1, 0, 1, 0};
        int dc[] = {0, 1, 0, -1};

        while (!q.empty()) {
            auto [r, c] = q.front();
            q.pop();

            for (int i = 0; i < 4; i++) {
                int newr = r + dr[i];
                int newc = c + dc[i];

                if (newr >= 0 && newr < n &&
                    newc >= 0 && newc < m &&
                    grid[newr][newc] == 1 &&
                    dist[r][c] + 1 < dist[newr][newc]) {

                    dist[newr][newc] = dist[r][c] + 1;

                    if (newr == destination.first &&
                        newc == destination.second)
                        return dist[newr][newc];

                    q.push({newr, newc});
                }
            }
        }
        return -1;
    }
};

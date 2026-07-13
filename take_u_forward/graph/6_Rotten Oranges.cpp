class Solution {
public:
    int orangesRot(vector<vector<int>>& grid) {

        int n = grid.size();
        int m = grid[0].size();

        // queue -> {{r, c}, time}
        queue<pair<pair<int,int>, int>> q;

        vector<vector<int>> vis(n, vector<int>(m, 0));
        int cntFresh = 0;

        // Push all rotten oranges
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 2) {
                    q.push({{i, j}, 0});
                    vis[i][j] = 2;
                }
                if(grid[i][j] == 1) cntFresh++;
            }
        }

        int tm = 0;
        int drow[] = {-1, 0, +1, 0};
        int dcol[] = {0, 1, 0, -1};

        int cnt = 0;

        // BFS
        while(!q.empty()) {
            int r = q.front().first.first;
            int c = q.front().first.second;
            int t = q.front().second;
            q.pop();

            tm = max(tm, t);

            for(int i = 0; i < 4; i++) {
                int nrow = r + drow[i];
                int ncol = c + dcol[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                   vis[nrow][ncol] != 2 && grid[nrow][ncol] == 1) {

                    q.push({{nrow, ncol}, t + 1});
                    vis[nrow][ncol] = 2;
                    cnt++;
                }
            }
        }

        // If some fresh oranges were never rotten
        if(cnt != cntFresh) return -1;

        return tm;
    }
};
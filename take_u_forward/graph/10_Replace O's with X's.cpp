class Solution {
private:
    int n, m;

    void dfs(int row, int col, vector<vector<int>>& vis, vector<vector<char>>& grid) {
        vis[row][col] = 1;

        int delRow[] = {-1, 0, 1, 0};
        int delCol[] = {0, 1, 0, -1};

        for (int i = 0; i < 4; i++) {
            int nrow = row + delRow[i];
            int ncol = col + delCol[i];

            if (nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                !vis[nrow][ncol] && grid[nrow][ncol] == 'O') {
                dfs(nrow, ncol, vis, grid);
            }
        }
    }

public:
    void fill(vector<vector<char>>& grid) {
        n = grid.size();
        m = grid[0].size();

        vector<vector<int>> vis(n, vector<int>(m, 0));

        // Traverse first & last row
        for (int j = 0; j < m; j++) {
            if (!vis[0][j] && grid[0][j] == 'O')
                dfs(0, j, vis, grid);

            if (!vis[n - 1][j] && grid[n - 1][j] == 'O')
                dfs(n - 1, j, vis, grid);
        }

        // Traverse first & last column
        for (int i = 0; i < n; i++) {
            if (!vis[i][0] && grid[i][0] == 'O')
                dfs(i, 0, vis, grid);

            if (!vis[i][m - 1] && grid[i][m - 1] == 'O')
                dfs(i, m - 1, vis, grid);
        }

        // Convert unvisited 'O' to 'X'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!vis[i][j] && grid[i][j] == 'O') {
                    grid[i][j] = 'X';
                }
            }
        }
    }
};

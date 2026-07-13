
class Solution {
  public:
    int numberOfEnclaves(vector<vector<int>> &grid) {
        int n = grid.size();
        int m = grid[0].size();
        
        queue<pair<int,int>> q;
        vector<vector<int>> vis(n, vector<int>(m, 0));

        // Push all boundary land cells
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(i == 0 || j == 0 || i == n-1 || j == m-1){
                    if(grid[i][j] == 1){
                        q.push({i, j});
                        vis[i][j] = 1;
                    }
                }
            }
        }

        int delrow[] = {-1, 0, 1, 0};
        int delcol[] = {0, 1, 0, -1};

        // BFS
        while(!q.empty()){
            auto [row, col] = q.front();
            q.pop();

            for(int i = 0; i < 4; i++){
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];

                if(nrow >= 0 && nrow < n && ncol >= 0 && ncol < m &&
                   vis[nrow][ncol] == 0 && grid[nrow][ncol] == 1){
                    q.push({nrow, ncol});
                    vis[nrow][ncol] = 1;
                }
            }
        }

        // Count enclaves
        int cnt = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(grid[i][j] == 1 && vis[i][j] == 0)
                    cnt++;
            }
        }
        return cnt;
    }
};
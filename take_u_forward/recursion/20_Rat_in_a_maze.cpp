#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    // Check whether the cell is valid
    bool isSafe(int x, int y, int n, vector<vector<int>> &maze,
                vector<vector<int>> &visited) {
        return (x >= 0 && x < n &&
                y >= 0 && y < n &&
                maze[x][y] == 1 &&
                visited[x][y] == 0);
    }

    // Backtracking function
    void solve(int x, int y, int n, vector<vector<int>> &maze,
               vector<vector<int>> &visited, string path,
               vector<string> &res) {

        // Destination reached
        if (x == n - 1 && y == n - 1) {
            res.push_back(path);
            return;
        }

        // Mark current cell as visited
        visited[x][y] = 1;

        // Down
        if (isSafe(x + 1, y, n, maze, visited))
            solve(x + 1, y, n, maze, visited, path + "D", res);

        // Left
        if (isSafe(x, y - 1, n, maze, visited))
            solve(x, y - 1, n, maze, visited, path + "L", res);

        // Right
        if (isSafe(x, y + 1, n, maze, visited))
            solve(x, y + 1, n, maze, visited, path + "R", res);

        // Up
        if (isSafe(x - 1, y, n, maze, visited))
            solve(x - 1, y, n, maze, visited, path + "U", res);

        // Backtrack
        visited[x][y] = 0;
    }

    // Function expected by the driver
    vector<string> ratInMaze(vector<vector<int>> &maze) {
        int n = maze.size();
        vector<string> res;

        if (n == 0 || maze[0][0] == 0)
            return res;

        vector<vector<int>> visited(n, vector<int>(n, 0));

        solve(0, 0, n, maze, visited, "", res);

        sort(res.begin(), res.end()); // Lexicographical order
        return res;
    }
};
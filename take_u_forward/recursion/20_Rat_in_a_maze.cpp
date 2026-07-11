#include <bits/stdc++.h>
using namespace std;

// Class to solve the Rat in a Maze problem
class Solution {
public:
    // Function to check if the cell is within maze and valid to move
    bool isSafe(int x, int y, int n, vector<vector<int>> &maze, 
                vector<vector<int>> &visited) {
        return (x >= 0 && x < n && y >= 0 && y < n && 
                maze[x][y] == 1 && visited[x][y] == 0);
    }

    // Function to solve maze using backtracking
    void solve(int x, int y, int n, vector<vector<int>> &maze, 
               vector<vector<int>> &visited, string path, 
               vector<string> &res) {

        // If destination reached, store the path
        if (x == n - 1 && y == n - 1) {
            res.push_back(path);
            return;
        }

        // Mark the cell visited
        visited[x][y] = 1;

        // Try moving Down
        if (isSafe(x + 1, y, n, maze, visited)) {
            solve(x + 1, y, n, maze, visited, path + "D", res);
        }
        // Try moving Left
        if (isSafe(x, y - 1, n, maze, visited)) {
            solve(x, y - 1, n, maze, visited, path + "L", res);
        }
        // Try moving Right
        if (isSafe(x, y + 1, n, maze, visited)) {
            solve(x, y + 1, n, maze, visited, path + "R", res);
        }
        // Try moving Up
        if (isSafe(x - 1, y, n, maze, visited)) {
            solve(x - 1, y, n, maze, visited, path + "U", res);
        }

        // Backtrack: unmark cell as visited
        visited[x][y] = 0;
    }

    // Main function to find all paths
    vector<string> findPath(vector<vector<int>> &maze, int n) {
        vector<string> res;
        vector<vector<int>> visited(n, vector<int>(n, 0));
        if (maze[0][0] == 1) {
            solve(0, 0, n, maze, visited, "", res);
        }
        return res;
    }
};

// Driver code
int main() {
    vector<vector<int>> maze = {
        {1, 0, 0, 0},
        {1, 1, 0, 1},
        {1, 1, 0, 0},
        {0, 1, 1, 1}
    };
    int n = maze.size();
    Solution obj;
    vector<string> paths = obj.findPath(maze, n);

    for (auto &p : paths) cout << p << " ";
}
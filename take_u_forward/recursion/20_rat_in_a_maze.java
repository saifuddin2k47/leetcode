class Solution {

    boolean isSafe(int x, int y, int n, int[][] maze, int[][] visited) {
        return x >= 0 && x < n &&
               y >= 0 && y < n &&
               maze[x][y] == 1 &&
               visited[x][y] == 0;
    }

    void solve(int x, int y, int n, int[][] maze, int[][] visited,
               String path, ArrayList<String> res) {

        if (x == n - 1 && y == n - 1) {
            res.add(path);
            return;
        }

        visited[x][y] = 1;

        if (isSafe(x + 1, y, n, maze, visited))
            solve(x + 1, y, n, maze, visited, path + "D", res);

        if (isSafe(x, y - 1, n, maze, visited))
            solve(x, y - 1, n, maze, visited, path + "L", res);

        if (isSafe(x, y + 1, n, maze, visited))
            solve(x, y + 1, n, maze, visited, path + "R", res);

        if (isSafe(x - 1, y, n, maze, visited))
            solve(x - 1, y, n, maze, visited, path + "U", res);

        visited[x][y] = 0;
    }

    public ArrayList<String> ratInMaze(int[][] maze) {
        int n = maze.length;
        ArrayList<String> res = new ArrayList<>();

        if (maze[0][0] == 0)
            return res;

        int[][] visited = new int[n][n];
        solve(0, 0, n, maze, visited, "", res);

        return res;
    }
}
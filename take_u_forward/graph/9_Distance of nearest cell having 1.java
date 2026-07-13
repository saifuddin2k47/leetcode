import java.util.*;

class Node {
    int first, second, third;
    Node(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

class Solution {
    public ArrayList<ArrayList<Integer>> nearest(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        int[][] vis = new int[n][m];
        int[][] dist = new int[n][m];

        Queue<Node> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 1) {
                    q.add(new Node(i, j, 0));
                    vis[i][j] = 1;
                }
            }
        }

        int[] delrow = {-1, 0, 1, 0};
        int[] delcol = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Node node = q.poll();
            int row = node.first;
            int col = node.second;
            int steps = node.third;

            dist[row][col] = steps;

            for (int i = 0; i < 4; i++) {
                int nrow = row + delrow[i];
                int ncol = col + delcol[i];

                if (nrow >= 0 && nrow < n &&
                    ncol >= 0 && ncol < m &&
                    vis[nrow][ncol] == 0) {

                    vis[nrow][ncol] = 1;
                    q.add(new Node(nrow, ncol, steps + 1));
                }
            }
        }

        // Convert int[][] to ArrayList<ArrayList<Integer>>
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            ArrayList<Integer> row = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                row.add(dist[i][j]);
            }
            result.add(row);
        }

        return result;
    }
}
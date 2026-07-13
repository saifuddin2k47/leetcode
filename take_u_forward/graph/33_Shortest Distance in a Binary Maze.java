import java.util.*;

class Tuple {
    int dist, row, col;

    Tuple(int dist, int row, int col) {
        this.dist = dist;
        this.row = row;
        this.col = col;
    }
}

class Solution {

    int shortestPath(int[][] grid, int[] source, int[] destination) {

        int n = grid.length;
        int m = grid[0].length;

        // ✅ Source == Destination case
        if (source[0] == destination[0] && source[1] == destination[1]) {
            return grid[source[0]][source[1]] == 1 ? 0 : -1;
        }

        // ✅ Blocked source or destination
        if (grid[source[0]][source[1]] == 0 ||
            grid[destination[0]][destination[1]] == 0) {
            return -1;
        }

        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, (int) 1e9);
        }

        Queue<Tuple> q = new LinkedList<>();
        dist[source[0]][source[1]] = 0;
        q.offer(new Tuple(0, source[0], source[1]));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!q.isEmpty()) {
            Tuple cur = q.poll();
            int dis = cur.dist;
            int r = cur.row;
            int c = cur.col;

            for (int i = 0; i < 4; i++) {
                int newr = r + dr[i];
                int newc = c + dc[i];

                if (newr >= 0 && newr < n &&
                    newc >= 0 && newc < m &&
                    grid[newr][newc] == 1 &&
                    dis + 1 < dist[newr][newc]) {

                    dist[newr][newc] = dis + 1;

                    if (newr == destination[0] &&
                        newc == destination[1]) {
                        return dist[newr][newc];
                    }

                    q.offer(new Tuple(dist[newr][newc], newr, newc));
                }
            }
        }
        return -1;
    }
}
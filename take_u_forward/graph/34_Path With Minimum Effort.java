import java.util.*;

class Solution {

    public int minCostPath(int[][] mat) {

        int n = mat.length;
        int m = mat[0].length;

        // Min-heap: {effort, row, col}
        PriorityQueue<int[]> pq =
            new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        int[][] dist = new int[n][m];
        for (int[] row : dist) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        dist[0][0] = 0;
        pq.add(new int[]{0, 0, 0});

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();

            int effort = cur[0];
            int r = cur[1];
            int c = cur[2];

            // Early exit (Dijkstra property)
            if (r == n - 1 && c == m - 1)
                return effort;

            // Skip outdated entries
            if (effort > dist[r][c])
                continue;

            for (int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (nr >= 0 && nc >= 0 && nr < n && nc < m) {
                    int newEffort = Math.max(
                        effort,
                        Math.abs(mat[r][c] - mat[nr][nc])
                    );

                    if (newEffort < dist[nr][nc]) {
                        dist[nr][nc] = newEffort;
                        pq.add(new int[]{newEffort, nr, nc});
                    }
                }
            }
        }
        return 0;
    }
}
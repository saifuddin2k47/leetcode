class Solution {
    public void floydWarshall(int[][] dist) {
        int n = dist.length;
        int INF = 100000000;

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {

                    // Skip if path doesn't exist
                    if (dist[i][k] == INF || dist[k][j] == INF)
                        continue;

                    dist[i][j] = Math.min(
                        dist[i][j],
                        dist[i][k] + dist[k][j]
                    );
                }
            }
        }
    }
}

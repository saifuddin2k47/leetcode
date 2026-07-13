// User function Template for Java

class Solution {

    int findCity(int n, int m, int[][] edges, int distanceThreshold) {

        int[][] dist = new int[n][n];

        // Initialize distances
        for (int i = 0; i < n; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        }

        // Edge weights
        for (int[] e : edges) {
            dist[e[0]][e[1]] = e[2];
            dist[e[1]][e[0]] = e[2];
        }

        // Distance to itself = 0
        for (int i = 0; i < n; i++) {
            dist[i][i] = 0;
        }

        // Floyd–Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        int minCount = n;
        int cityNo = -1;

        // Count reachable neighbors
        for (int city = 0; city < n; city++) {
            int count = 0;
            for (int adj = 0; adj < n; adj++) {
                if (city != adj && dist[city][adj] <= distanceThreshold) {
                    count++;
                }
            }

            // Tie → choose larger city index
            if (count <= minCount) {
                minCount = count;
                cityNo = city;
            }
        }

        return cityNo;
    }
}

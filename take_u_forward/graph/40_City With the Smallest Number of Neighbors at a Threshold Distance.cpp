class Solution {
public:
    int findCity(int n, int m, vector<vector<int>>& edges, int distanceThreshold) {
        
        vector<vector<int>> dist(n, vector<int>(n, INT_MAX));

        // Initialize distances
        for (auto &e : edges) {
            dist[e[0]][e[1]] = e[2];
            dist[e[1]][e[0]] = e[2];
        }

        for (int i = 0; i < n; i++)
            dist[i][i] = 0;

        // Floyd–Warshall
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] == INT_MAX || dist[k][j] == INT_MAX)
                        continue;
                    dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int minCount = n;
        int answerCity = -1;

        for (int city = 0; city < n; city++) {
            int count = 0;
            for (int adj = 0; adj < n; adj++) {
                if (city != adj && dist[city][adj] <= distanceThreshold)
                    count++;
            }

            // Tie-breaking: choose city with greater index
            if (count <= minCount) {
                minCount = count;
                answerCity = city;
            }
        }

        return answerCity;
    }
};

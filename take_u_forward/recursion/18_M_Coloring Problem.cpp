class Solution {
public:
    // Function to check if it is safe to assign a color to a node
    bool isSafe(int node, int color[], bool graph[101][101], int n, int col) {
        for (int k = 0; k < n; k++) {
            if (graph[node][k] && color[k] == col) {
                return false;
            }
        }
        return true;
    }

    // Recursive function to solve the coloring problem
    bool solve(int node, int color[], int m, int N, bool graph[101][101]) {
        if (node == N)
            return true;

        for (int i = 1; i <= m; i++) {
            if (isSafe(node, color, graph, N, i)) {
                color[node] = i;

                if (solve(node + 1, color, m, N, graph))
                    return true;

                color[node] = 0; // Backtrack
            }
        }
        return false;
    }

    bool graphColoring(int v, vector<vector<int>> &edges, int m) {

        bool graph[101][101] = {false};

        // Convert edge list to adjacency matrix
        for (auto &edge : edges) {
            int u = edge[0];
            int w = edge[1];
            graph[u][w] = true;
            graph[w][u] = true;
        }

        int color[101] = {0};

        return solve(0, color, m, v, graph);
    }
};
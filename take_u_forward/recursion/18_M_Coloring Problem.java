import java.util.*;

class Solution {

    // Function to check if it is safe to assign a color to a node
    public static boolean isSafe(int node, int[] color, boolean[][] graph, int n, int col) {
        for (int k = 0; k < n; k++) {
            if (graph[node][k] && color[k] == col) {
                return false;
            }
        }
        return true;
    }

    // Recursive function
    public static boolean solve(int node, int[] color, int m, int N, boolean[][] graph) {
        if (node == N) {
            return true;
        }

        for (int i = 1; i <= m; i++) {
            if (isSafe(node, color, graph, N, i)) {
                color[node] = i;

                if (solve(node + 1, color, m, N, graph))
                    return true;

                color[node] = 0;
            }
        }
        return false;
    }

    boolean graphColoring(int N, int[][] edges, int m) {

        // Create adjacency matrix
        boolean[][] graph = new boolean[N][N];

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u][v] = true;
            graph[v][u] = true;
        }

        int[] color = new int[N];

        return solve(0, color, m, N, graph);
    }
}
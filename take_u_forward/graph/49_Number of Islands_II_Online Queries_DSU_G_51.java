import java.util.*;

class DisjointSet {
    private int[] rank, parent, size;

    public DisjointSet(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        Arrays.fill(size, 1);
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
        }
    }

    public int findUPar(int node) {
        if (node == parent[node]) return node;
        return parent[node] = findUPar(parent[node]); // Path compression
    }

    public void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        } else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        } else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v];
        }
    }
}

class Solution {
    private boolean isValid(int adjr, int adjc, int n, int m) {
        return adjr >= 0 && adjr < n && adjc >= 0 && adjc < m;
    }

    public int[] numOfIslands(int n, int m, int[][] operators) {
        DisjointSet ds = new DisjointSet(n * m);
        int[][] vis = new int[n][m];
        int cnt = 0;
        List<Integer> ans = new ArrayList<>();
        
        for (int[] it : operators) {
            int row = it[0], col = it[1];
            if (vis[row][col] == 1) {
                ans.add(cnt);
                continue;
            }

            vis[row][col] = 1;
            cnt++;

            int[] dr = {-1, 0, 1, 0};
            int[] dc = {0, 1, 0, -1};
            for (int ind = 0; ind < 4; ind++) {
                int adjr = row + dr[ind], adjc = col + dc[ind];
                if (isValid(adjr, adjc, n, m)) {
                    if (vis[adjr][adjc] == 1) {
                        int nodeNo = row * m + col;
                        int adjNodeNo = adjr * m + adjc;
                        if (ds.findUPar(nodeNo) != ds.findUPar(adjNodeNo)) {
                            cnt--;
                            ds.unionBySize(nodeNo, adjNodeNo);
                        }
                    }
                }
            }
            ans.add(cnt);
        }

        // Convert List to Array for returning
        return ans.stream().mapToInt(i -> i).toArray();
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 4, m = 5;
        int[][] operators = {
            {0, 0}, {0, 0}, {1, 1}, {1, 0}, {0, 1},
            {0, 3}, {1, 3}, {0, 4}, {3, 2}, {2, 2}, {1, 2}, {0, 2}
        };

        Solution obj = new Solution();
        int[] ans = obj.numOfIslands(n, m, operators);
        for (int res : ans) {
            System.out.print(res + " ");
        }
        System.out.println();
    }
}
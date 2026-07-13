// User function Template for Java

// User function Template for Java

class Solution {

    private static void dfs(int node, ArrayList<ArrayList<Integer>> adjLs, int vis[]) {
        vis[node] = 1;
        for (Integer it : adjLs.get(node)) {
            if (vis[it] == 0) {
                dfs(it, adjLs, vis);
            }
        }
    }

    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {

        // adjacency list creation
        ArrayList<ArrayList<Integer>> adjLs = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjLs.add(new ArrayList<>());
        }

        // convert adjacency matrix to list
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (adj.get(i).get(j) == 1 && i != j) {
                    adjLs.get(i).add(j);
                    adjLs.get(j).add(i);
                }
            }
        }

        int vis[] = new int[V];
        int cnt = 0;

        for (int i = 0; i < V; i++) {
            if (vis[i] == 0) {
                cnt++;
                dfs(i, adjLs, vis);
            }
        }
        return cnt;
    }
}

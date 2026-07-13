// User function Template for Java

class Solution {
    public boolean isPossible(int N, int P, int[][] prerequisites) {
        // Your Code goes here
         // Create adjacency list
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            adj.add(new ArrayList<>());
        }

        // Correct edge direction: v -> u
        for (int i = 0; i < P; i++) {
            int u = prerequisites[i][0];
            int v = prerequisites[i][1];
            adj.get(v).add(u);
        }
        
         // Indegree array
        int[] indegree = new int[N];
        for (int i = 0; i < N; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        // Queue for nodes with indegree 0
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

       int cnt=0;

        // Kahn's Algorithm
        while (!q.isEmpty()) {
            int node = q.poll();
            cnt++; 
            for (int it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }

        if(cnt==N) return true;
        return false;
    }
}
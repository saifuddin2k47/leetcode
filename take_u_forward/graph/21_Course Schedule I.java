class Solution {
    public boolean canFinish(int n, int[][] prerequisites) {
        // code here
        // Step 1: Create adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Build graph (y -> x)
        for (int[] p : prerequisites) {
            int x = p[0];
            int y = p[1];
            adj.get(y).add(x);
        }

        // Step 3: Compute indegree
        int[] indegree = new int[n];
        for (int i = 0; i < n; i++) {
            for (int it : adj.get(i)) {
                indegree[it]++;
            }
        }

        // Step 4: Queue for courses with indegree 0
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // Step 5: Kahn's Algorithm
        int count = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            count++;

            for (int it : adj.get(node)) {
                indegree[it]--;
                if (indegree[it] == 0) {
                    q.add(it);
                }
            }
        }

        // Step 6: If all courses processed → possible
        return count == n;
    }
}
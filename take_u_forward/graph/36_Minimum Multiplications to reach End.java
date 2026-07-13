// User function Template for Java

class Solution {
    int minimumMultiplications(int[] arr, int start, int end) {
        
        // If start is already equal to end
        if (start == end) return 0;
        
        int mod = 100000;
        
        // Distance array to store minimum steps to reach each number
        int[] dist = new int[mod];
        Arrays.fill(dist, (int) 1e9);
        dist[start] = 0;
        
        // Queue for BFS: {current value, steps}
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{start, 0});
        
        // BFS traversal
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int node = curr[0];
            int steps = curr[1];
            
            for (int factor : arr) {
                int num = (node * factor) % mod;
                
                // If we found a shorter path
                if (steps + 1 < dist[num]) {
                    dist[num] = steps + 1;
                    
                    // If end is reached
                    if (num == end) {
                        return steps + 1;
                    }
                    
                    q.offer(new int[]{num, steps + 1});
                }
            }
        }
        
        // If end is not reachable
        return -1;
    }
}
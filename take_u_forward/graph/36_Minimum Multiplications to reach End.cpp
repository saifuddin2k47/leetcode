// User function Template for C++

class Solution {
  public:
    int minimumMultiplications(vector<int>& arr, int start, int end) {
        
        // If start is already equal to end
        if(start == end) return 0;
        
        const int mod = 100000;
        
        // Distance array to store minimum steps to reach each number
        vector<int> dist(mod, 1e9);
        
        // BFS queue: {current value, steps}
        queue<pair<int,int>> q;
        
        q.push({start, 0});
        dist[start] = 0;
        
        while(!q.empty()) {
            int node = q.front().first;
            int steps = q.front().second;
            q.pop();
            
            for(int it : arr) {
                int num = (node * it) % mod;
                
                // If we found a shorter path to num
                if(steps + 1 < dist[num]) {
                    dist[num] = steps + 1;
                    
                    // If end is reached, return answer
                    if(num == end) {
                        return steps + 1;
                    }
                    
                    q.push({num, steps + 1});
                }
            }
        }
        
        // If end is not reachable
        return -1;
    }
};

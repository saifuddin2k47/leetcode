
// Memoizaton Solution 

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    // Function to calculate Fibonacci using memoization
    int fib(int n, vector<int>& dp) {
        // If base case return n
        if (n <= 1) return n;

        // If already computed, return stored value
        if (dp[n] != -1) return dp[n];

        // Otherwise compute and store
        dp[n] = fib(n - 1, dp) + fib(n - 2, dp);
        return dp[n];
    }
};

int main() {
    int n = 10;
    vector<int> dp(n + 1, -1);
    Solution sol;
    cout << sol.fib(n, dp);
    return 0;
}

// Tabulation Solution

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    // Function to calculate Fibonacci using tabulation
    int fib(int n) {
        // If n is 0 or 1, return n
        if (n <= 1) return n;

        // Create dp array
        vector<int> dp(n + 1, 0);

        // Initialize base cases
        dp[0] = 0;
        dp[1] = 1;

        // Fill dp array iteratively
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        // Return final answer
        return dp[n];
    }
};

int main() {
    int n = 10;
    Solution sol;
    cout << sol.fib(n);
    return 0;
}
